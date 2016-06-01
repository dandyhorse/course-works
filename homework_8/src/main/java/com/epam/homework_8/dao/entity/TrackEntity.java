package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.entity.tags.Tags;
import com.epam.homework_8.dao.exceptions.EntityException;
import com.epam.homework_8.dao.entity.tags.Utils;
import com.epam.homework_8.models.Track;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;

import java.io.*;
import java.time.Duration;

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
        out.write(Utils.getFormatTag("\n\t\t\t%s{", Tags.TRACK_TAG));
        out.write(Utils.getFormatTag("\t%s : %s\n\t\t\t\t\t%s : %s", Tags.NAME_ATTR, trackName, Tags.DURATION_ATTR, trackDuration.toString()));
        out.write("\n\t\t\t}");
    }

    @Override
    public void readTextExternal(BufferedReader in) throws IOException {
        String stringTrack = in.readLine();
        String innerString;
        try {
            innerString = Utils.deleteLastBracket(stringTrack);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EntityException("invalid tag " + Tags.TRACK_TAG + " (brackets) in file", e);
        }
        try {
            trackName = getName(innerString);
            trackDuration = getDuration(innerString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EntityException("invalid tags " + Tags.NAME_ATTR + " or " + Tags.DURATION_ATTR, e);
        }
    }

    private Duration getDuration(String string) {
        int start = string.indexOf(Utils.getFormatTag("%s :", Tags.DURATION_ATTR));
        String durString = string.substring(start, string.length()).replace(Utils.getFormatTag("%s :", Tags.DURATION_ATTR), "").trim();
        return Duration.parse(durString);
    }

    private String getName(String string) {
        int start = string.indexOf(Utils.getFormatTag("%s :", Tags.NAME_ATTR));
        int end = string.indexOf(Utils.getFormatTag("%s :", Tags.DURATION_ATTR));
        String substring = string.substring(start, end).replace(Utils.getFormatTag("%s :", Tags.NAME_ATTR), "");
        return substring.trim();
    }

}
