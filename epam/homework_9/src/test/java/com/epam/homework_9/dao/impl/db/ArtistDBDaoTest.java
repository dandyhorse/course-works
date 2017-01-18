package com.epam.homework_9.dao.impl.db;

import com.epam.homework_9.dao.FullArtistDaoTest;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.Artist;
import org.junit.BeforeClass;

public class ArtistDBDaoTest extends FullArtistDaoTest {

    private static final String propertyPath = "src/main/resources/db.properties";
    private static DaoFactory dbFactory;

    @BeforeClass
    public static void setUpClass() throws Exception {
        dbFactory = new ArtistPostgresFactory();
    }

    @Override
    public Dao<Artist> getDao() {
        return dbFactory.newDao(propertyPath);
    }
}