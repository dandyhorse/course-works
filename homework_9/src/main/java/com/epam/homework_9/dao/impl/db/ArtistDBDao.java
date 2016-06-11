package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.impl.db.executor.Executor;
import com.epam.homework_9.dao.impl.db.utils.SqlQueries;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.epam.homework_9.dao.impl.db.utils.SqlColumns.*;

public class ArtistDBDao implements Dao<Artist>, AutoCloseable {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Connection connection;
    private AlbumDBDao albumDBDao;

    public ArtistDBDao(Connection connection) {
        this.connection = connection;
        albumDBDao = new AlbumDBDao(connection);
    }

    @Override
    public List<Artist> getAll() {
        return null;
    }

    @Override
    public Artist getById(Long id) {
        try {
            return new Executor(connection).executeQuery(
                    SqlQueries.SELECT_ARTIST_BY_ID,
                    pStatement -> pStatement.setLong(1, id),
                    result -> processResultArtistSet(id, result));
        } catch (SQLException e) {
            logger.error(String.format("%s, %s", e.getMessage(), e.getSQLState()));
            throw new DaoException("", e);
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
                        Album.Builder builder = Album.newBuilder()
                                .id(r.getLong(ALBUM_ID))

                                .title(r.getString(ALBUM_TITLE))
                                .genre(r.getString(ALBUM_GENRE));
                        Set<Track> tracks = albumDBDao.selectTracksByAlbumId(id);
                        builder.addAllTrack(tracks);
                        albumSet.add(builder.build());
                    }
                    return albumSet;
                }
        );
    }

    @Override
    public void add(Artist artist) {
//        try {
//            connection.setAutoCommit(false);
//            new Executor(connection).executeUpdate(
//                    SqlQueries.INSERT_ARTIST,
//                    statement -> {
//                        statement.setLong(1, artist.getId());
//                        statement.setString(2, artist.getTitle());
//                    });
//            connection.setAutoCommit(true);
//        } catch (SQLException e) {
//            logger.error("");
//            throw new DaoException("", e);
//        }
    }

    @Override
    public boolean delete(Artist artist) {
        return true;
    }

    @Override
    public boolean update(Artist artist) {
        return true;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

}
