package com.epam.homework_3.lists;

public class DoubleLinkedList<T> implements Cloneable, WeirdList<T> {

    private int size;
    private Node last;
    private Node first;

    private class Node implements Comparable<T> {
        Node previous;
        Node next;
        T value;

        @Override
        public int compareTo(T o) {
            return 0;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void add(T t) {

    }

    @Override
    public void add(int index, T t) {

    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
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
        return 0;
    }

    @Override
    public WeirdList<T> getBackwardLIst() {
        return null;
    }

    @Override
    public WeirdList<T> getForwardLIst() {
        return null;
    }

    @Override
    public void sort() {

    }


}
