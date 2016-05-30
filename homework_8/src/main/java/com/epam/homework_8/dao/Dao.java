package com.epam.homework_8.dao;

public interface Dao<T> {

    void save(T t);

    T get();
}
