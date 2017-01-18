package com.epam.homework_6_1.caches.interfaces;

import java.util.Optional;
import java.util.function.Supplier;

public interface Pool<T> {

    void addCache(Supplier<T> sup, Supplier<String> getNameSup);

    Optional<T> getCache(String name);

    void clearCaches();

    boolean isEmpty();

    int size();
}
