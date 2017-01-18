package com.epam.homework_9.dao.impl;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.dao.interfaces.Migration;

import java.util.List;

public class SimpleMigration implements Migration {

    @Override
    public <T> void migrate(Dao<T> fromDao, Dao<T> toDao) {
        if (fromDao != toDao) {
            List<T> list = fromDao.getAll();
            list.forEach(toDao::add);
        } else {
            throw new DaoException("both dao's link to the same dao");
        }
    }

}
