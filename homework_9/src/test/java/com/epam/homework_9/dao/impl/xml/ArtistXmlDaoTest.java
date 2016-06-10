package com.epam.homework_9.dao.impl.xml;

import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.utils.ContentProvider;
import com.epam.homework_9.dao.AbstractArtistDaoTest;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.MusicGuide;
import org.junit.Before;
import org.junit.BeforeClass;

public class ArtistXmlDaoTest extends AbstractArtistDaoTest {

    private static final String outputFile = "src/main/resources/music_guide-test.xml";
    private static DaoFactory xmlFactory = new ArtistXmlFactory();

    @BeforeClass
    public static void setUpClass() throws Exception {
        MusicGuide guide = new MusicGuide();
        ContentProvider.fillContent(guide);
    }

    @Override
    public Dao<Artist> getDao() {
        return xmlFactory.newDao(outputFile);
    }
}