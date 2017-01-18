package com.epam.homework_2.algorithms;

/**
 * Created by User on 01.05.2016.
 */
public abstract class AbstractSortAndSearch {

    public abstract void sort(int[] arr);

    public abstract int search(int[] arr, int element);

    /**
     * Swaps x[a] with x[b].
     */
    protected void swap(int[] x, int a, int b) {
        int i = x[a];
        x[a] = x[b];
        x[b] = i;
    }
}
