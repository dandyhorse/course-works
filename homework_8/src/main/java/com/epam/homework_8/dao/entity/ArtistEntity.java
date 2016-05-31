package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.exceptions.EntityException;
import com.epam.homework_8.dao.serializers.Utils;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;
import com.epam.homework_8.dao.validators.TagValidator;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArtistEntity implements TextExternalizable {

    private transient String artistName;
    private transient List<AlbumEntity> albumEntities;

    private static final transient String albumTag = TagAttributes.ALBUM_TAG;
    private static final transient String nameTag = TagAttributes.NAME_TAG;
    private static final transient String artistTag = TagAttributes.ARTIST_TAG;

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
        out.write(Utils.getFormatTag("\n\t%s{", artistTag));
        out.write(Utils.getFormatTag("\t%s : %s", nameTag, artistName));
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
        readAlbums(innerString);
    }

    private void readAlbums(String innerString) {
        TagValidator.validateAlbumTag(innerString);
        Stream<String> albumStream = Stream.of(innerString.split(albumTag + "\\{")).skip(1);
        albumStream.forEach(albumString -> {
            AlbumEntity albumEntity = new AlbumEntity();
            try {
                albumEntity.readTextExternal(Utils.stringToBuffer(albumString));
            } catch (IOException e) {
                throw new EntityException("ArtistEntity read crash", e);
            }
            albumEntities.add(albumEntity);
        });
    }

    private String getName(String string) {
        String tag = Utils.getFormatTag("%s :", nameTag);
        int start = string.indexOf(tag);
        int end = string.indexOf(Utils.getFormatTag("%s{", albumTag));
        String substring = string.substring(start, end).replace(tag, "");
        return substring.trim();
    }

}

