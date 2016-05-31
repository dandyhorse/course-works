package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.exceptions.EntityException;
import com.epam.homework_8.dao.serializers.Utils;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;
import com.epam.homework_8.dao.validators.TagValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MusicGuideEntity implements TextExternalizable {

    private transient List<ArtistEntity> artistEntities;

    public MusicGuideEntity() {
        artistEntities = new ArrayList<>();
    }

    public void addArtistEntity(ArtistEntity artistEntity) {
        artistEntities.add(artistEntity);
    }

    public List<ArtistEntity> getArtistEntities() {
        return artistEntities;
    }

    @Override
    public void writeTextExternal(BufferedWriter out) throws IOException {
        out.write("MusicGuide{");
        writeArtists(out);
        out.write("\n}");
    }

    private void writeArtists(BufferedWriter out) {
        artistEntities.forEach(artistEntity -> {
            try {
                artistEntity.writeTextExternal(out);
            } catch (IOException e) {
                throw new EntityException("MusicGuide write crash", e);
            }
        });
    }

    @Override
    public void readTextExternal(BufferedReader in) throws IOException {
        String text = readerToStringBuilder(in);
        TagValidator.validateMusicGuideTag(text);
        String[] innerText = text.split("MusicGuide\\{");
        //picked innerText[1] cause innerText[0] is empty String
        String innerString = Utils.deleteLastBracket(innerText[1]);
        readArtists(innerString);
    }

    private String readerToStringBuilder(BufferedReader in) {
        StringBuilder text = new StringBuilder();
        in.lines().forEach(text::append);
        return text.toString();
    }

    private void readArtists(String innerString) {
        TagValidator.validateArtistTag(innerString);
        Stream<String> artistStream = Stream.of(innerString.split("Artist\\{")).skip(1);
        artistStream.forEach(s -> {
            ArtistEntity artistEntity = new ArtistEntity();
            try {
                artistEntity.readTextExternal(Utils.stringToBuffer(s));
            } catch (IOException e) {
                throw new EntityException("MusicGuide read crash", e);
            }
            artistEntities.add(artistEntity);
        });
    }

}
