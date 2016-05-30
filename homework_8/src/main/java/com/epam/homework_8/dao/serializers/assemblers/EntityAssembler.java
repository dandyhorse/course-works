package com.epam.homework_8.dao.serializers.assemblers;

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

public class EntityAssembler {

    public MusicGuideEntity newEntity(MusicGuide musicGuide) {
        Map<String, TrackEntity> uniqueTracks = new HashMap<>();

        MusicGuideEntity guideEntity = new MusicGuideEntity(musicGuide);

        musicGuide.getAllArtists().forEach(artist -> bindArtist(uniqueTracks, guideEntity, artist));

        return guideEntity;
    }

    private void bindArtist(Map<String, TrackEntity> uniqueTracks, MusicGuideEntity guideEntity, Artist artist) {
        ArtistEntity artistEntity = newEntity(artist);
        artist.getAlbums().forEach(album -> bindAlbum(uniqueTracks, artistEntity, album));
        guideEntity.addArtistEntity(artistEntity);
    }

    private void bindAlbum(Map<String, TrackEntity> uniqueTracks, ArtistEntity artistEntity, Album album) {
        AlbumEntity albumEntity = newEntity(album);
        album.getTrackList().forEach(track -> bindUniqueTrack(uniqueTracks, albumEntity, track));
        artistEntity.addAlbumEntity(albumEntity);
    }

    private void bindUniqueTrack(Map<String, TrackEntity> uniqueTracks, AlbumEntity albumEntity, Track track) {
        if (isTrackUnique(track, uniqueTracks)) {
            TrackEntity trackEntity = newEntity(track);
            uniqueTracks.put(track.getName(), trackEntity);
            albumEntity.addTrackEntity(trackEntity);
        }
    }

    private TrackEntity newEntity(Track track) {
        return new TrackEntity(track);
    }

    private boolean isTrackUnique(Track track, Map<String, TrackEntity> uniqueTracks) {
        TrackEntity trackEntity = uniqueTracks.get(track.getName());
        return trackEntity == null || !trackEntity.getModel().getDuration().equals(track.getDuration());
    }

    private ArtistEntity newEntity(Artist artist) {
        return new ArtistEntity(artist);
    }

    private AlbumEntity newEntity(Album album) {
        return new AlbumEntity(album);
    }


}
