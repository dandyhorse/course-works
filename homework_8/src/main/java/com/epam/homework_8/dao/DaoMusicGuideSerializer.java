package com.epam.homework_8.dao;

import com.epam.homework_8.dao.assemblers.AssemblerService;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.dao.entity.MusicGuideEntity;
import com.epam.homework_8.dao.serializers.interfaces.Serializer;

public class DaoMusicGuideSerializer implements Dao<MusicGuide> {

    private String outputFile;
    private Serializer<MusicGuideEntity> serializer;

    public DaoMusicGuideSerializer(String outputFile, Serializer<MusicGuideEntity> serializer) {
        this.outputFile = outputFile;
        this.serializer = serializer;
    }

    public void save(MusicGuide musicGuide) {
        MusicGuideEntity guideEntity = AssemblerService.newEntityAssembler().newEntity(musicGuide);
        serializer.serialize(outputFile, guideEntity);
    }

    public MusicGuide get() {
        MusicGuideEntity guideEntity = serializer.deserialize(outputFile);
        return AssemblerService.newModelAssembler().newModel(guideEntity);
    }

}
