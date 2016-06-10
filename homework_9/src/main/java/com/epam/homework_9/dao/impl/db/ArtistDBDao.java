package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.impl.db.utils.Executor;
import com.epam.homework_9.dao.impl.db.utils.SqlQueries;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ArtistDBDao implements Dao<Artist>, AutoCloseable {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Connection connection;

    public ArtistDBDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Artist> getAll() {
        try {
            return new Executor(connection).executeQuery(SqlQueries.SELECT_ALL_ARTISTS, new UnaryOperator<PreparedStatement>() {
                @Override
                public PreparedStatement apply(PreparedStatement preparedStatement) {
                    return null;
                }
            }, new Function<ResultSet, List<Artist>>() {
                @Override
                public List<Artist> apply(ResultSet resultSet) {
                    return null;
                }
            });
        } catch (SQLException e) {
            logger.error("");
            throw new DaoException("", e);
        }
    }

    @Override
    public Artist getById(Long id) {
        try {
            return new Executor(connection).executeQuery(SqlQueries.SELECT_ARTIST_BY_ID, new UnaryOperator<PreparedStatement>() {
                @Override
                public PreparedStatement apply(PreparedStatement preparedStatement) {
                    return null;
                }
            }, new Function<ResultSet, Artist>() {
                @Override
                public Artist apply(ResultSet resultSet) {
                    return null;
                }
            });
        } catch (SQLException e) {
            logger.error("");
            throw new DaoException("", e);
        }
    }

    @Override
    public void add(Artist artist) {
        try {
            connection.setAutoCommit(false);
            new Executor(connection).executeUpdate(SqlQueries.INSERT_ARTIST, new UnaryOperator<PreparedStatement>() {
                @Override
                public PreparedStatement apply(PreparedStatement statement) {
                    try {
                        statement.setLong(1, artist.getId());
                        statement.setString(2, artist.getName());
                    } catch (SQLException e) {
                        logger.error("");
                        throw new DaoException("", e);
                    }
                    return null;
                }
            });
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("");
            throw new DaoException("", e);
        }
    }

    @Override
    public boolean delete(Artist artist) {
        try {
            connection.setAutoCommit(false);
            new Executor(connection).executeUpdate(SqlQueries.DELETE_ARTIST, new UnaryOperator<PreparedStatement>() {
                @Override
                public PreparedStatement apply(PreparedStatement statement) {
                    try {
                        statement.setString(1, "");
                    } catch (SQLException e) {
                        logger.error("");
                        throw new DaoException("", e);
                    }
                    return null;
                }
            });
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("");
            throw new DaoException("", e);
        }
        return true;
    }

    @Override
    public boolean update(Artist artist) {
        try {
            connection.setAutoCommit(false);
            new Executor(connection).executeUpdate(SqlQueries.UPDATE_ARTIST, new UnaryOperator<PreparedStatement>() {
                @Override
                public PreparedStatement apply(PreparedStatement statement) {
                    try {
                        statement.setString(1, "");
                    } catch (SQLException e) {
                        logger.error("");
                        throw new DaoException("", e);
                    }
                    return null;
                }
            });
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("");
            throw new DaoException("", e);
        }
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
