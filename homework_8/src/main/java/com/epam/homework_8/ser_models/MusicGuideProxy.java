package com.epam.homework_8.ser_models;

import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class MusicGuideProxy implements Externalizable {
    private MusicGuide modelMusicGuide;

    public MusicGuideProxy(MusicGuide modelMusicGuide) {
        this.modelMusicGuide = modelMusicGuide;
    }

    public MusicGuideProxy() {
    }

    public MusicGuide getModel() {
        return modelMusicGuide;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        List<Artist> allArtists = modelMusicGuide.getAllArtists();
        List<ArtistProxy> artistProxyList = allArtists.stream().map(ArtistProxy::new).collect(Collectors.toList());

        out.writeObject(artistProxyList);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        List<ArtistProxy> artistProxyList = (List<ArtistProxy>) in.readObject();
        List<Artist> artistList = artistProxyList.stream().map(ArtistProxy::getModelArtist).collect(Collectors.toList());
        modelMusicGuide = new MusicGuide(artistList);
    }

}
