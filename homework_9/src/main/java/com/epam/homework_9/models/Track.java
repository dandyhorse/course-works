package com.epam.homework_9.models;

import com.epam.homework_9.dao.impl.xml.utils.adapters.JaxbDurationStringAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Duration;

@XmlType(name = "track", namespace = "http://www.epam.com/musicGuide")
@XmlAccessorType(XmlAccessType.FIELD)
public class Track {

    @XmlAttribute(name = "id", required = true)
    private Long id;
    @XmlAttribute(name = "title", required = true)
    private String title;
    @XmlJavaTypeAdapter(JaxbDurationStringAdapter.class)
    @XmlAttribute(name = "duration", required = true)
    private Duration duration;

    public Track(Long id, String title, Duration duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Track() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (id != null ? !id.equals(track.id) : track.id != null) return false;
        if (title != null ? !title.equals(track.title) : track.title != null) return false;
        return duration != null ? duration.equals(track.duration) : track.duration == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\t\tTrack {" +
                "\n\t\t\tid = " + id +
                "\n\t\t\ttitle = " + title +
                "\n\t\t\tduration = " + duration +
                "\n\t\t\t}\n";
    }
}
