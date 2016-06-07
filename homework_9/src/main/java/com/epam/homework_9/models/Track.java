package com.epam.homework_9.models;

import com.epam.homework_9.dao.xml.adapters.JaxbDurationStringAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Duration;

@XmlType(name = "track", namespace = "http://www.epam.com/musicGuide")
@XmlAccessorType(XmlAccessType.FIELD)
public class Track {

    @XmlAttribute(name = "id", required = true)
    private Long id;
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlJavaTypeAdapter(JaxbDurationStringAdapter.class)
    @XmlAttribute(name = "duration", required = true)
    private Duration duration;

    public Track(Long id, String name, Duration duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Track() {
    }

    public String getName() {
        return name;
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
        if (name != null ? !name.equals(track.name) : track.name != null) return false;
        return duration != null ? duration.equals(track.duration) : track.duration == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\t\tTrack {" +
                "\n\t\t\tname = " + name +
                "\n\t\t\tduration = " + duration +
                "\n\t\t\t}\n";
    }
}
