package com.epam.homework_9.dao.interfaces;

public interface Migration {

    <T> void migrate(Dao<T> fromDao, Dao<T> toDao);

}
