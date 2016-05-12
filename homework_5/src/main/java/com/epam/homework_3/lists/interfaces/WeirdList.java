package com.epam.homework_3.lists.interfaces;

import com.epam.homework_3.lists.DoubleLinkedList;

import java.util.ListIterator;

public interface WeirdList<T> extends WeirdSorted<T>, Iterable<T>, Cloneable {

    void add(T t);

    ListIterator<T> listIterator();

    void add(int index, T t);

    T getFirst();

    T getLast();

    T get(int index);

    void delete(T t);

    void delete(int index);

    void set(int index, T t);

    void clear();

    int size();

    <R extends Comparable<? super R>> DoubleLinkedList<R> map(WeirdFunction<? super T, ? extends R> function);

}
