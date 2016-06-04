package com.epam.homework_9.dao;


import com.epam.homework_9.dao.interfaces.AbstractDaoFactory;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Artist;

public class ArtistXmlFactory extends AbstractDaoFactory {

    @Override
    public Dao<Artist> newDao(String outputFile) {
        //TODO собираем доступ к xml
        return new ArtistDao(outputFile);

    }

}
