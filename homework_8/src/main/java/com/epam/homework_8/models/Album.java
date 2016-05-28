package com.epam.homework_8.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Album {

    private String name;
    private String genre;
    private List<Track> trackList;

    public Album() {
        trackList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public static Builder newBuilder() {
        return new Album().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setName(String name) {
            Album.this.name = name;
            return this;
        }

        public Builder setGenre(String genre) {
            Album.this.genre = genre;
            return this;
        }

        public Builder addTrack(Track track) {
            Album.this.trackList.add(track);
            return this;
        }

        public Builder addAllTrack(Collection<Track> trackList) {
            Album.this.trackList.addAll(trackList);
            return this;
        }

        public Album build() {
            return Album.this;
        }
    }

    @Override
    public String toString() {
        return "\tAlbum {" +
                "\n\t\tname = " + name +
                "\n\t\tgenre = " + genre +
                '\n' + trackList +
                "\n\t\t}\n";
    }
}
