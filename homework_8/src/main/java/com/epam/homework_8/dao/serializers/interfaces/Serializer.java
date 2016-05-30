package com.epam.homework_8.dao.serializers.interfaces;

import java.io.Serializable;

public interface Serializer<T extends Serializable> {

    T deserialize(String sourceFile);

    void serialize(String destinationFile, T t);

}
