package com.epam.homework_9.dao.interfaces;

import com.epam.homework_9.dao.interfaces.Dao;

public interface Migration {

    <T> void migrate(Dao<T> fromDao, Dao<T> toDao);

}
