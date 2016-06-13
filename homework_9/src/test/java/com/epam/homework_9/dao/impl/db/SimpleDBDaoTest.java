package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.Track;
import com.epam.homework_9.utils.ContentProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.time.Duration;

import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SimpleDBDaoTest {
    private static Connection connection;

    private Dao<Album> albumDao;
    private Dao<Artist> artistDao;
    private static final long testId = 100L;
    private Artist testArtist;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ArtistPostgresFactory factory = new ArtistPostgresFactory();
        connection = factory.getPostgresDBConnection("src/main/resources/db.properties");
    }

    @Before
    public void setUp() throws Exception {
        testArtist = ContentProvider.getTestArtist(testId);
        albumDao = new AlbumDBDao(connection);
        artistDao = new ArtistDBDao(connection);
    }

    @Ignore
    @Test
    public void getAll() throws Exception {

    }


}