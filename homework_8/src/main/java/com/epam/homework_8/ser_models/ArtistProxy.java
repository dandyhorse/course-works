package com.epam.homework_8.ser_models;

import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistProxy implements Externalizable {
    private Artist modelArtist;

    public ArtistProxy(Artist modelArtist) {
        this.modelArtist = modelArtist;
    }

    public ArtistProxy() {
    }

    public Artist getModelArtist() {
        return modelArtist;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String name = modelArtist.getName();
        List<Album> albums = modelArtist.getAlbums();
        List<AlbumProxy> albumProxyList = albums.stream().map(AlbumProxy::new).collect(Collectors.toList());

        out.writeObject(name);
        out.writeObject(albumProxyList);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        List<AlbumProxy> albumProxyList = (List<AlbumProxy>) in.readObject();
        List<Album> albumList = albumProxyList.stream().map(AlbumProxy::getModelAlbum).collect(Collectors.toList());
        modelArtist = Artist.newBuilder().setName(name).addAllAlbum(albumList).build();
    }

}

