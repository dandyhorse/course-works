package com.epam.homework_8.dao.entity;

import com.epam.homework_8.models.Track;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;

import java.io.*;
import java.time.Duration;

public class TrackEntity implements TextExternalizable {

    private transient Track modelTrack;

    public TrackEntity(Track modelTrack) {
        this.modelTrack = modelTrack;
    }

    public TrackEntity() {
    }

    public Track getModel() {
        return modelTrack;
    }

    @Override
    public void writeTextExternal(Writer out) throws IOException {
        String name = modelTrack.getName();
        Duration duration = modelTrack.getDuration();
        out.write("\n\t\t\tTrack{");
        out.write(String.format("\tName : %s\n\t\t\t\t\tDuration : %s", name, duration));
        out.write("\n\t\t\t}");
    }

    @Override
    public void readTextExternal(Reader in) throws IOException, ClassNotFoundException {

    }
}
