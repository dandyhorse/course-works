package com.epam.homework_8.models;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Track {

    private String name;
    private Duration duration;

    public Track(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return  "\t\tTrack {" +
                "\n\t\t\tname = " + name +
                "\n\t\t\tduration = " + duration +
                "\n\t\t\t}\n";
    }
}
