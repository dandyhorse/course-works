package com.epam.homework_3.lists;

public interface WeirdList<T> extends Sorted, DoubleIterable<T> {

    void add(T t);

    void add(int index, T t);

    T getFirst();

    T getLast();

    T get(int index);

    T delete(T t);

    T delete(int index);

    void set(int index, T t);

    void clear();

    int size();
}
