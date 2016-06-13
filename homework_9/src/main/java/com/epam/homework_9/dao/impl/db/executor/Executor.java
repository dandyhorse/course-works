package com.epam.homework_9.dao.impl.db.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

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

    public void executeBatchUpdate(String query, PrepareOperator<PreparedStatement> operator) throws SQLException {
        logger.debug("butch executing " + query);
        PreparedStatement pStatement = connection.prepareStatement(query);
        operator.prepare(pStatement);
        pStatement.executeBatch();
        pStatement.close();
    }

    public void executeBatchFunction(String query, PrepareOperator<PreparedStatement> operator) throws SQLException {
        logger.debug("function executing " + query);
        CallableStatement callableStatement = connection.prepareCall(query);
        operator.prepare(callableStatement);
        callableStatement.executeBatch();
        callableStatement.close();
    }

    public void executeFunction(String query, PrepareOperator<PreparedStatement> operator) throws SQLException {
        logger.debug("function executing " + query);
        CallableStatement callableStatement = connection.prepareCall(query);
        operator.prepare(callableStatement);
        callableStatement.executeUpdate();
        callableStatement.close();
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

    public void executeQuery(String query) throws SQLException {
        logger.debug("start executing " + query);
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();
    }
}
