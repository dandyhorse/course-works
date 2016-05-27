package com.epam.homework_8.serializers;

import com.epam.homework_8.ser_models.MusicGuideProxy;

import java.io.*;
import java.util.Optional;


public class SerializerMusicGuide implements Serializer<MusicGuideProxy> {

    @Override
    public Optional<MusicGuideProxy> deserialize(String destinationFile) {
        MusicGuideProxy guide = new MusicGuideProxy();
        Optional<MusicGuideProxy> optional = Optional.empty();
        try (FileInputStream is = new FileInputStream(destinationFile);
             BufferedInputStream bis = new BufferedInputStream(is);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            guide.readExternal(ois);
            optional = Optional.of(guide);

        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
        return optional;
    }

    @Override
    public void serialize(String sourceFile, MusicGuideProxy guide) {
        try (FileOutputStream os = new FileOutputStream(sourceFile);
             BufferedOutputStream bos = new BufferedOutputStream(os);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            guide.writeExternal(oos);
            oos.flush();

        } catch (IOException e) {
            e.getMessage();
        }
    }

}
