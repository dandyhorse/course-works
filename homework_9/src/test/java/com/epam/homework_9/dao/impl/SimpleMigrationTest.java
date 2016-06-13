package com.epam.homework_9.dao.impl;

import com.epam.homework_9.dao.impl.db.ArtistPostgresFactory;
import com.epam.homework_9.dao.impl.xml.ArtistXmlFactory;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.dao.interfaces.Migration;
import com.epam.homework_9.models.Artist;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SimpleMigrationTest {

    private static DaoFactory xmlFactory;
    private static DaoFactory dbFactory;
    private Dao<Artist> xmlDao;
    private Dao<Artist> dbDao;

    @BeforeClass
    public static void setUpClass() throws Exception {
        xmlFactory = new ArtistXmlFactory();
        dbFactory = new ArtistPostgresFactory();
    }

    @Before
    public void setUp() throws Exception {
        xmlDao = xmlFactory.newDao("src/main/resources/music_guide-test.xml");
        dbDao = dbFactory.newDao("src/main/resources/db.properties");
    }

    @Test
    public void migrate() throws Exception {
        Migration migration = new SimpleMigration();
        migration.migrate(xmlDao, dbDao);
        assertThat(xmlDao.getAll(), equalTo(dbDao.getAll()));
    }
}