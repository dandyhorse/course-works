package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.serializers.Utils;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;
import com.epam.homework_8.dao.validators.TagValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArtistEntity implements TextExternalizable {

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
        out.write("\n\tArtist{");
        out.write(String.format("\tName : %s", artistName));
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
    public void readTextExternal(BufferedReader in) throws IOException {
        String stringArtist = in.readLine();
        artistName = getName(stringArtist);
        String innerString = Utils.deleteLastBracketInArtistTag(stringArtist);
        readAlbums(innerString);
    }

    private void readAlbums(String innerString) {
        TagValidator.validateAlbumTag(innerString);
        Stream<String> albumStream = Stream.of(innerString.split("Album\\{")).skip(1);
        albumStream.forEach(albumString -> {
            AlbumEntity albumEntity = new AlbumEntity();
            try {
                albumEntity.readTextExternal(Utils.stringToBuffer(albumString));
            } catch (IOException e) {
                e.getMessage();
            }
            albumEntities.add(albumEntity);
        });
    }

    private String getName(String string) {
        int start = string.indexOf("Name :");
        int end = string.indexOf("Album{");
        String substring = string.substring(start, end).replace("Name :", "");
        return substring.trim();
    }

}

