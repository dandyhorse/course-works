package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.serializers.Utils;
import com.epam.homework_8.models.Track;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;

import java.io.*;
import java.time.Duration;
import java.time.format.DateTimeParseException;

public class TrackEntity implements TextExternalizable {

    private transient String trackName;
    private transient Duration trackDuration;

    public TrackEntity(Track modelTrack) {
        this.trackName = modelTrack.getName();
        this.trackDuration = modelTrack.getDuration();
    }

    public TrackEntity() {
    }

    public String getTrackName() {
        return trackName;
    }

    public Duration getTrackDuration() {
        return trackDuration;
    }

    @Override
    public void writeTextExternal(BufferedWriter out) throws IOException {
        out.write("\n\t\t\tTrack{");
        out.write(String.format("\tName : %s\n\t\t\t\t\tDuration : %s", trackName, trackDuration));
        out.write("\n\t\t\t}");
    }

    @Override
    public void readTextExternal(BufferedReader in) throws IOException {
        String stringTrack = in.readLine();
        String innerString;
        try {
            innerString = Utils.deleteLastBracket(stringTrack);
        } catch (StringIndexOutOfBoundsException e) {
            //TODO custom exception
            throw new RuntimeException(e);
        }
        trackName = getName(innerString);
        trackDuration = getDuration(innerString);
    }

    private Duration getDuration(String string) {
        int start = string.indexOf("Duration :");
        String durString = string.substring(start, string.length()).replace("Duration :", "").trim();
        return Duration.parse(durString);
    }

    private String getName(String string) {
        int start = string.indexOf("Name :");
        int end = string.indexOf("Duration :");
        String substring = string.substring(start, end).replace("Name :", "");
        return substring.trim();
    }
}
