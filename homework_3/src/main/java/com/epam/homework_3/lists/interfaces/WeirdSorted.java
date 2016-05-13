package com.epam.homework_3.lists.interfaces;

import java.util.Comparator;

public interface WeirdSorted<T> {

    void sort();

    void sort(Comparator<? super T> comp);

}
