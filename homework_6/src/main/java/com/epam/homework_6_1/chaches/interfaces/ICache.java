package com.epam.homework_6_1.chaches.interfaces;

public interface ICache<T> {
    T get();

    void set(T t);
}
