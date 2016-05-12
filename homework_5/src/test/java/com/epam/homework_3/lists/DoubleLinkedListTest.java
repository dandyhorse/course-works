package com.epam.homework_3.lists;

import com.epam.homework_3.lists.interfaces.WeirdList;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ConcurrentModificationException;
import java.util.Random;

import static org.junit.Assert.*;

public class DoubleLinkedListTest {

    private static final Logger logger = LoggerFactory.getLogger(DoubleLinkedListTest.class);
    private WeirdList<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new DoubleLinkedList<>();
        list.add(new Random().nextInt());
        list.add(new Random().nextInt());
    }

    @Test
    public void mapTest() throws Exception {
        DoubleLinkedList<String> stringList = list.map(Object::toString);
        assertEquals(stringList.getLast().getClass(), String.class);
        assertEquals(stringList.getLast(), list.getLast().toString());
        assertEquals(stringList.getFirst(), list.getFirst().toString());
        assertEquals(stringList.size(), list.size());
    }

    //    ConcurrentModification tests
    @Test(expected = ConcurrentModificationException.class)
    public void test() throws Exception {

    }

    //    ArrayIndexOutOfBoundsException tests

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void ttest() throws Exception {

    }
}