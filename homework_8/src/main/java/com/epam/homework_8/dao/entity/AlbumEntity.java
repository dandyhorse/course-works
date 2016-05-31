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

    private static final transient String albumTag = TagAttributes.ALBUM_TAG;
    private static final transient String nameTag = TagAttributes.NAME_TAG;
    private static final transient String genreTag = TagAttributes.GENRE_TAG;
    private static final transient String trackTag = TagAttributes.TRACK_TAG;


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
        out.write(Utils.getFormatTag("\n\t\t%s{", albumTag));
        out.write(Utils.getFormatTag("\t%s : %s\n\t\t\t\t%s : %s", nameTag, nameAlbum, genreTag, genreAlbum));
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
        String emptySpace = " ";
        int start = string.indexOf(Utils.getFormatTag("%s :", genreTag));
        int end = string.indexOf(Utils.getFormatTag("%s{", trackTag));
        if (end == -1) {
            end = string.lastIndexOf(emptySpace);
        }
        String substring = string.substring(start, end).replace(Utils.getFormatTag("%s :", genreTag), "");
        return substring.trim();
    }

    private String getName(String string) {
        int start = string.indexOf(Utils.getFormatTag("%s :", nameTag));
        int end = string.indexOf(Utils.getFormatTag("%s :", genreTag));
        String substring = string.substring(start, end).replace("Name :", "");
        return substring.trim();
    }

    private void readTracks(String innerString) {
        TagValidator.validateTrackTag(innerString);
        Stream<String> trackStream = Stream.of(innerString.split(trackTag + "\\{")).skip(1);
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
