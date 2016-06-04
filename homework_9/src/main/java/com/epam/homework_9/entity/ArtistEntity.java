package com.epam.homework_9.entity;

import com.epam.homework_9.models.Artist;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "artist")
public class ArtistEntity {

    @XmlAttribute(name = "id", required = true)
    private final Long id;
    @XmlAttribute(name = "name", required = true)
    private final String artistName;
    @XmlElement(name = "album")
    private final List<AlbumEntity> albumEntityList;

    public ArtistEntity(Long id, Artist artist, List<AlbumEntity> albumEntityList) {
        this.id = id;
        this.artistName = artist.getName();
        this.albumEntityList = albumEntityList;
    }

    public Long getId() {
        return id;
    }

    public String getArtistName() {
        return artistName;
    }

    public List<AlbumEntity> getAlbumEntityList() {
        return albumEntityList;
    }

}

