package com.epam.homework_8.validators;


import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;

public class Validator {

    public static boolean validate(MusicGuide musicGuide) {
        return musicGuide.getAllArtists().size() > 0;
    }

    public static boolean validate(Artist artist) {
        boolean b = artist.getName() != null && !artist.getName().equals("");
        b &= artist.getAlbums().size() > 0;
        return b;
    }

}
