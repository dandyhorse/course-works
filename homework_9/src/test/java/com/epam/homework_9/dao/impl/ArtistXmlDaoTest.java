package com.epam.homework_9.dao.impl;

import com.epam.homework_9.models.utils.ContentProvider;
import com.epam.homework_9.dao.AbstractArtistDaoTest;
import com.epam.homework_9.dao.impl.xml.ArtistXmlFactory;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.MusicGuide;
import org.junit.Before;
import org.junit.BeforeClass;

public class ArtistXmlDaoTest extends AbstractArtistDaoTest {

    private static String outputFile;
    private static DaoFactory factory;

    @BeforeClass
    public static void setUpClass() throws Exception {
        MusicGuide guide = new MusicGuide();
        ContentProvider.fillContent(guide);
        outputFile = "src/main/resources/music_guide-test.xml";
        factory = new ArtistXmlFactory();

    }

    @Before
    public void setUp() throws Exception {
        dao = factory.newDao(outputFile);
    }

}