package com.epam.homework_6_1.caches.interfaces;

public interface ICache {

    String get(Integer k);

    void set(Integer key, String value);

}
