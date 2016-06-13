package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.impl.db.executor.Executor;
import com.epam.homework_9.dao.impl.db.utils.SqlQueries;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.epam.homework_9.dao.impl.db.utils.SqlColumns.*;

class AlbumDBDao implements Dao<Album> {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Connection connection;

    AlbumDBDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Album> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Album getById(Long id) {
        try {
            return new Executor(connection).executeQuery(
                    SqlQueries.SELECT_ALBUM_BY_ID,
                    s -> s.setLong(1, id),
                    r -> processAlbumResultSet(id, r)
            );
        } catch (SQLException e) {
            logger.error(String.format("%s, %s", e.getMessage(), e.getSQLState()));
            throw new DaoException("", e);
        }
    }

    private Album processAlbumResultSet(Long id, ResultSet result) throws SQLException {
        result.next();
        Album.Builder builder = Album.newBuilder().id(id);
        builder.title(result.getString(ALBUM_TITLE))
                .genre(result.getString(ALBUM_GENRE));
        Set<Track> tracks = selectTracksByAlbumId(id);
        builder.addAllTrack(tracks);
        return builder.build();
    }

    Set<Track> selectTracksByAlbumId(Long id) throws SQLException {
        return new Executor(connection).executeQuery(
                SqlQueries.SELECT_TRACKS_FROM_ALBUM,
                s -> s.setLong(1, id),
                r -> {
                    Set<Track> trackSet = new HashSet<>();
                    while (r.next()) {
                        Track track = new Track(
                                r.getLong(TRACK_ID),
                                r.getString(TRACK_TITLE),
                                Duration.parse(r.getString(TRACK_DURATION)));
                        trackSet.add(track);
                    }
                    return trackSet;
                }
        );
    }

    @Override
    public void add(Album album) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Album album) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Album album) {
        throw new UnsupportedOperationException();
    }
}
