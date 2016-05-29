package com.epam.homework_8.ser_models;

import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Track;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumProxy implements Externalizable {
    private Album modelAlbum;

    public AlbumProxy(Album modelAlbum) {
        this.modelAlbum = modelAlbum;
    }

    public AlbumProxy() {
    }

    public Album getModelAlbum() {
        return modelAlbum;
    }

    @SuppressWarnings("unused")
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String name = modelAlbum.getName();
        String genre = modelAlbum.getGenre();
        List<Track> trackList = modelAlbum.getTrackList();
        List<TrackProxy> artistProxyList = trackList.stream().map(TrackProxy::new).collect(Collectors.toList());

        out.writeObject(name);
        out.writeObject(genre);
        out.writeObject(artistProxyList);
    }

    @SuppressWarnings("unused")
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String genre = (String) in.readObject();
        List<TrackProxy> trackProxyList = (List<TrackProxy>) in.readObject();
        List<Track> trackList = trackProxyList.stream().map(TrackProxy::getModelTrack).collect(Collectors.toList());
        modelAlbum = Album.newBuilder().addAllTrack(trackList).setName(name).setGenre(genre).build();
    }

}
