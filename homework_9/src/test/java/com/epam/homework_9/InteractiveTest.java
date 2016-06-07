package com.epam.homework_9;

import com.epam.homework_9.dao.ArtistXmlFactory;
import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.dao.interfaces.DaoFactory;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import org.junit.Test;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InteractiveTest {

    @Test
    public void getAllTrackTime() throws Exception {
        MusicGuide guide = new MusicGuide();
        ContentProvider.fillContent(guide);
        String outputFile = "src/main/resources/music_guide-test.xml";
        DaoFactory factory = new ArtistXmlFactory();
        Dao<Artist> dao = factory.newDao(outputFile);
        Interactive interactive = new Interactive(dao);
        Long time = interactive.getAllTrackTime(1L);

        Duration duration = dao.getById(1L).getAlbums().get(0).getTrackList().get(0).getDuration();
        long seconds = duration.getSeconds();

        assertThat(time, is(seconds));
    }
}