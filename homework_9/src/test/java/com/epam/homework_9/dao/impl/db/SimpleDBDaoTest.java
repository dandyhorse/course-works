package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.Track;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SimpleDBDaoTest {
    //TODO не забыть поменять null на путь к конфигу при релизе.
    private static Connection connection;

    private Dao<Track> trackDao;
    private Dao<Album> albumDao;
    private Dao<Artist> artistDao;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ArtistPostgresFactory factory = new ArtistPostgresFactory();
        connection = factory.getPostgresDBConnection("src/main/resources/db.properties");
    }

    @Before
    public void setUp() throws Exception {
        albumDao = new AlbumDBDao(connection);
        artistDao = new ArtistDBDao(connection);
        trackDao = new TrackDBDao(connection);
    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void getAlbumById() throws Exception {
        //TODO before add
        Album album = albumDao.getById(1L);
        assertThat(album, is(notNullValue(Album.class)));
        System.out.println(album);
        //TODO after delete
    }

    @Ignore
    @Test
    public void addAlbum() throws Exception {
        Album album = Album.newBuilder()
                .id(100L)
                .title("test-title")
                .genre("test-genre")
                .addTrack(new Track(10L, "test-track", Duration.ofMinutes(1)))
                .build();
        albumDao.add(album);
        Album addedAlbum = albumDao.getById(album.getId());
        assertThat(album, is(addedAlbum));
        //TODO after delete
    }

    @Test
    public void getArtistById() throws Exception {
        //TODO before add
        Artist artist = artistDao.getById(1L);
        assertThat(artist, is(notNullValue(Artist.class)));
        System.out.println(artist);
        //TODO after delete
    }

    @Test
    public void addTrack() throws Exception {
        Track track = new Track(12L, "fuck", Duration.ofMinutes(12));
        trackDao.add(track);
        Track addedTrack = trackDao.getById(12L);
        assertThat(track, is(addedTrack));
        //TODO after delete
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }


}