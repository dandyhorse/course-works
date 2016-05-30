package com.epam.homework_8.validators;


import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.models.Track;

import java.time.Duration;
import java.util.List;

public class Validator {

    public static boolean validate(MusicGuide musicGuide) {
        List<Artist> allArtists = musicGuide.getAllArtists();
        return allArtists != null && allArtists.size() > 0;
    }

    public static boolean validate(Artist artist) {
        String name = artist.getName();
        boolean result = name != null && !name.equals("");
        result &= artist.getAlbums().size() > 0;
        return result;
    }

    public static boolean validate(Album album) {
        return false;
    }

    public static boolean validate(Track track) {
        String name = track.getName();
        Duration duration = track.getDuration();
        boolean result = name != null && !name.equals("");
        result &= duration != null && duration.getSeconds() > 0;
        return result;
    }


}
