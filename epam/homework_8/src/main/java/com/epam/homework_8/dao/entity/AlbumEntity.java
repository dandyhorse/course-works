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

public class AlbumEntity implements TextExternalizable, Externalizable {

    private transient String albumName;
    private transient String albumGenre;
    private transient List<TrackEntity> trackEntities;

    public AlbumEntity(Album modelAlbum) {
        this();
        this.albumName = modelAlbum.getName();
        this.albumGenre = modelAlbum.getGenre();
    }

    public AlbumEntity() {
        trackEntities = new ArrayList<>();
    }

    public void addTrackEntity(TrackEntity trackEntity) {
        trackEntities.add(trackEntity);
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumGenre() {
        return albumGenre;
    }

    public List<TrackEntity> getTrackEntities() {
        return trackEntities;
    }

    @Override
    public void writeTextExternal(BufferedWriter out) throws IOException {
        out.write(Utils.formatTag("\n\t\t%s{", Tags.ALBUM_TAG));
        out.write(Utils.formatTag("\t%s : %s\n\t\t\t\t%s : %s", Tags.NAME_ATTR, albumName, Tags.GENRE_ATTR, albumGenre));
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
        albumName = getName(stringAlbum);
        albumGenre = getGenre(stringAlbum);
        String innerString = properProcessText(stringAlbum);
        readTracks(innerString);
    }

    private String properProcessText(String stringAlbum) {
        String innerString;
        try {
            innerString = Utils.deleteLastBracket(stringAlbum);
            innerString = Utils.deleteAttributeFrom(innerString, Tags.NAME_ATTR, albumName);
            innerString = Utils.deleteAttributeFrom(innerString, Tags.GENRE_ATTR, albumGenre);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EntityException("invalid tag Album{} in file", e);
        }
        return innerString;
    }

    private String getGenre(String string) {
        int start = string.indexOf(Utils.formatTag("%s :", Tags.GENRE_ATTR));
        int end = string.indexOf(Utils.formatTag("%s{", Tags.TRACK_TAG));
        if (end == -1) {
            end = string.indexOf("}");
        }
        String substring = string.substring(start, end).replace(Utils.formatTag("%s :", Tags.GENRE_ATTR), "");
        return substring.trim();
    }

    private String getName(String string) {
        int start = string.indexOf(Utils.formatTag("%s :", Tags.NAME_ATTR));
        int end = string.indexOf(Utils.formatTag("%s :", Tags.GENRE_ATTR));
        String substring = string.substring(start, end).replace("Name :", "");
        return substring.trim();
    }

    private void readTracks(String innerString) {
        TagValidator.validateTrackTag(innerString);
        Stream<String> trackStream = Stream.of(innerString.split(Tags.TRACK_TAG + "\\{")).skip(1);
        trackStream.forEach(trackString -> {
            TrackEntity trackEntity = new TrackEntity();
            try {
                trackEntity.readTextExternal(Utils.stringToBufferReader(trackString));
            } catch (IOException e) {
                throw new EntityException("AlbumEntity read crash", e);
            }
            trackEntities.add(trackEntity);
        });
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(albumName);
        out.writeObject(albumGenre);
        out.writeObject(trackEntities);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        albumName = (String) in.readObject();
        albumGenre = (String) in.readObject();
        trackEntities = (List<TrackEntity>) in.readObject();
    }


}
