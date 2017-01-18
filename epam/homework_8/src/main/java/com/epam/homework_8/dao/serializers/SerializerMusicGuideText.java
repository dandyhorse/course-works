package com.epam.homework_8.dao.serializers;

import com.epam.homework_8.dao.entity.MusicGuideEntity;
import com.epam.homework_8.dao.exceptions.SerializerException;
import com.epam.homework_8.dao.serializers.interfaces.Serializer;
import com.epam.homework_8.dao.validators.TagValidator;

import java.io.*;

public class SerializerMusicGuideText implements Serializer<MusicGuideEntity> {

    @Override
    public MusicGuideEntity deserialize(String sourceFile) {
        MusicGuideEntity guideEntity = new MusicGuideEntity();
        try (FileReader fw = new FileReader(sourceFile);
             BufferedReader bf = new BufferedReader(fw)) {

            guideEntity.readTextExternal(bf);

        } catch (IOException e) {
            throw new SerializerException("Read exception", e);
        }
        return guideEntity;
    }

    @Override
    public void serialize(String destinationFile, MusicGuideEntity guideEntity) {
        try (FileWriter fw = new FileWriter(destinationFile);
             BufferedWriter bf = new BufferedWriter(fw)) {

            guideEntity.writeTextExternal(bf);
            bf.flush();

        } catch (IOException e) {
            throw new SerializerException("Write exception", e);
        }
    }

}
