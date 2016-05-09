package com.epam.homework_3.lists;

import java.util.ListIterator;

public interface WeirdList<T> extends Sorted<T>, Iterable<T>, Cloneable {

    void add(T t);

    ListIterator<T> listIterator();

    void add(int index, T t);

    T getFirst();

    T getLast();

    T get(int index);

    T delete(T t);

    T delete(int index);

    void set(int index, T t);

    void clear();

    int size();

    <R extends Comparable<R>> DoubleLinkedList<R> map(WeirdFunction<? extends R, ? super T> function);

}
