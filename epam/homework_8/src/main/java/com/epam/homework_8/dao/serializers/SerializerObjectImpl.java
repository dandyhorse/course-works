package com.epam.homework_8.dao.serializers;

import com.epam.homework_8.dao.exceptions.SerializerException;
import com.epam.homework_8.dao.serializers.interfaces.Serializer;

import java.io.*;


public class SerializerObjectImpl<T extends Serializable> implements Serializer<T> {

    public T deserialize(String sourceFile) {
        T object;
        try (FileInputStream is = new FileInputStream(sourceFile);
             BufferedInputStream bis = new BufferedInputStream(is);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            object = (T) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new SerializerException("Read exception", e);
        }
        if (object == null) {
            throw new SerializerException("Can't read a non-null object from file");
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
            throw new SerializerException("Write exception", e);
        }
    }
}
