package com.epam.homework_3.lists;

import java.util.Iterator;

public class DoubleLinkedList<T extends Comparable<T>> implements Cloneable, WeirdList<T>, DoubleIterable<T> {

    private int size;
    private Node<T> last;
    private Node<T> first;

    private static class Node<T> {
        T value;
        Node<T> previous;
        Node<T> next;

        public Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }

    /**
     * Интерфейс для передачи двух функций в методах hasNext и next
     * во внешний интерфейс Iterator
     *
     * @param <T> типизруемый объект
     */
    private interface DirectionInterface<T> {
        T next(int pointer);

        boolean hasNext(int pointer);
    }

    /**
     * Класс, при создании которого, определяется
     * с какой стороны будет проходить итерация( с конца или с начала),
     * а также функции как проходить по итератору.
     */
    private class DoubleIterator implements Iterator<T> {

        private int pointer;
        private DirectionInterface<T> direction;

        DoubleIterator(int pointer, DirectionInterface<T> direction) {
            this.pointer = pointer;
            this.direction = direction;
        }

        @Override
        public boolean hasNext() {
            return direction.hasNext(pointer);
        }

        @Override
        public T next() {
            return direction.next(pointer);

        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleIterator(0, new DirectionInterface<T>() {

            @Override
            public T next(int pointer) {
//                return get(pointer + 1);
                return null;
            }

            @Override
            public boolean hasNext(int pointer) {
                return pointer != size;
            }
        });
    }

    @Override
    public Iterator<T> backwardIterator() {
        return new DoubleIterator(size(), new DirectionInterface<T>() {

            @Override
            public T next(int pointer) {
//                return get(pointer - 1);
                return null;
            }

            @Override
            public boolean hasNext(int pointer) {
                return pointer != 0;
            }
        });
    }

    @SuppressWarnings("Unused method")
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DoubleLinkedList<T> list = new DoubleLinkedList<>();
        for (Node x = first; x != null; x = x.next)
            return super.clone();
        return null;
    }

    @Override
    public void add(T t) {
        addLast(t);
    }

    private void addLast(T t) {

    }

    @Override
    public void add(int index, T t) {
        if (index > size) {
            addLast(t);
        }
        if (size == 0) {
            first.value = t;
            size++;
        }
//        if () {
//            //TODO
//        }
    }

    @Override
    public T getFirst() {
        return first.value;
    }

    @Override
    public T getLast() {
        return last.value;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T delete(T t) {
        return null;
    }

    @Override
    public T delete(int index) {
        return null;
    }

    @Override
    public void set(int index, T t) {

    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {

    }


}
