package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.impl.db.executor.Executor;
import com.epam.homework_9.dao.impl.db.utils.SqlQueries;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import static com.epam.homework_9.dao.impl.db.utils.SqlColumns.TRACK_DURATION;
import static com.epam.homework_9.dao.impl.db.utils.SqlColumns.TRACK_TITLE;

class TrackDBDao implements Dao<Track> {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Connection connection;

    TrackDBDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Track> getAll() {
        return null;
    }

    @Override
    public Track getById(Long id) {
        try {
            return new Executor(connection).executeQuery(
                    SqlQueries.SELECT_TRACK_BY_ID,
                    statement -> statement.setLong(1, id),
                    result -> {
                        result.next();
                        String title = result.getString(TRACK_TITLE);
                        String duration = result.getString(TRACK_DURATION);
                        return new Track(id, title, Duration.parse(duration));
                    });
        } catch (SQLException e) {
            logger.error(String.format("%s, %s", e.getMessage(), e.getSQLState()));
            throw new DaoException("", e);
        }
    }

    @Override
    public void add(Track track) {
        try {
            new Executor(connection).executeUpdate(SqlQueries.INSERT_TRACK, s -> {
                s.setLong(1, track.getId());
                s.setString(2, track.getTitle());
                s.setString(3, track.getDuration().toString());
            });
        } catch (SQLException e) {
            logger.error("" + e.getMessage());
            throw new DaoException("", e);
        }
    }

    @Override
    public boolean delete(Track track) {
        return false;
    }

    @Override
    public boolean update(Track track) {
        return false;
    }
}
