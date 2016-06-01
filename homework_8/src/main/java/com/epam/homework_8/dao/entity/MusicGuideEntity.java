package com.epam.homework_8.dao.entity;

import com.epam.homework_8.dao.entity.tags.Tags;
import com.epam.homework_8.dao.exceptions.EntityException;
import com.epam.homework_8.dao.entity.tags.Utils;
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
        out.write(Utils.formatTag("%s{", Tags.MUSIC_GUIDE_TAG));
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
        String text = Utils.readerToString(in);
        TagValidator.validateMusicGuideTag(text);
        String[] innerText = text.split(Tags.MUSIC_GUIDE_TAG + "\\{");
        String innerString = Utils.deleteLastBracket(innerText[1]);
        readArtists(innerString);
    }

    private void readArtists(String innerString) {
        TagValidator.validateArtistTag(innerString);
        Stream<String> artistStream = Stream.of(innerString.split(Tags.ARTIST_TAG + "\\{")).skip(1);
        artistStream.forEach(s -> {
            ArtistEntity artistEntity = new ArtistEntity();
            try {
                artistEntity.readTextExternal(Utils.stringToBufferReader(s));
            } catch (IOException e) {
                throw new EntityException("MusicGuide read crash", e);
            }
            artistEntities.add(artistEntity);
        });
    }

}
