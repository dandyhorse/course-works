package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.exceptions.EntityException;
import com.epam.homework_8.dao.serializers.Utils;
import com.epam.homework_8.models.Album;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;
import com.epam.homework_8.dao.validators.TagValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AlbumEntity implements TextExternalizable {

    private transient String nameAlbum;
    private transient String genreAlbum;
    private transient List<TrackEntity> trackEntities;

    public AlbumEntity(Album modelAlbum) {
        this();
        this.nameAlbum = modelAlbum.getName();
        this.genreAlbum = modelAlbum.getGenre();
    }

    public AlbumEntity() {
        trackEntities = new ArrayList<>();
    }

    public void addTrackEntity(TrackEntity trackEntity) {
        trackEntities.add(trackEntity);
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public String getGenreAlbum() {
        return genreAlbum;
    }

    public List<TrackEntity> getTrackEntities() {
        return trackEntities;
    }

    @Override
    public void writeTextExternal(BufferedWriter out) throws IOException {
        out.write("\n\t\tAlbum{");
        out.write(String.format("\tName : %s\n\t\t\t\tGenre : %s", nameAlbum, genreAlbum));
        trackEntities.forEach(trackEntity -> {
            try {
                trackEntity.writeTextExternal(out);
            } catch (IOException e) {
                throw new EntityException("AlbumEntity write crash", e);
            }
        });
        out.write("\n\t\t}");
    }

    @Override
    public void readTextExternal(BufferedReader in) throws IOException {
        String stringAlbum = in.readLine();
        nameAlbum = getName(stringAlbum);
        genreAlbum = getGenre(stringAlbum);
        String innerString;
        try {
            innerString = Utils.deleteLastBracket(stringAlbum);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EntityException("invalid tag Album{} in file", e);
        }
        readTracks(innerString);
    }

    private String getGenre(String string) {
        int start = string.indexOf("Genre :");
        int end = string.indexOf("Track{");
        if (end == -1) {
            end = string.lastIndexOf(" ");
        }
        String substring = string.substring(start, end).replace("Genre :", "");
        return substring.trim();
    }

    private String getName(String string) {
        int start = string.indexOf("Name :");
        int end = string.indexOf("Genre");
        String substring = string.substring(start, end).replace("Name :", "");
        return substring.trim();
    }

    private void readTracks(String innerString) {
        if (TagValidator.validateTrackTag(innerString)) {
            Stream<String> trackStream = Stream.of(innerString.split("Track\\{")).skip(1);
            trackStream.forEach(trackString -> {
                TrackEntity trackEntity = new TrackEntity();
                try {
                    trackEntity.readTextExternal(Utils.stringToBuffer(trackString));
                } catch (IOException e) {
                    throw new EntityException("AlbumEntity read crash", e);
                }
                trackEntities.add(trackEntity);
            });
        }
    }

}
