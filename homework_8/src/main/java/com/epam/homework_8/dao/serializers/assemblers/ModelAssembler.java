package com.epam.homework_8.dao.serializers.assemblers;

import com.epam.homework_8.dao.entity.AlbumEntity;
import com.epam.homework_8.dao.entity.ArtistEntity;
import com.epam.homework_8.dao.entity.MusicGuideEntity;
import com.epam.homework_8.dao.entity.TrackEntity;
import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.models.Track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModelAssembler {

    private Map<String, List<Track>> albumTrackMap;

    public MusicGuide newModel(MusicGuideEntity musicGuideEntity) {
        albumTrackMap = new HashMap<>();
        List<Artist> artists = new ArrayList<>();
        musicGuideEntity.getArtistEntities().forEach(artistEntity -> artists.add(newModel(artistEntity)));
        albumTrackMap = null;
        return new MusicGuide(artists);
    }

    private Artist newModel(ArtistEntity artistEntity) {
        Artist.Builder artistBuilder = Artist.newBuilder()
                .setName(artistEntity.getArtistName());
        artistEntity.getAlbumEntities().forEach(albumEntity -> artistBuilder.addAlbum(newModel(albumEntity)));
        return artistBuilder.build();
    }

    private Album newModel(AlbumEntity albumEntity) {
        String nameAlbum = albumEntity.getNameAlbum();
        Album.Builder albumBuilder = Album.newBuilder()
                .setName(nameAlbum)
                .setGenre(albumEntity.getGenreAlbum());

        if (albumTrackMap.containsKey(nameAlbum)) {
            addTracksToAlbum(nameAlbum, albumBuilder);
        } else {
            List<Track> trackList = new ArrayList<>();
            albumEntity.getTrackEntities().forEach(trackEntity -> trackList.add(newModel(trackEntity)));
            albumTrackMap.put(nameAlbum, trackList);
            addTracksToAlbum(nameAlbum, albumBuilder);
        }
        return albumBuilder.build();
    }

    private void addTracksToAlbum(String nameAlbum, Album.Builder albumBuilder) {
        List<Track> trackList = albumTrackMap.get(nameAlbum);
        trackList.forEach(albumBuilder::addTrack);
    }

    private Track newModel(TrackEntity trackEntity) {
        return new Track(trackEntity.getTrackName(), trackEntity.getTrackDuration());
    }

}
