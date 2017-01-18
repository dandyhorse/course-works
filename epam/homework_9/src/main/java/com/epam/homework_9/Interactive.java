package com.epam.homework_9;

import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

public class Interactive {

    private Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Dao<Artist> artistDao;

    public Interactive(Dao<Artist> artistDao) {
        this.artistDao = artistDao;
    }

    public Long getAllTrackTime(Long artistId) {
        Artist artist = artistDao.getById(artistId);
        RuntimeException exc = new RuntimeException("can't calculate all tracks time");
        logger.debug("getting all tracks time of " + artist.getName());
        return artist.getAlbums().stream()
                .map(mapAlbumsToTracksTime(exc))
                .reduce(sumAllTime())
                .orElseThrow(getExceptionSupplier(exc));
    }

    private BinaryOperator<Long> sumAllTime() {
        return (aLong, aLong2) -> aLong + aLong2;
    }

    private Function<Album, Long> mapAlbumsToTracksTime(RuntimeException exc) {
        return album -> album.getTrackList().stream()
                .map(track -> track
                        .getDuration()
                        .getSeconds())
                .reduce(sumAllTime())
                .orElseThrow(getExceptionSupplier(exc));
    }

    private Supplier<RuntimeException> getExceptionSupplier(RuntimeException exc) {
        return () -> {
            logger.error(exc.getMessage());
            return exc;
        };
    }


}
