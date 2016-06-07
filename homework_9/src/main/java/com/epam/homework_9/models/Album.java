package com.epam.homework_9.models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlType(name = "album", namespace = "http://www.epam.com/musicGuide")
@XmlAccessorType(XmlAccessType.FIELD)
public class Album {

    @XmlAttribute(name = "id", required = true)
    private Long id;
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlAttribute(name = "genre", required = true)
    private String genre;
    @XmlElements(value = @XmlElement(name = "track"))
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

        public Builder id(Long id) {
            Album.this.id = id;
            return this;
        }

        public Builder name(String name) {
            Album.this.name = name;
            return this;
        }

        public Builder genre(String genre) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (id != null ? !id.equals(album.id) : album.id != null) return false;
        if (name != null ? !name.equals(album.name) : album.name != null) return false;
        if (genre != null ? !genre.equals(album.genre) : album.genre != null) return false;
        return trackList != null ? trackList.equals(album.trackList) : album.trackList == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (trackList != null ? trackList.hashCode() : 0);
        return result;
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
