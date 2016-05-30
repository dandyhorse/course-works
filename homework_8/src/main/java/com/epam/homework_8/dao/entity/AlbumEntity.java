package com.epam.homework_8.dao.entity;

import com.epam.homework_8.models.Album;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumEntity implements TextExternalizable {

    private transient Album modelAlbum;
    private transient List<TrackEntity> trackEntities;

    public AlbumEntity(Album modelAlbum) {
        this();
        this.modelAlbum = modelAlbum;
    }

    public AlbumEntity() {
        trackEntities = new ArrayList<>();
    }

    public void addTrackEntity(TrackEntity trackEntity) {
        trackEntities.add(trackEntity);
    }

    public Album getModel() {
        return modelAlbum;
    }

    @Override
    public void writeTextExternal(Writer out) throws IOException {
        String name = modelAlbum.getName();
        String genre = modelAlbum.getGenre();
        out.write("\n\t\tAlbum{");
        out.write(String.format("\tName : %s\n\t\t\t\tGenre : %s", name, genre));
        trackEntities.forEach(trackEntity -> {
            try {
                trackEntity.writeTextExternal(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        out.write("\n\t\t}");
    }

    @Override
    public void readTextExternal(Reader in) throws IOException, ClassNotFoundException {

    }
}
