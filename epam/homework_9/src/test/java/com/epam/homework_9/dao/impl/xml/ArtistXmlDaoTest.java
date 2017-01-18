package com.epam.homework_9.dao.impl.xml;

import com.epam.homework_9.dao.FullArtistDaoTest;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.Artist;
import org.junit.BeforeClass;

public class ArtistXmlDaoTest extends FullArtistDaoTest {

    private static final String outputFile = "src/main/resources/music_guide-test.xml";
    private static DaoFactory xmlFactory;

    @BeforeClass
    public static void setUpClass() throws Exception {
        xmlFactory = new ArtistXmlFactory();
    }

    @Override
    public Dao<Artist> getDao() {
        return xmlFactory.newDao(outputFile);
    }
}