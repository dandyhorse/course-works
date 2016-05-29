package com.epam.homework_8.serializers;

import com.epam.homework_8.ser_models.MusicGuideProxy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class SerializerMusciGuideText implements Serializer<MusicGuideProxy> {

    @Override
    public Optional<MusicGuideProxy> deserialize(String destinationFile) {
        return null;
    }

    @Override
    public void serialize(String sourceFile, MusicGuideProxy guide) {
        try (FileWriter os = new FileWriter(sourceFile);
             BufferedWriter bos = new BufferedWriter(os)) {

            guide.writeTextExternal(bos);
            bos.flush();

        } catch (IOException e) {
            e.getMessage();
        }
    }

}
