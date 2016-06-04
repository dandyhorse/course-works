package com.epam.homework_9.entity;

import com.epam.homework_9.models.Album;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "album")
public class AlbumEntity {

    @XmlAttribute(name = "id", required = true)
    private final Long id;
    @XmlAttribute(name = "name", required = true)
    private final String albumName;
    @XmlElement(name = "genre", required = true)
    private final String albumGenre;

    @XmlElement(name = "track")
    private final List<TrackEntity> trackEntityList;

    public AlbumEntity(Long id, Album modelAlbum, List<TrackEntity> trackEntityList) {
        this.id = id;
        this.albumName = modelAlbum.getName();
        this.albumGenre = modelAlbum.getGenre();
        this.trackEntityList = trackEntityList;
    }

    public Long getId() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumGenre() {
        return albumGenre;
    }

    public List<TrackEntity> getTrackEntityList() {
        return trackEntityList;
    }


}
