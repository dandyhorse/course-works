package com.epam.homework_9.dao;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.dao.xml.saxhandlers.ArtistHandler;
import com.epam.homework_9.dao.xml.saxhandlers.CountArtistsHandler;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import com.epam.homework_9.models.Track;
import com.epam.homework_9.validators.ModelValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArtistDao implements Dao<Artist> {

    private static final Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private final String xmlPath;
    private SAXParser parser;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public ArtistDao(String xmlPath, SAXParserFactory factory) {
        logger.info("initialization - " + ArtistDao.class);
        this.xmlPath = xmlPath;
        try {
            parser = factory.newSAXParser();
            logger.debug("start init JAXB");
            JAXBContext context = JAXBContext.newInstance(MusicGuide.class, Artist.class, Album.class, Track.class);
            initMarshaller(context);
            initUnmarshaller(context);
            logger.debug("successful initialization");
        } catch (ParserConfigurationException | SAXException | JAXBException e) {
            logger.error("unsuccessful initialization - " + e.getMessage());
            throw new DaoException("init parser fallen at the beginning", e);
        }
    }

    private void initUnmarshaller(JAXBContext context) throws JAXBException {
        unmarshaller = context.createUnmarshaller();

    }

    private void initMarshaller(JAXBContext context) throws JAXBException {
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public List<Artist> getAll() {
        logger.debug("getAll method");
        CountArtistsHandler countHandler = new CountArtistsHandler();
        parse(countHandler);
        Long count = countHandler.getCount();
        List<Artist> artists = new ArrayList<>();
        for (long i = 1; i <= count; i++) {
            artists.add(getById(i));
        }
        return artists;
    }

    @Override
    public Artist getById(Long id) {
        logger.debug("getAll method");
        ArtistHandler handler = new ArtistHandler(id);
        parse(handler);
        Artist artist = handler.get();
        ModelValidator.validate(artist);
        return artist;
    }

    private void parse(DefaultHandler handler) {
        try {
            parser.parse(xmlPath, handler);
        } catch (SAXException | IOException e) {
            logger.error("parsing was fallen" + e.getMessage());
            throw new DaoException("parsing lol", e);
        }
    }

    @Override
    public void add(Artist artist) {
        ModelValidator.validate(artist);
        marshalling(musicGuide -> musicGuide.addArtist(artist));
    }

    public void marshalling(Consumer<MusicGuide> operator) {
        try {
            MusicGuide guide = (MusicGuide) unmarshaller.unmarshal(new FileReader(xmlPath));
            operator.accept(guide);
            marshaller.marshal(guide, new FileWriter(xmlPath));
        } catch (JAXBException | IOException e) {
            logger.error("marshalling was fallen " + e.getMessage());
            throw new DaoException("marshalling lol", e);
        }
    }

    @Override
    public boolean delete(Artist artist) {
        if (checkArtist(artist)) {
            marshalling(musicGuide -> musicGuide.getAllArtists().remove(artist));
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Artist artist) {
        ModelValidator.validate(artist);
        Artist artistFromXml = getById(artist.getId());
        marshalling(musicGuide -> {
            int i = musicGuide.getAllArtists().indexOf(artistFromXml);
            musicGuide.getAllArtists().add(i, artist);
        });
        return true;
    }

    private boolean checkArtist(Artist artist) {
        ModelValidator.validate(artist);
        Long id = artist.getId();
        Artist artistInXml = getById(id);
        return !artistInXml.equals(artist);
    }

}
