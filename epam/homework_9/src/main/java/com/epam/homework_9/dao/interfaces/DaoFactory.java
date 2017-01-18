package com.epam.homework_9.dao.interfaces;

public abstract class DaoFactory {
    public abstract <T> Dao<T> newDao(String outputFile);
}
