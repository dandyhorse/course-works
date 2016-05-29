package com.epam.homework_8.serializers;

import java.io.Serializable;
import java.util.Optional;

public interface Serializer<T extends Serializable> {

    Optional<T> deserialize(String destinationFile);

    void serialize(String sourceFile, T t);

}
