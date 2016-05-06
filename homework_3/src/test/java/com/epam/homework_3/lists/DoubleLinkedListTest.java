package com.epam.homework_3.lists;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by User on 06.05.2016.
 */
public class DoubleLinkedListTest {

    @Test
    public void iterator() throws Exception {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    @Test
    public void backwardIterator() throws Exception {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        Iterator<Integer> iterator = list.backwardIterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            System.out.println(i);
        }
    }
}