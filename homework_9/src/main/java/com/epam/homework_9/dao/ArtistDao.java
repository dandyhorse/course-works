package com.epam.homework_9.dao;

import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Artist;

import java.util.List;

public class ArtistDao implements Dao<Artist> {

    public ArtistDao() {

    }

    public ArtistDao(String outputFile) {

    }

    @Override
    public List<Artist> getAll() {
        return null;
    }

    @Override
    public Artist getById(Long id) {
        return null;
    }

    @Override
    public void add(Artist artist) {

    }

    @Override
    public void delete(Artist artist) {

    }

    @Override
    public void update(Artist artist) {

    }

}
