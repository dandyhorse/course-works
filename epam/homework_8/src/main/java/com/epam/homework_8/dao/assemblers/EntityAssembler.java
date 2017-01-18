package com.epam.homework_8.dao.assemblers;

import com.epam.homework_8.dao.entity.AlbumEntity;
import com.epam.homework_8.dao.entity.ArtistEntity;
import com.epam.homework_8.dao.entity.MusicGuideEntity;
import com.epam.homework_8.dao.entity.TrackEntity;
import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.models.Track;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EntityAssembler {

    private Map<String, TrackEntity> uniqueTracks;

    public MusicGuideEntity newEntity(MusicGuide musicGuide) {
        uniqueTracks = new HashMap<>();
        MusicGuideEntity guideEntity = new MusicGuideEntity();
        musicGuide.getAllArtists().forEach(artist -> guideEntity.addArtistEntity(newEntity(artist)));
        uniqueTracks = null;
        return guideEntity;
    }

    private ArtistEntity newEntity(Artist artist) {
        ArtistEntity artistEntity = new ArtistEntity(artist);
        artist.getAlbums().forEach(album -> artistEntity.addAlbumEntity(newEntity(album)));
        return artistEntity;
    }

    private AlbumEntity newEntity(Album album) {
        AlbumEntity albumEntity = new AlbumEntity(album);
        album.getTrackList().forEach(track ->
                Optional.ofNullable(newUniqueEntity(track)).ifPresent(albumEntity::addTrackEntity));
        return albumEntity;
    }

    private TrackEntity newUniqueEntity(Track track) {
        if (isTrackUnique(track, uniqueTracks)) {
            TrackEntity trackEntity = newEntity(track);
            uniqueTracks.put(track.getName(), trackEntity);
            return trackEntity;
        }
        return null;
    }

    public TrackEntity newEntity(Track track) {
        return new TrackEntity(track);
    }

    private boolean isTrackUnique(Track track, Map<String, TrackEntity> uniqueTracks) {
        TrackEntity trackEntity = uniqueTracks.get(track.getName());
        return trackEntity == null || !trackEntity.getTrackDuration().equals(track.getDuration());
    }

}
