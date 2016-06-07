
package com.epam.homework_9.models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "guide", namespace = "http://www.epam.com/musicGuide")
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicGuide {

    @XmlElements(value = @XmlElement(name = "artist"))
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

    public void addArtist(Artist artist) {
        artistList.add(artist);
    }

    public void addAllArtists(Collection<Artist> artists) {
        artists.forEach(this::addArtist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicGuide that = (MusicGuide) o;

        return artistList != null ? artistList.equals(that.artistList) : that.artistList == null;

    }

    @Override
    public int hashCode() {
        return artistList != null ? artistList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MusicGuide {" +
                '\n' + artistList +
                "\n}";
    }

}
