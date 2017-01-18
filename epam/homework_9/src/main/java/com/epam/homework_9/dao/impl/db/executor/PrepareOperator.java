package com.epam.homework_9.dao.impl.db.executor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PrepareOperator<T extends PreparedStatement> {

    void prepare(T statement) throws SQLException;

}
