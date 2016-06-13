package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.exceptions.ModelException;
import com.epam.homework_9.dao.impl.db.executor.Executor;
import com.epam.homework_9.dao.impl.db.executor.Procedure;
import com.epam.homework_9.dao.impl.db.utils.SqlQueries;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.Track;
import com.epam.homework_9.validators.ModelValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.epam.homework_9.dao.impl.db.utils.SqlColumns.*;

public class ArtistDBDao implements Dao<Artist>, AutoCloseable {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Connection connection;
    private AlbumDBDao albumDBDao;


    public ArtistDBDao(Connection connection) {
        this.connection = connection;
        this.albumDBDao = new AlbumDBDao(connection);
    }

    @Override
    public List<Artist> getAll() {
        try {
            List<Artist> artistList = new Executor(connection).executeQuery(SqlQueries.SELECT_ALL_ARTISTS, statement -> {
            }, result -> {
                List<Artist> list = new ArrayList<>();
                while (result.next()) {
                    long id = result.getLong("id");
                    String name = result.getString("name");
                    Set<Album> albumSet = selectAlbums(id);
                    list.add(Artist.newBuilder().id(id).name(name).addAllAlbum(albumSet).build());
                }
                return list;
            });
            artistList.forEach(this::validate);
            return artistList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }

    @Override
    public Artist getById(Long id) {
        try {
            Artist artist = new Executor(connection).executeQuery(
                    SqlQueries.SELECT_ARTIST_BY_ID,
                    pStatement -> pStatement.setLong(1, id),
                    result -> processResultArtistSet(id, result));
            validate(artist);
            return artist;
        } catch (SQLException e) {
            logger.error(String.format("getting Artist by id:%d. %s, %s", id, e.getMessage(), e.getNextException()));
            throw new DaoException("select error with ", e);
        }
    }

    private Artist processResultArtistSet(Long id, ResultSet resultArtistSet) throws SQLException {
        resultArtistSet.next();
        String name = resultArtistSet.getString(ARTIST_NAME);
        Artist.Builder artistBuilder = Artist.newBuilder().id(id).name(name);
        Set<Album> albums = selectAlbums(id);
        albums.forEach(artistBuilder::addAlbum);
        return artistBuilder.build();
    }

    private Set<Album> selectAlbums(Long id) throws SQLException {
        return new Executor(connection).executeQuery(
                SqlQueries.SELECT_ALBUMS_FROM_ARTIST,
                s -> s.setLong(1, id),
                r -> {
                    Set<Album> albumSet = new HashSet<>();
                    while (r.next()) {
                        long albumId = r.getLong(ALBUM_ID);
                        Album.Builder builder = Album.newBuilder()
                                .id(albumId)
                                .title(r.getString(ALBUM_TITLE))
                                .genre(r.getString(ALBUM_GENRE));
                        Set<Track> tracks = albumDBDao.selectTracksByAlbumId(albumId);
                        builder.addAllTrack(tracks);
                        albumSet.add(builder.build());
                    }
                    return albumSet;
                }
        );
    }

    @Override
    public void add(Artist artist) {
        validate(artist);
        commonDataActions(() -> {
            List<Album> albumList = artist.getAlbums();
            insertArtist(artist);
            insertAlbums(albumList);
            List<Track> trackList = albumList.stream()
                    .flatMap(album -> album.getTrackList().stream())
                    .collect(Collectors.toList());
            insertTracks(trackList);
            insertAdjoiningTables(artist, albumList);
        });
    }

    private void validate(Artist artist) {
        try {
            ModelValidator.validate(artist);
        } catch (ModelException e) {
            logger.error("validation error of artist: " + artist.getId() + artist.getName());
            throw new DaoException("validation error", e);
        }
    }

    private void commonDataActions(Procedure procedure) {
        try {
            boolean isAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            procedure.act();
            connection.commit();
            if (isAutoCommit) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error("can't rollback because - " + e1.getMessage());
            }
            logger.error("exception message: " + e.getMessage() +
                    "\n next exception: " + e.getNextException());
            throw new DaoException(e);
        }
    }

    private void insertArtist(Artist artist) throws SQLException {
        new Executor(connection).executeUpdate(SqlQueries.INSERT_ARTIST, s -> {
            s.setLong(1, artist.getId());
            s.setString(2, artist.getName());
        });
    }

    private void insertAdjoiningTables(Artist artist, List<Album> albumList) throws SQLException {
        new Executor(connection).executeBatchUpdate(SqlQueries.INSERT_ADJ_ARTIST_ALBUM, s -> {
            for (Album album : albumList) {
                s.setLong(1, artist.getId());
                s.setLong(2, album.getId());
                s.addBatch();
            }
        });
        new Executor(connection).executeBatchUpdate(SqlQueries.INSERT_ADJ_ALBUM_TRACK, s -> {
            for (Album album : albumList) {
                List<Track> tracks = album.getTrackList();
                for (Track track : tracks) {
                    s.setLong(1, album.getId());
                    s.setLong(2, track.getId());
                    s.addBatch();
                }
            }
        });
    }

    private void insertTracks(List<Track> trackList) throws SQLException {
        new Executor(connection).executeBatchUpdate(SqlQueries.INSERT_TRACK, s -> {
            for (Track track : trackList) {
                s.setLong(1, track.getId());
                s.setString(2, track.getTitle());
                s.setString(3, track.getDuration().toString());
                s.addBatch();
            }
        });
    }

    private void insertAlbums(List<Album> albumList) throws SQLException {
        new Executor(connection).executeBatchUpdate(SqlQueries.INSERT_ALBUM, s -> {
            for (Album album : albumList) {
                s.setLong(1, album.getId());
                s.setString(2, album.getTitle());
                s.setString(3, album.getGenre());
                s.addBatch();
            }
        });
    }

    @Override
    public boolean delete(Artist artist) {
        validate(artist);
        commonDataActions(() -> {
            List<Album> albumList = artist.getAlbums();
            deleteArtist(artist.getId());
            deleteAlbums(albumList.stream().map(Album::getId).collect(Collectors.toList()));
            deleteTracks(albumList.stream()
                    .flatMapToLong(album -> album.getTrackList().stream().mapToLong(Track::getId)));
        });
        return true;
    }

    private void deleteArtist(Long id) throws SQLException {
        new Executor(connection).executeUpdate(
                SqlQueries.DELETE_ARTIST,
                s -> s.setLong(1, id));
    }

    private void deleteAlbums(List<Long> albumsIds) throws SQLException {
        new Executor(connection).executeBatchUpdate(SqlQueries.DELETE_ALBUM, s -> {
            for (Long id : albumsIds) {
                s.setLong(1, id);
                s.addBatch();
            }
        });
    }

    private void deleteTracks(LongStream trackIdStream) throws SQLException {
        List<Long> ids = trackIdStream.boxed().collect(Collectors.toList());
        new Executor(connection).executeBatchUpdate(SqlQueries.DELETE_TRACK, s -> {
            for (Long id : ids) {
                s.setLong(1, id);
                s.addBatch();
            }
        });
    }

    @Override
    public boolean update(Artist artist) {
        validate(artist);
        commonDataActions(() -> {
            List<Album> albumList = artist.getAlbums();
            updateArtist(artist);
            updateAlbums(albumList);
            updateTracks(albumList.stream()
                    .flatMap(album -> album.getTrackList().stream())
                    .collect(Collectors.toList()));
            updateAdjoiningTables(artist, artist.getAlbums());
        });
        return true;
    }

    private void updateAdjoiningTables(Artist artist, List<Album> albumList) throws SQLException {
        new Executor(connection).executeBatchUpdate(SqlQueries.INSERT_WITH_CONFLICT_ADJ_ARTIST_ALBUM, s -> {
            for (Album album : albumList) {
                s.setLong(1, artist.getId());
                s.setLong(2, album.getId());
                s.setLong(3, album.getId());
                s.addBatch();
            }
        });
        new Executor(connection).executeBatchUpdate(SqlQueries.INSERT_WITH_CONFLICT_ADJ_ALBUM_TRACK, s -> {
            for (Album album : albumList) {
                List<Track> tracks = album.getTrackList();
                for (Track track : tracks) {
                    s.setLong(1, album.getId());
                    s.setLong(2, track.getId());
                    s.setLong(3, track.getId());
                    s.addBatch();
                }
            }
        });
    }

    private void updateArtist(Artist artist) throws SQLException {
        new Executor(connection).executeFunction(SqlQueries.CALL_ARTIST_FUNCTION, s -> {
            s.setLong(1, artist.getId());
            s.setString(2, artist.getName());
        });
    }

    private void updateAlbums(List<Album> albumList) throws SQLException {
        new Executor(connection).executeBatchFunction(SqlQueries.CALL_ALBUM_FUNCTION, s -> {
            for (Album album : albumList) {
                s.setLong(1, album.getId());
                s.setString(2, album.getTitle());
                s.setString(3, album.getGenre());
                s.addBatch();
            }
        });
    }

    private void updateTracks(List<Track> albumList) throws SQLException {
        new Executor(connection).executeBatchFunction(SqlQueries.CALL_TRACK_FUNCTION, s -> {
            for (Track track : albumList) {
                s.setLong(1, track.getId());
                s.setString(2, track.getTitle());
                s.setString(3, track.getDuration().toString());
                s.addBatch();
            }
        });
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

}
