package com.epam.homework_9.dao;

import com.epam.homework_9.models.utils.ContentProvider;
import com.epam.homework_9.dao.exceptions.ModelException;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.Track;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


public abstract class AbstractArtistDaoTest {

    private Dao<Artist> dao;
    private long testID = 100L;
    private Artist testArtist;

    public abstract Dao<Artist> getDao();

    @Before
    public void setUpArtist() {
        dao = getDao();
        testArtist = getTestArtist();
        dao.add(testArtist);
    }

    @Test
    public void getAll() throws Exception {
        Artist testArtist2 = Artist.newBuilder().id(101L).name("test101").addAlbum(ContentProvider.getAddictiveAlbum()).build();

        dao.add(testArtist2);
        List<Artist> artistList = dao.getAll();
        assertThat(artistList.size(), is(not(0)));
        assertThat(artistList.retainAll(Arrays.asList(testArtist, testArtist2)), is(true));
        dao.delete(testArtist);
        dao.delete(testArtist2);
    }

    @Test
    public void getById() throws Exception {
        Artist addedArtist = dao.getById(testID);
        assertThat(testArtist, equalTo(addedArtist));
        dao.delete(addedArtist);
    }

    @Test
    public void add() throws Exception {
        Artist addedArtist = dao.getById(testID);
        assertThat(testArtist, is(addedArtist));
        assertThat(testArtist.getAlbums(), everyItem(is(not(nullValue(null)))));
        dao.delete(addedArtist);
    }

    private Artist getTestArtist() {
        return Artist.newBuilder()
                .id(testID)
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
        dao.delete(testArtist);
        dao.getById(testArtist.getId());
    }

    @Test
    public void update() throws Exception {
        List<Album> originalAlbums = testArtist.getAlbums().stream().collect(Collectors.toList());
        //forward update
        testArtist.getAlbums().add(ContentProvider.getAddictiveAlbum());
        assertThat(dao.update(testArtist), is(true));
        Artist addedArtist = dao.getById(testID);
        assertThat(testArtist, is(addedArtist));

        //back update
        testArtist.getAlbums().clear();
        testArtist.getAlbums().addAll(originalAlbums);
        assertThat(dao.update(testArtist), is(true));

        dao.delete(testArtist);
    }


}
