package com.epam.homework_8.dao.entity;

import com.epam.homework_8.models.Artist;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistEntity implements TextExternalizable {

    private transient Artist modelArtist;
    private transient List<AlbumEntity> albumEntities;

    public ArtistEntity(Artist modelArtist) {
        this();
        this.modelArtist = modelArtist;
    }

    public ArtistEntity() {
        albumEntities = new ArrayList<>();
    }

    public Artist getModel() {
        return modelArtist;
    }

    public void addAlbumEntity(AlbumEntity albumEntity) {
        albumEntities.add(albumEntity);
    }

    @Override
    public void writeTextExternal(Writer out) throws IOException {
        String name = modelArtist.getName();
        out.write("\n\tArtist{");
        out.write(String.format("\tName : %s", name));
        albumEntities.forEach(albumEntity -> {
            try {
                albumEntity.writeTextExternal(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        out.write("\n\t}");
    }

    @Override
    public void readTextExternal(Reader in) throws IOException, ClassNotFoundException {

    }


}

