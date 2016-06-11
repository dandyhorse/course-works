package com.epam.homework_9.dao.impl.db.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {

    T apply(ResultSet result) throws SQLException;
}
