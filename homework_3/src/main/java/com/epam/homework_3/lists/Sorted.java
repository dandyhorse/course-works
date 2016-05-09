package com.epam.homework_3.lists;

import java.util.Comparator;

public interface Sorted<T> {

    void sort();

    void sort(Comparator<T> comp);

}
