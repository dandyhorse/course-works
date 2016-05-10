package com.epam.homework_3.lists;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by User on 09.05.2016.
 */
public class DoubleLinkedListTest {

    private static final Logger logger = LoggerFactory.getLogger(DoubleLinkedListTest.class);
    private WeirdList<Integer> list;
    private Integer first = 2;
    private Integer last = 8;
    private Integer element = 100;

    @Before
    public void setUp() throws Exception {
        list = new DoubleLinkedList<>();
        list.add(first);
        list.add(new Random().nextInt(20));
        list.add(new Random().nextInt(20));
        list.add(new Random().nextInt(20));
        list.add(last);
    }

    @Test
    public void testClone() throws Exception {

    }

    @Test
    public void add() throws Exception {
        list.add(element);
        assertTrue(list.size() == 6);
        assertEquals(element, list.get(6));
        logger.info("test add() - success\n{}. With adding {}", list, element);
    }

    @Test
    public void iterator() throws Exception {
        int j = 0;
        for (Integer i : list) {
            assertNotNull(i);
            assertEquals(i, list.get(j++));
        }
        logger.info("test iterator() - success");
    }

    @Test
    public void listIterator() throws Exception {
        int j = list.size() - 1;
        ListIterator<Integer> listIterator = list.listIterator();
        assertFalse(listIterator.hasNext());
        while (listIterator.hasPrevious()) {
            Integer previous = listIterator.previous();
            assertNotNull(previous);
            assertEquals(previous, list.get(j--));
        }
        logger.info("test listIterator()) - success");
    }

    @Test
    public void addByIndex() throws Exception {
        list.add(3, element);
        Integer addedElement = list.get(3);
        assertEquals(addedElement, element);
    }

    @Test
    public void getFirst() throws Exception {
        Integer first = list.getFirst();
        assertEquals(first, this.first);
    }

    @Test
    public void getLast() throws Exception {
        Integer last = list.getLast();
        assertTrue(last.equals(this.last));
    }

    @Test
    public void get() throws Exception {
        Integer integer_0 = list.get(0);
        Integer integer_5 = list.get(5);
        assertEquals(integer_0, first);
        assertEquals(integer_5, last);
    }

    @Test
    public void delete() throws Exception {
        list.set(3, element);
        assertEquals(list.size(), 5);
        list.delete(element);
        assertEquals(list.size(), 4);
        Integer notThatElement = list.get(3);
        assertNotEquals(notThatElement, element);
    }

    @Test
    public void getLastByIndex() throws Exception {
        Integer last = list.get(5);
        assertEquals(this.last, last);
    }

    @Test
    public void getFirstByIndex() throws Exception {
        Integer first = list.get(0);
        assertEquals(this.first, first);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteAll() throws Exception {
        System.out.println(list);
        for (int i = 0; i < 5; i++) {
            list.delete(0);
        }
        System.out.println(list);
        list.getFirst(); //NoSuchElementException

    }


    @Test
    public void deleteByIndex() throws Exception {
        list.set(3, element);
        assertEquals(list.size(), 5);
        list.delete(3);
        assertEquals(list.size(), 4);
        Integer notThatElement = list.get(3);
        assertNotEquals(notThatElement, element);
    }

    @Test
    public void set() throws Exception {
        list.set(3, element);
        Integer integer = list.get(3);
        assertEquals(integer, element);
    }

    @Test(expected = NoSuchElementException.class)
    public void clear() throws Exception {
        list.clear();
        assertEquals(list.size(), 0);
        assertNull(list.getFirst()); //NoSuchElementException
    }

    @Test
    public void sort() throws Exception {
        list.sort();
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) < list.get(i + 1));
        }
    }

    @Test
    public void sortWithComparator() throws Exception {
        list.sort((o1, o2) -> {
            Integer i = o1 * (-1);
            return (i).compareTo(o2 * -1);
        });
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) > list.get(i + 1));
        }
    }

    @Test
    public void map() throws Exception {
        WeirdFunction<String, Integer> wFunc = Object::toString;
        DoubleLinkedList<String> stringList = list.map(wFunc);
        assertEquals(stringList.getLast().getClass(), String.class);
        assertEquals(stringList.getLast(), list.getLast().toString());
        assertEquals(stringList.getFirst(), list.getFirst().toString());
        assertEquals(stringList.size(), list.size());
    }

    @Test
    public void isListSelfContained() throws Exception {
        DoubleLinkedList<Integer> dList = (DoubleLinkedList<Integer>) list;
        DoubleLinkedList.Node<Integer> firstNode = dList.node(0);
        DoubleLinkedList.Node<Integer> lastNode = dList.node(5);

        assertEquals(firstNode.value, lastNode.next.value);
        assertEquals(firstNode.previous.value, lastNode.value);
    }
}