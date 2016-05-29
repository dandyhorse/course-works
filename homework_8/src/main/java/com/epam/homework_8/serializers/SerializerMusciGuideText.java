package com.epam.homework_8.serializers;

import com.epam.homework_8.ser_models.MusicGuideProxy;

import java.util.Optional;

public class SerializerMusciGuideText implements Serializer<MusicGuideProxy> {

    @Override
    public Optional<MusicGuideProxy> deserialize(String destinationFile) {
        return null;
    }

    @Override
    public void serialize(String sourceFile, MusicGuideProxy object) {

    }

}
