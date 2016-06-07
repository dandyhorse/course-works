package com.epam.homework_9;

import com.epam.homework_9.dao.interfaces.Dao;
import com.epam.homework_9.models.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Interactive {

    private Logger logger = LogManager.getLogger("com.epam.homework_9.fullOutLog");
    private Dao<Artist> artistDao;

    public Interactive(Dao<Artist> artistDao) {
        this.artistDao = artistDao;
    }

    public Long getAllTrackTime(Long artistId) {
        Artist artist = artistDao.getById(artistId);
        RuntimeException exc = new RuntimeException("can't calculate time from tracks");
        return artist.getAlbums()
                .stream()
                .map(album -> album.getTrackList()
                        .stream()
                        .map(track -> track
                                .getDuration()
                                .getSeconds())
                        .reduce((aLong, aLong2) -> aLong + aLong2)
                        .orElseThrow(() -> exc))
                .reduce((aLong, aLong2) -> aLong + aLong2)
                .orElseThrow(() -> exc);
    }
}
