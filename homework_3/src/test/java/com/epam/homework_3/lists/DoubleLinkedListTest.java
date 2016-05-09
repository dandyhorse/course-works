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
        assertTrue(element.equals(list.get(6)));
    }

    @Test
    public void iterator() throws Exception {
        for (Integer i : list) {
            assertNotNull(i);
            System.out.println(i.toString());
        }
    }

    @Test
    public void listIterator() throws Exception {
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Integer next = listIterator.next();
            assertNotNull(next);
            System.out.println(next);
        }
        while (listIterator.hasPrevious()) {
            Integer previous = listIterator.previous();
            assertNotNull(previous);
            System.out.println(previous);
        }
    }

    @Test
    public void addByIndex() throws Exception {
        list.add(3, element);
        Integer integer = list.get(3);
        assertTrue(integer.equals(element));
        System.out.println(integer);
    }

    @Test
    public void getFirst() throws Exception {
        Integer first = list.getFirst();
        assertTrue(first.equals(this.first));
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
        assertTrue(integer_0.equals(first));
        assertTrue(integer_5.equals(last));
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void deleteByIndex() throws Exception {

    }

    @Test
    public void set() throws Exception {
        list.set(3, element);
        Integer integer = list.get(3);
        assertTrue(integer.equals(element));
    }

    @Test(expected = NoSuchElementException.class)
    public void clear() throws Exception {
        list.clear();
        assertTrue(list.size() == 0);
        assertNull(list.getFirst());
        assertNull(list.getLast());
    }

    @Test
    public void sort() throws Exception {
        list.sort();
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) < list.get(i + 1));
        }
        System.out.println(list);
    }

    @Test
    public void sortWithComparator() throws Exception {
        list.sort((o1, o2) -> {
            Integer i = o1 * (-1);
            return (i).compareTo(o2 * -1);
        });
        System.out.println(list);
    }

    @Test
    public void map() throws Exception {
        WeirdFunction<String, Integer> wFunc = Object::toString;
        DoubleLinkedList<String> stringList = list.map(wFunc);
        assertTrue(stringList.getLast().getClass().equals(String.class));
        System.out.println(stringList);
    }

    @Test
    public void isListCircle() throws Exception {
        DoubleLinkedList<Integer> dList = (DoubleLinkedList<Integer>) list;
        DoubleLinkedList.Node<Integer> firstNode = dList.node(0);
        DoubleLinkedList.Node<Integer> lastNode = dList.node(5);

        assertTrue(firstNode.value.equals(lastNode.next.value));
        assertTrue(firstNode.previous.value.equals(lastNode.value));
    }
}