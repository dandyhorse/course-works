package com.epam.homework_8.serializers;

import java.io.*;
import java.util.Optional;

public class SerializerObjImpl<T extends Serializable> implements Serializer<T> {

    public Optional<T> deserialize(String destinationFile) {
        T object;
        Optional<T> optional = Optional.empty();
        try (FileInputStream is = new FileInputStream(destinationFile);
             BufferedInputStream bis = new BufferedInputStream(is);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            object = (T) ois.readObject();
            optional = Optional.of(object);

        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
        return optional;
    }

    public void serialize(String sourceFile, T object) {
        try (FileOutputStream os = new FileOutputStream(sourceFile);
             BufferedOutputStream bos = new BufferedOutputStream(os);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(object);
            oos.flush();

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
