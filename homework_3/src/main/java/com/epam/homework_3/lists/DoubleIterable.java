package com.epam.homework_3.lists;

import java.util.Iterator;

/**
 * Created by User on 06.05.2016.
 */
public interface DoubleIterable<T> extends Iterable<T> {
    Iterator<T> backwardIterator();
}
