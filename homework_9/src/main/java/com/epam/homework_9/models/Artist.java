package com.epam.homework_9.models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlType(name = "artist", namespace = "http://www.epam.com/musicGuide")
@XmlAccessorType(XmlAccessType.FIELD)
public class Artist {
    @XmlAttribute(name = "id", required = true)
    private Long id;
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlElements(value = @XmlElement(name = "album"))
    private List<Album> albums;

    public Artist() {
        albums = new ArrayList<>();
    }

    public Long getId() {
        return id;
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

        public Builder id(Long id) {
            Artist.this.id = id;
            return this;
        }

        public Builder addAlbum(Album album) {
            Artist.this.albums.add(album);
            return this;
        }

        public Builder name(String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (!id.equals(artist.id)) return false;
        if (!name.equals(artist.name)) return false;
        return albums.equals(artist.albums);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + albums.hashCode();
        return result;
    }
}
