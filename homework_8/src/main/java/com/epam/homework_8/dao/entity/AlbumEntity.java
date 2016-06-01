package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.entity.tags.Tags;
import com.epam.homework_8.dao.exceptions.EntityException;
import com.epam.homework_8.dao.entity.tags.Utils;
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
        out.write(Utils.getFormatTag("\n\t\t%s{", Tags.ALBUM_TAG));
        out.write(Utils.getFormatTag("\t%s : %s\n\t\t\t\t%s : %s", Tags.NAME_ATTR, nameAlbum, Tags.GENRE_ATTR, genreAlbum));
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
        String innerString = properProcessText(stringAlbum);
        readTracks(innerString);
    }

    private String properProcessText(String stringAlbum) {
        String innerString;
        try {
            innerString = Utils.deleteLastBracket(stringAlbum);
            innerString = Utils.deleteAttributeFromInnerString(innerString, Tags.NAME_ATTR, nameAlbum);
            innerString = Utils.deleteAttributeFromInnerString(innerString, Tags.GENRE_ATTR, genreAlbum);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EntityException("invalid tag Album{} in file", e);
        }
        return innerString;
    }

    private String getGenre(String string) {
        System.out.println(string);
        int start = string.indexOf(Utils.getFormatTag("%s :", Tags.GENRE_ATTR));
        int end = string.indexOf(Utils.getFormatTag("%s{", Tags.TRACK_TAG));
        if (end == -1) {
            end = string.indexOf("}");
        }
        String substring = string.substring(start, end).replace(Utils.getFormatTag("%s :", Tags.GENRE_ATTR), "");
        return substring.trim();
    }

    private String getName(String string) {
        int start = string.indexOf(Utils.getFormatTag("%s :", Tags.NAME_ATTR));
        int end = string.indexOf(Utils.getFormatTag("%s :", Tags.GENRE_ATTR));
        String substring = string.substring(start, end).replace("Name :", "");
        return substring.trim();
    }

    private void readTracks(String innerString) {
        TagValidator.validateTrackTag(innerString);
        Stream<String> trackStream = Stream.of(innerString.split(Tags.TRACK_TAG + "\\{")).skip(1);
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
