package com.epam.homework_8.dao.entity;

import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.dao.serializers.interfaces.TextExternalizable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MusicGuideEntity implements TextExternalizable {

    private transient MusicGuide modelMusicGuide;
    private transient List<ArtistEntity> artistEntities;

    public MusicGuideEntity(MusicGuide modelMusicGuide) {
        this();
        this.modelMusicGuide = modelMusicGuide;
    }

    public MusicGuideEntity() {
        artistEntities = new ArrayList<>();
    }

    public MusicGuide getModel() {
        return modelMusicGuide;
    }

    public void addArtistEntity(ArtistEntity artistEntity) {
        artistEntities.add(artistEntity);
    }

    @Override
    public void writeTextExternal(Writer out) throws IOException {
        out.write("MusicGuide{");
        artistEntities.forEach(artistEntity -> {
            try {
                artistEntity.writeTextExternal(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        out.write("\n}");
    }

    @Override
    public void readTextExternal(Reader in) throws IOException, ClassNotFoundException {

    }


}
