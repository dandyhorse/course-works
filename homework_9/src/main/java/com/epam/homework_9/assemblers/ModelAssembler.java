package com.epam.homework_9.assemblers;

import com.epam.homework_9.entity.AlbumEntity;
import com.epam.homework_9.entity.ArtistEntity;
import com.epam.homework_9.entity.MusicGuideEntity;
import com.epam.homework_9.entity.TrackEntity;
import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import com.epam.homework_9.models.Track;
import com.epam.homework_9.assemblers.validators.ModelValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelAssembler {

    private Map<String, List<Track>> albumTrackMap;

    public MusicGuide newModel(MusicGuideEntity musicGuideEntity) {
        albumTrackMap = new HashMap<>();
        List<Artist> artists = new ArrayList<>();
        musicGuideEntity.getArtistEntities().forEach(artistEntity -> artists.add(newModel(artistEntity)));
        albumTrackMap = null;
        MusicGuide musicGuide = new MusicGuide(artists);
        ModelValidator.validate(musicGuide);
        return musicGuide;
    }

    private Artist newModel(ArtistEntity artistEntity) {
        Artist.Builder artistBuilder = Artist.newBuilder()
                .setName(artistEntity.getArtistName());
        artistEntity.getAlbumEntityList().forEach(albumEntity -> artistBuilder.addAlbum(newModel(albumEntity)));
        Artist artist = artistBuilder.build();
        ModelValidator.validate(artist);
        return artist;
    }

    private Album newModel(AlbumEntity albumEntity) {
        String nameAlbum = albumEntity.getAlbumName();
        Album.Builder albumBuilder = Album.newBuilder()
                .setName(nameAlbum)
                .setGenre(albumEntity.getAlbumGenre());

        if (albumTrackMap.containsKey(nameAlbum)) {
            addTracksToAlbum(nameAlbum, albumBuilder);
        } else {
            List<Track> trackList = new ArrayList<>();
            albumEntity.getTrackEntityList().forEach(trackEntity -> trackList.add(newModel(trackEntity)));
            albumTrackMap.put(nameAlbum, trackList);
            addTracksToAlbum(nameAlbum, albumBuilder);
        }
        Album album = albumBuilder.build();
        ModelValidator.validate(album);
        return album;
    }

    private void addTracksToAlbum(String nameAlbum, Album.Builder albumBuilder) {
        List<Track> trackList = albumTrackMap.get(nameAlbum);
        trackList.forEach(albumBuilder::addTrack);
    }

    private Track newModel(TrackEntity trackEntity) {
        Track track = new Track(trackEntity.getTrackName(), trackEntity.getTrackDuration());
        ModelValidator.validate(track);
        return track;
    }

}
