package com.epam.homework_9.dao;

import com.epam.homework_9.models.utils.ContentProvider;
import com.epam.homework_9.dao.exceptions.ModelException;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.Track;
import org.junit.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class AbstractArtistDaoTest {

    protected Dao<Artist> dao;
    private long testID = 100L;

    @Test
    public void getAll() throws Exception {
        List<Artist> artistList = dao.getAll();
        assertThat(artistList.size(), is(not(0)));
        assertThat(artistList, everyItem(is(not(nullValue(null)))));
    }

    @Test
    public void getById() throws Exception {
        Artist artist = getTestArtist();
        dao.add(artist);
        Artist addedArtist = dao.getById(testID);
        assertThat(artist.getName(), is(addedArtist.getName()));
        dao.delete(addedArtist);
    }

    @Test
    public void add() throws Exception {
        Artist artist = getTestArtist();
        dao.add(artist);
        Artist addedArtist = dao.getById(testID);
        assertThat(artist, is(addedArtist));
        assertThat(artist.getAlbums(), everyItem(is(not(nullValue(null)))));
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
        Artist artist = getTestArtist();
        dao.add(artist);
        dao.delete(artist);
        dao.getById(artist.getId());
    }

    @Test
    public void update() throws Exception {
        Artist artist = getTestArtist();
        dao.add(artist);

        List<Album> originalAlbums = artist.getAlbums().stream().collect(Collectors.toList());
        //forward update
        artist.getAlbums().add(ContentProvider.getAddictiveAlbum());
        assertThat(dao.update(artist), is(true));
        Artist addedArtist = dao.getById(testID);
        assertThat(artist, is(addedArtist));

        //back update
        artist.getAlbums().clear();
        artist.getAlbums().addAll(originalAlbums);
        assertThat(dao.update(artist), is(true));

        dao.delete(artist);
    }
}
