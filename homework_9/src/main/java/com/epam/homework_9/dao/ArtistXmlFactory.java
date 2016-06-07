package com.epam.homework_9.dao;

import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Artist;

import javax.xml.parsers.SAXParserFactory;


public class ArtistXmlFactory extends DaoFactory {

    private SAXParserFactory factory = SAXParserFactory.newInstance();

    @Override
    public Dao<Artist> newDao(String outputFile) {
        return new ArtistDao(outputFile, factory);
    }

}
