package com.epam.homework_9.assemblers;

import com.epam.homework_9.entity.AlbumEntity;
import com.epam.homework_9.entity.ArtistEntity;
import com.epam.homework_9.entity.MusicGuideEntity;
import com.epam.homework_9.entity.TrackEntity;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import com.epam.homework_9.models.Track;

import java.util.*;

public class EntityAssembler {

    private Map<String, TrackEntity> uniqueTracks;
    private Long artistLongId = 1L;
    private Long albumLongId = 1L;
    private Long trackLongId = 1L;

    public MusicGuideEntity newEntity(MusicGuide musicGuide) {
        uniqueTracks = new HashMap<>();
        List<ArtistEntity> artistEntityList = new ArrayList<>();
        musicGuide.getAllArtists().forEach(artist -> artistEntityList.add(newEntity(artist)));
        MusicGuideEntity guideEntity = new MusicGuideEntity(artistEntityList);
        uniqueTracks = null;
        return guideEntity;
    }

    private ArtistEntity newEntity(Artist artist) {
        List<AlbumEntity> albumEntityList = new ArrayList<>();
        artist.getAlbums().forEach(album -> albumEntityList.add(newEntity(album)));
        return new ArtistEntity(artistLongId++, artist, albumEntityList);
    }

    private AlbumEntity newEntity(Album album) {
        List<TrackEntity> trackEntityList = new ArrayList<>();
        album.getTrackList().forEach(t -> Optional.ofNullable(newUniqueEntity(t)).ifPresent(trackEntityList::add));
        return new AlbumEntity(albumLongId++, album, trackEntityList);
    }

    private TrackEntity newUniqueEntity(Track track) {
        if (isTrackUnique(track, uniqueTracks)) {
            TrackEntity trackEntity = newEntity(track);
            uniqueTracks.put(track.getName(), trackEntity);
            return trackEntity;
        } else {
            trackLongId--;
            return null;
        }
    }

    private TrackEntity newEntity(Track track) {
        return new TrackEntity(trackLongId++, track);
    }

    private boolean isTrackUnique(Track track, Map<String, TrackEntity> uniqueTracks) {
        TrackEntity trackEntity = uniqueTracks.get(track.getName());
        return trackEntity == null || !trackEntity.getTrackDuration().equals(track.getDuration());
    }

}
