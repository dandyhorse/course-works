package com.epam.homework_9.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Artist{

    private String name;
    private List<Album> albums;

    public Artist() {
        albums = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public static Builder newBuilder() {
        return new Artist().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder addAlbum(Album album) {
            Artist.this.albums.add(album);
            return this;
        }

        public Builder setName(String name) {
            Artist.this.name = name;
            return this;
        }

        public Builder addAllAlbum(Collection<Album> albums) {
            Artist.this.albums.addAll(albums);
            return this;
        }

        public Artist build() {
            return Artist.this;
        }

    }

    @Override
    public String toString() {
        return "\tArtist {" +
                "\n\tname = " + name +
                "\n" + albums +
                "\n\t}\n";
    }
}
