package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.entity.tags.Tags;
import com.epam.homework_8.dao.exceptions.EntityException;
import com.epam.homework_8.dao.entity.tags.Utils;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;
import com.epam.homework_8.dao.validators.TagValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArtistEntity implements TextExternalizable, Externalizable {

    private transient String artistName;
    private transient List<AlbumEntity> albumEntities;

    public ArtistEntity(Artist modelArtist) {
        this();
        this.artistName = modelArtist.getName();
    }

    public ArtistEntity() {
        albumEntities = new ArrayList<>();
    }

    public void addAlbumEntity(AlbumEntity albumEntity) {
        albumEntities.add(albumEntity);
    }

    public String getArtistName() {
        return artistName;
    }

    public List<AlbumEntity> getAlbumEntities() {
        return albumEntities;
    }

    @Override
    public void writeTextExternal(BufferedWriter out) throws IOException {
        out.write(Utils.formatTag("\n\t%s{", Tags.ARTIST_TAG));
        out.write(Utils.formatTag("\t%s : %s", Tags.NAME_ATTR, artistName));
        albumEntities.forEach(albumEntity -> {
            try {
                albumEntity.writeTextExternal(out);
            } catch (IOException e) {
                throw new EntityException("ArtistEntity write crash", e);
            }
        });
        out.write("\n\t}");
    }

    @Override
    public void readTextExternal(BufferedReader in) throws IOException {
        String stringArtist = in.readLine();
        artistName = getName(stringArtist);
        String innerString = Utils.deleteLastBracketInArtistTag(stringArtist);
        String s = Utils.deleteAttributeFrom(innerString, Tags.NAME_ATTR, artistName);
        readAlbums(s);
    }

    private void readAlbums(String innerString) {
        TagValidator.validateAlbumTag(innerString);
        Stream<String> albumStream = Stream.of(innerString.split(Tags.ALBUM_TAG + "\\{")).skip(1);
        albumStream.forEach(albumString -> {
            AlbumEntity albumEntity = new AlbumEntity();
            try {
                albumEntity.readTextExternal(Utils.stringToBufferReader(albumString));
            } catch (IOException e) {
                throw new EntityException("ArtistEntity read crash", e);
            }
            albumEntities.add(albumEntity);
        });
    }

    private String getName(String string) {
        String tag = Utils.formatTag("%s :", Tags.NAME_ATTR);
        int start = string.indexOf(tag);
        int end = string.indexOf(Utils.formatTag("%s{", Tags.ALBUM_TAG));
        String substring = string.substring(start, end).replace(tag, "");
        return substring.trim();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(artistName);
        out.writeObject(albumEntities);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        artistName = (String) in.readObject();
        albumEntities = (List<AlbumEntity>) in.readObject();
    }
}

