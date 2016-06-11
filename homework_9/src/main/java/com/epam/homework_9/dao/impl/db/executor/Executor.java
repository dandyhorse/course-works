package com.epam.homework_9.dao.impl.db.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Executor {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(String query, PrepareOperator<PreparedStatement> operator) throws SQLException {
        logger.debug("executing " + query);
        PreparedStatement pStatement = connection.prepareStatement(query);
        operator.prepare(pStatement);
        pStatement.executeUpdate();
        pStatement.close();
    }

    public <T> T executeQuery(String query, PrepareOperator<PreparedStatement> operator, ResultHandler<T> handler) throws SQLException {
        logger.debug("start executing " + query);
        PreparedStatement pStatement = connection.prepareStatement(query);
        operator.prepare(pStatement);
        pStatement.execute();
        ResultSet result = pStatement.getResultSet();
        T value = handler.apply(result);
        result.close();
        pStatement.close();
        return value;
    }
}
