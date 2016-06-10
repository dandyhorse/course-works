package com.epam.homework_9.dao.impl.db.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Executor {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(String query, UnaryOperator<PreparedStatement> function) throws SQLException {
        logger.debug("executing " + query);
        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement preparedStatement = function.apply(statement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

//    public <T> T executeQuery(PreparedStatement pStatement, Function<ResultSet, T> handler) throws SQLException {
//        logger.debug("executing " + pStatement);
//        pStatement.execute();
//        ResultSet result = pStatement.getResultSet();
//        T value = handler.apply(result);
//        result.close();
//        pStatement.close();
//        return value;
//    }

    public <T> T executeQuery(String query, UnaryOperator<PreparedStatement> function, Function<ResultSet, T> handler) throws SQLException {
        logger.debug("start executing " + query);
        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement preparedStatement = function.apply(statement);
        preparedStatement.execute();
        ResultSet result = preparedStatement.getResultSet();
        T value = handler.apply(result);
        result.close();
        preparedStatement.close();
        return value;
    }
}
