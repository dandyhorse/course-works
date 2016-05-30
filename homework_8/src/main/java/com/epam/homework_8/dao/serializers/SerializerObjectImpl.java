package com.epam.homework_8.dao.serializers;

import com.epam.homework_8.dao.serializers.interfaces.Serializer;

import java.io.*;

@SuppressWarnings("unused")
public class SerializerObjectImpl<T extends Serializable> implements Serializer<T> {

    public T deserialize(String sourceFile) {
        T object = null;
        try (FileInputStream is = new FileInputStream(sourceFile);
             BufferedInputStream bis = new BufferedInputStream(is);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            object = (T) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
        if (object == null) {
            throw new RuntimeException("Can't read a non-null object from file");
        }
        return object;
    }

    public void serialize(String destinationFile, T t) {
        try (FileOutputStream os = new FileOutputStream(destinationFile);
             BufferedOutputStream bos = new BufferedOutputStream(os);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(t);
            oos.flush();

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
