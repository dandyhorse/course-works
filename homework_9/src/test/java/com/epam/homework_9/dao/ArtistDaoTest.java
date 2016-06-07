package com.epam.homework_9.dao;

import com.epam.homework_9.ContentProvider;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import com.epam.homework_9.models.Track;
import com.epam.homework_9.dao.exceptions.ModelException;
import com.epam.homework_9.validators.ModelValidator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ArtistDaoTest {
    private Dao<Artist> dao;
    private static String outputFile;
    private static DaoFactory factory;
    private final String firstArtistName = "Sixto Rodriguez";

    @BeforeClass
    public static void setUpClass() throws Exception {
        MusicGuide guide = new MusicGuide();
        ContentProvider.fillContent(guide);
        outputFile = "src/main/resources/music_guide-test.xml";
        factory = new ArtistXmlFactory();
    }

    @Before
    public void setUp() throws Exception {
        dao = factory.newDao(outputFile);
    }

    @Test
    public void getAll() throws Exception {
        List<Artist> artistList = dao.getAll();
        assertThat(artistList.size(), is(not(0)));
        assertThat(artistList, everyItem(is(not(nullValue(null)))));
    }

    @Test
    public void getById() throws Exception {
        Artist artist = dao.getById(1L);
        assertThat(artist.getName(), is(firstArtistName));
        assertThat(artist.getAlbums(), everyItem(is(not(nullValue(null)))));
    }

    @Test
    public void add() throws Exception {
        Artist artist = getTestArtist();
        dao.add(artist);
        Artist addedArtist = dao.getById(artist.getId());
        assertThat(artist, is(addedArtist));
        dao.delete(addedArtist);
    }

    private Artist getTestArtist() {
        return Artist.newBuilder()
                .id(10L)
                .name("test-artist")
                .addAlbum(Album.newBuilder()
                        .name("test-album")
                        .genre("test-genre")
                        .id(1L)
                        .addTrack(new Track(1L, "test-track", Duration.ofSeconds(1)))
                        .build())
                .build();
    }

    @Test(expected = ModelException.class)
    public void delete() throws Exception {
        Artist artist = getTestArtist();
        dao.add(artist);
        dao.delete(artist);
        dao.getById(artist.getId());
    }

    @Test
    public void update() throws Exception {
        long sameId = 1L;
        Artist artist = dao.getById(sameId);
        List<Album> originalAlbums = artist.getAlbums().stream().collect(Collectors.toList());
        artist.getAlbums().add(ContentProvider.getAddictiveAlbum());
        assertThat(dao.update(artist), is(true));

        Artist addedArtist = dao.getById(sameId);
        assertThat(artist, is(addedArtist));

        //comeback and check again
        artist.getAlbums().clear();
        artist.getAlbums().addAll(originalAlbums);
        ModelValidator.validate(artist);
        assertThat(dao.update(artist), is(true));
    }
}