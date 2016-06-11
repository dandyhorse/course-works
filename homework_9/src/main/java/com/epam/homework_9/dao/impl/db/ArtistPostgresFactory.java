package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ArtistPostgresFactory extends DaoFactory {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");

    static {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            logger.error("postgres driver exc");
            throw new DaoException("", e);
        }
    }

    @Override
    public Dao<Artist> newDao(String propertyPath) {
        Connection connection = getPostgresDBConnection(propertyPath);
        return new ArtistDBDao(connection);
    }

    public Connection getPostgresDBConnection(String config) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(config));
        } catch (NullPointerException | IOException e) {
            logger.warn("Generate connection with default properties. " + e.getMessage());
        }
        try {
            String url = properties.getProperty("url", "jdbc:postgresql://localhost:5555/guide_db");
            String name = properties.getProperty("name", "postgres");
            String pass = properties.getProperty("password", "555");
            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            logger.error("sql exc");
            throw new DaoException("", e);
        }
    }

}
