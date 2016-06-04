package com.epam.homework_9.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "guide")
public class MusicGuideEntity {

    @XmlElement(name = "artist")
    private transient List<ArtistEntity> artistEntityList;

    public MusicGuideEntity(List<ArtistEntity> artistEntityList) {
        this.artistEntityList = artistEntityList;
    }

    public List<ArtistEntity> getArtistEntities() {
        return artistEntityList;
    }

}
