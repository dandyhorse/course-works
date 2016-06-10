package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.utils.ContentProvider;
import com.epam.homework_9.dao.AbstractArtistDaoTest;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.MusicGuide;
import org.junit.BeforeClass;


public class ArtistDBDaoTest extends AbstractArtistDaoTest {

    private static final String propertyPath = "src/main/resources/db.properties";
    private static DaoFactory dbFactory = new ArtistPostgresFactory();

    @BeforeClass
    public static void setUpClass() throws Exception {
        MusicGuide guide = new MusicGuide();
        ContentProvider.fillContent(guide);
    }

    @Override
    public Dao<Artist> getDao() {
        return dbFactory.newDao(propertyPath);
    }
}