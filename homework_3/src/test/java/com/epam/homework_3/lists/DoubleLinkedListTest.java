package com.epam.homework_3.lists;

import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by User on 09.05.2016.
 */
public class DoubleLinkedListTest {

    private WeirdList<Integer> list;
    private Integer first = 2;
    private Integer last = 8;
    private Integer element = 100;

    @Before
    public void setUp() throws Exception {
        list = new DoubleLinkedList<>();
        list.add(first);
        list.add(1);
        list.add(3);
        list.add(7);
        list.add(last);
    }

    @Test
    public void testClone() throws Exception {

    }

    @Test
    public void add() throws Exception {
        list.add(element);
        assertTrue(list.size() == 6);
        System.out.println(list);
        assertEquals(element, list.get(6));
    }

    @Test
    public void iterator() throws Exception {
        for (Integer i : list) {
            assertNotNull(i);
            System.out.printf("%s, ", i.toString());
        }
    }

    @Test
    public void listIterator() throws Exception {
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Integer next = listIterator.next();
            assertNotNull(next);
            System.out.printf("%d, ", next);
        }
        while (listIterator.hasPrevious()) {
            Integer previous = listIterator.previous();
            assertNotNull(previous);
            System.out.printf("%d, ", previous);
        }
    }

    @Test
    public void addByIndex() throws Exception {
        list.add(3, element);
        Integer integer = list.get(3);
        assertEquals(integer, element);
        System.out.println(integer);
    }

    @Test
    public void getFirst() throws Exception {
        Integer first = list.getFirst();
        assertEquals(first, this.first);
        System.out.println(first);
    }

    @Test
    public void getLast() throws Exception {
        Integer last = list.getLast();
        assertTrue(last.equals(this.last));
        System.out.println(last);
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
        System.out.println(list);
        assertEquals(list.size(), 5);
        list.delete(element);
        System.out.println(list);
        assertEquals(list.size(), 4);
        Integer integer = list.get(3);
        assertNotEquals(integer, element);
    }

    @Test
    public void getLastByIndex() throws Exception {
        Integer last = list.get(5);
        System.out.println(last);
        assertEquals(this.last, last);
    }

    @Test
    public void getFirstByIndex() throws Exception {
        Integer first = list.get(0);
        System.out.println(first);
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
        System.out.println(list);
        assertEquals(list.size(), 5);
        list.delete(3);
        System.out.println(list);
        assertEquals(list.size(), 4);
        Integer integer = list.get(3);
        assertNotEquals(integer, element);
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
        assertTrue(stringList.getLast().getClass().equals(String.class));
        System.out.println(stringList);
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