package com.epam.homework_8.dao.validators;


import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.models.Track;

import java.time.Duration;
import java.util.List;

public class ModelValidator {

    public static void validate(MusicGuide musicGuide) {
        List<Artist> allArtists = musicGuide.getAllArtists();
        boolean result = allArtists != null && allArtists.size() > 0;
        if (!result) {
            throw new RuntimeException();
        }
    }

    public static void validate(Artist artist) {
        String name = artist.getName();
        boolean result = name != null && !name.equals("");
        result &= artist.getAlbums().size() > 0;
        if (!result) {
            throw new RuntimeException();
        }
    }

    public static void validate(Album album) {
        String genre = album.getGenre();
        String name = album.getName();
        int size = album.getTrackList().size();
        boolean result = name != null && !name.equals("");
        result &= genre != null && !genre.equals("");
        result &= size > 0;
        if (!result) {
            throw new RuntimeException();
        }
    }

    public static void validate(Track track) {
        String name = track.getName();
        Duration duration = track.getDuration();
        boolean result = name != null && !name.equals("");
        result &= duration != null && duration.getSeconds() > 0;
        if (!result) {
            throw new RuntimeException();
        }
    }

}
