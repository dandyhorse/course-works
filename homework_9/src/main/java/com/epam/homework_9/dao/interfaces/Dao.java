package com.epam.homework_9.dao.interfaces;

import java.util.List;

public interface Dao<T> {

    List<T> getAll();

    T getById(Long id);

    void add(T t);

    boolean delete(T t);

    boolean update(T t);

}
