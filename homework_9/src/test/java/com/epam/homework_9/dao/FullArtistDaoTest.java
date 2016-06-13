package com.epam.homework_9.dao;

import com.epam.homework_9.dao.exceptions.DaoException;
import com.epam.homework_9.dao.exceptions.ModelException;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.Track;
import com.epam.homework_9.utils.ContentProvider;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public abstract class FullArtistDaoTest {

    private Dao<Artist> dao;
    private long testID = 90L;
    private Artist testArtist;

    //    id_album=100
    public abstract Dao<Artist> getDao();

    @Before
    public void setUpArtist() {
        dao = getDao();
        testArtist = ContentProvider.getTestArtist(testID);
        dao.add(testArtist);
    }

    @Ignore
    @Test
    public void getAll() throws Exception {
        Artist testArtist2 = null;
        try {
            testArtist2 = Artist.newBuilder()
                    .id(101L).name("test101")
                    .addAlbum(ContentProvider.getAddictiveAlbum(testID + 1L))
                    .build();
            dao.add(testArtist2);
            List<Artist> artistList = dao.getAll();
            assertThat(artistList.size(), is(not(0)));
            assertThat(artistList.retainAll(Arrays.asList(testArtist, testArtist2)), is(true));
        } finally {
            dao.delete(testArtist);
            dao.delete(testArtist2);
        }
    }

    @Test
    public void getById() throws Exception {
        Artist addedArtist = null;
        try {
            addedArtist = dao.getById(testID);
            assertThat(testArtist, equalTo(addedArtist));
        } finally {
            dao.delete(addedArtist);
        }
    }

    @Test
    public void add() throws Exception {
        Artist addedArtist = null;
        try {
            addedArtist = dao.getById(testID);
            assertThat(testArtist, is(addedArtist));
            assertThat(testArtist.getAlbums(), everyItem(is(not(nullValue(null)))));
        } finally {
            dao.delete(addedArtist);
        }
    }

    @Test(expected = DaoException.class)
    public void delete() throws Exception {
        dao.delete(testArtist);
        dao.getById(testArtist.getId());
    }

    @Test
    public void update() throws Exception {
        try {
            List<Album> originalAlbums = testArtist.getAlbums().stream().collect(Collectors.toList());
            //forward update
            testArtist.getAlbums().add(ContentProvider.getAddictiveAlbum(testID + 1L));
            assertThat(dao.update(testArtist), is(true));
            Artist addedArtist = dao.getById(testID);
            assertThat(testArtist, is(addedArtist));

            //back update
            testArtist.getAlbums().clear();
            testArtist.getAlbums().addAll(originalAlbums);
            assertThat(dao.update(testArtist), is(true));
        } finally {
            dao.delete(testArtist);
        }
    }


}
