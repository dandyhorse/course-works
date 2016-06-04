package com.epam.homework_9.dao.interfaces;

public abstract class AbstractDaoFactory {
    public abstract <T> Dao<T> newDao(String outputFile);
}
