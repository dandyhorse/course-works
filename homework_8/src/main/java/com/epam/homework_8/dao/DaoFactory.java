package com.epam.homework_8.dao;

import com.epam.homework_8.dao.entity.MusicGuideEntity;
import com.epam.homework_8.dao.exceptions.SerializerException;
import com.epam.homework_8.dao.serializers.SerializerMusicGuideText;
import com.epam.homework_8.dao.serializers.SerializerObjectImpl;
import com.epam.homework_8.dao.serializers.interfaces.Serializer;
import com.epam.homework_8.models.MusicGuide;

public class DaoFactory {

    public static Dao<MusicGuide> getFileSerializer(String outputFile, String string) {
        Serializer<MusicGuideEntity> serializer = null;
        switch (string) {
            case "binary":
                serializer = new SerializerObjectImpl<>();
                break;
            case "text":
                serializer = new SerializerMusicGuideText();
                break;
            default:
                throw new SerializerException("the way of serialization is not defined");
        }
        return new DaoMusicGuideSerializer(outputFile, serializer);
    }


}
