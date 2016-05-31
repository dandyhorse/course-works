package com.epam.homework_8.dao.validators;


import com.epam.homework_8.dao.exceptions.ModelException;
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
        isException(result, "invalid MusicGuide");
    }

    public static void validate(Artist artist) {
        String name = artist.getName();
        boolean result = name != null && !name.equals("");
        result &= artist.getAlbums().size() > 0;
        isException(result, "invalid Artist");
    }

    public static void validate(Album album) {
        String genre = album.getGenre();
        String name = album.getName();
        int size = album.getTrackList().size();
        boolean result = name != null && !name.equals("");
        result &= genre != null && !genre.equals("");
        result &= size > 0;
        String message = "invalid Album";
        isException(result, message);
    }

    private static void isException(boolean result, String message) {
        if (!result) {
            throw new ModelException(message);
        }
    }

    public static void validate(Track track) {
        String name = track.getName();
        Duration duration = track.getDuration();
        boolean result = name != null && !name.equals("");
        result &= duration != null && duration.getSeconds() > 0;
        isException(result, "invalid Track");
    }

}
