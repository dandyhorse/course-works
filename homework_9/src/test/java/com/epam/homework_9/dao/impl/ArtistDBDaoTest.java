package com.epam.homework_9.dao.impl;

import com.epam.homework_9.models.utils.ContentProvider;
import com.epam.homework_9.dao.AbstractArtistDaoTest;
import com.epam.homework_9.dao.impl.db.ArtistPostgresFactory;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.MusicGuide;
import org.junit.Before;
import org.junit.BeforeClass;


public class ArtistDBDaoTest extends AbstractArtistDaoTest {

    private static String propertyPath;
    private static DaoFactory factory;

    @BeforeClass
    public static void setUpClass() throws Exception {
        MusicGuide guide = new MusicGuide();
        ContentProvider.fillContent(guide);
        propertyPath = "src/main/resources/db.properties";
        factory = new ArtistPostgresFactory();

    }

    @Before
    public void setUp() throws Exception {
        dao = factory.newDao(propertyPath);
    }

}