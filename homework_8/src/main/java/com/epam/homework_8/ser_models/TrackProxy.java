package com.epam.homework_8.ser_models;

import com.epam.homework_8.models.Track;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.Duration;

public class TrackProxy implements Externalizable {

    private Track modelTrack;

    public TrackProxy(Track modelTrack) {
        this.modelTrack = modelTrack;
    }

    public TrackProxy() {
    }

    public Track getModelTrack() {
        return modelTrack;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String name = modelTrack.getName();
        Duration duration = modelTrack.getDuration();

        out.writeObject(name);
        out.writeObject(duration);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        Duration duration = (Duration) in.readObject();
        modelTrack = new Track(name, duration);
    }
}
