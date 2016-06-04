package com.epam.homework_9.entity;

import com.epam.homework_9.models.Track;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.Duration;

@XmlRootElement(name = "track")
public class TrackEntity {

    @XmlAttribute(name = "id", required = true)
    private final Long id;
    @XmlAttribute(name = "name", required = true)
    private final String trackName;
    @XmlAttribute(name = "duration", required = true)
    private final Duration trackDuration;

    public TrackEntity(Long id, Track modelTrack) {
        this.id = id;
        this.trackName = modelTrack.getName();
        this.trackDuration = modelTrack.getDuration();
    }

    public Long getId() {
        return id;
    }

    public String getTrackName() {
        return trackName;
    }

    public Duration getTrackDuration() {
        return trackDuration;
    }

}
