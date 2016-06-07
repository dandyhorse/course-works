package com.epam.homework_9.validators;

import com.epam.homework_9.ContentProvider;
import com.epam.homework_9.dao.exceptions.ModelException;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelValidatorTest {

    @Test
    public void fullValidate() throws Exception {
        MusicGuide musicGuide = new MusicGuide();
        ContentProvider.fillContent(musicGuide);
        ModelValidator.validate(musicGuide);
    }

    @Test(expected = ModelException.class)
    public void errValidate() throws Exception {
        MusicGuide musicGuide = new MusicGuide();
        musicGuide.addArtist(Artist.newBuilder()
                .name("123")
                .addAlbum(Album.newBuilder()
                        .name("123")
                        .build())
                .build());
        ModelValidator.validate(musicGuide);
    }
}