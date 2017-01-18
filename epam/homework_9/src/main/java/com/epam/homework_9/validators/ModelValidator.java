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

    public static void validate(MusicGuide musicGuide) throws ModelException {
        List<Artist> allArtists = musicGuide.getAllArtists();
        boolean result = allArtists != null && allArtists.size() > 0;
        isException(result, "invalid MusicGuide");
        for (Artist artist : allArtists)
            ModelValidator.validate(artist);
    }

    public static void validate(Artist artist) throws ModelException {
        String name = artist.getName();
        boolean result = name != null && !name.equals("");
        result &= artist.getAlbums().size() > 0;
        isException(result, "invalid Artist");
        for (Album album : artist.getAlbums())
            ModelValidator.validate(album);
    }

    public static void validate(Album album) throws ModelException {
        String genre = album.getGenre();
        String title = album.getTitle();
        int size = album.getTrackList().size();
        boolean result = title != null && !title.equals("");
        result &= genre != null && !genre.equals("");
        result &= size > 0;
        String message = "invalid Album";
        isException(result, message);
        for (Track track : album.getTrackList())
            ModelValidator.validate(track);
    }

    private static void isException(boolean result, String message) throws ModelException {
        if (!result) {
            logger.error("validate is fallen because of " + message);
            throw new ModelException(message);
        }
    }

    public static void validate(Track track) throws ModelException {
        String title = track.getTitle();
        Duration duration = track.getDuration();
        boolean result = title != null && !title.equals("");
        result &= duration != null && duration.getSeconds() > 0;
        isException(result, "invalid Track");
    }

}
