package com.epam.homework_8.models;

import com.sun.istack.internal.NotNull;

import java.util.*;


public class MusicGuide {

    private List<Artist> artistList;

    public MusicGuide() {
        artistList = new ArrayList<>();
    }

    public MusicGuide(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public List<Artist> getAllArtists() {
        return artistList;
    }

    public void addArtist(@NotNull Artist artist) {
        artistList.add(artist);
    }

    public void addAllArtists(Collection<Artist> artists) {
        artists.forEach(this::addArtist);
    }

    @Override
    public String toString() {
        return "MusicGuide {" +
                '\n' + artistList +
                "\n}";
    }
}
