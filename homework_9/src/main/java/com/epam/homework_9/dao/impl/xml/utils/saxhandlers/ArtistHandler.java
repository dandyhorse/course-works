package com.epam.homework_9.dao.impl.xml.utils.saxhandlers;

import com.epam.homework_9.dao.impl.xml.utils.Tags;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtistHandler extends DefaultHandler {

    private Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");

    private final Long idForFind;
    private String artistName;
    private Artist artist;
    private boolean isArtist = false;

    private HashMap<Integer, Album.Builder> albums = new HashMap<>();
    private int albumCount = 0;

    private List<Album> albumList = new ArrayList<>();

    public Artist get() {
        return artist;
    }

    public ArtistHandler(Long id) {
        this.idForFind = id;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case Tags.PREFIX + ":" + Tags.ARTIST_TAG: {
                if (attributes.getValue(Tags.ARTIST_ID).trim().equals(String.valueOf(idForFind))) {
                    artistName = attributes.getValue(Tags.ARTIST_NAME);
                    isArtist = true;
                } else {
                    isArtist = false;
                }
                break;
            }
            case Tags.PREFIX + ":" + Tags.ALBUM_TAG: {
                if (isArtist) {
                    String id = attributes.getValue(Tags.ALBUM_ID);
                    String name = attributes.getValue(Tags.ALBUM_TITLE);
                    String genre = attributes.getValue(Tags.ALBUM_GENRE);
                    albums.put(albumCount, Album.newBuilder()
                            .genre(genre)
                            .title(name)
                            .id(Long.parseLong(id)));
                }
                break;
            }
            case Tags.PREFIX + ":" + Tags.TRACK_TAG: {
                if (isArtist) {
                    String id = attributes.getValue(Tags.TRACK_ID);
                    String name = attributes.getValue(Tags.TRACK_TITLE);
                    String duration = attributes.getValue(Tags.TRACK_DURATION);
                    albums.get(albumCount).addTrack(new Track(Long.parseLong(id), name, Duration.parse(duration)));
                }
                break;
            }
        }
        logger.trace("start " + qName + " " + localName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case Tags.PREFIX + ":" + Tags.ALBUM_TAG: {
                if (isArtist) {
                    Album album = albums.get(albumCount++).build();
                    albumList.add(album);
                }
            }
        }
        logger.trace("end of " + qName + " " + localName);
    }

    @Override
    public void endDocument() throws SAXException {
        artist = Artist.newBuilder().name(artistName).id(idForFind).addAllAlbum(albumList).build();
        logger.debug("xml is parsed, creating " + Artist.class);
    }
}
