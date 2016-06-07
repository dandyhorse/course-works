package com.epam.homework_9.validators;

import com.epam.homework_9.dao.exceptions.ModelException;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import com.epam.homework_9.models.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.List;

public class ModelValidator {

    private static Logger logger = LogManager.getLogger("com.epam.homework_9.fullErrLog");

    public static void validate(MusicGuide musicGuide) {
        List<Artist> allArtists = musicGuide.getAllArtists();
        boolean result = allArtists != null && allArtists.size() > 0;
        isException(result, "invalid MusicGuide");
        allArtists.forEach(ModelValidator::validate);
    }

    public static void validate(Artist artist) {
        String name = artist.getName();
        boolean result = name != null && !name.equals("");
        result &= artist.getAlbums().size() > 0;
        isException(result, "invalid Artist");
        artist.getAlbums().forEach(ModelValidator::validate);
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
        album.getTrackList().forEach(ModelValidator::validate);
    }

    private static void isException(boolean result, String message) {
        if (!result) {
            logger.error("validate is fallen because of " + message);
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
