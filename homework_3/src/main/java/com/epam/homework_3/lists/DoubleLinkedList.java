package com.epam.homework_3.lists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T extends Comparable<T>> implements WeirdList<T> {

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

    @SuppressWarnings("Unused method")
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DoubleLinkedList<T> list = new DoubleLinkedList<>();
//        for (Node x = first; x != null; x = x.next)
//            return super.clone();
        return list;
    }

    @Override
    public void add(T t) {
        linkLast(t);
    }

    private void linkLast(T t) {
        Node<T> node = this.last;
        Node<T> newNode = new Node<>(node, t, first);
        last = newNode;
        if (node == null)
            first = newNode;
        else
            node.next = newNode;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new JustIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new TwoWaysListIterator();
    }

    private class JustIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return (current != size());
        }

        @Override
        public T next() {
            return get(current++);
        }

    }

    private class TwoWaysListIterator extends JustIterator implements ListIterator<T> {

        private int current = size();

        @Override
        public boolean hasPrevious() {
            return (current != -1);
        }

        @Override
        public T previous() {
            return get(current--);
        }

        @Override
        public int nextIndex() {
            return current + 1;
        }

        @Override
        public int previousIndex() {
            return current - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException("set");
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException("add");
        }
    }

    @Override
    public void add(int index, T t) {
        if (index == size) {
            linkLast(t);
        } else {
            linkBefore(t, node(index));
        }
    }

    private void linkBefore(T t, Node<T> node) {
        //TODO link circle

        final Node<T> pred = node.previous;
        final Node<T> newNode = new Node<>(pred, t, node);
        node.previous = newNode;

        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;

    }

    private Node<T> node(int index) {
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.previous;
            return x;
        }
    }

    @Override
    public T getFirst() {
        final Node<T> first = this.first;
        if (first == null)
            throw new NoSuchElementException();
        return first.value;
    }

    @Override
    public T getLast() {
        final Node<T> last = this.last;
        if (last == null)
            throw new NoSuchElementException();
        return last.value;
    }

    @Override
    public T get(int index) {
        if (size() < 2) {
            return getFirst();
        } else {
            return node(index).value;
        }
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
        if (size() > index) {
            Node<T> node = node(index);
            node.value = t;
        }
    }

    @Override
    public void clear() {
        for (Node<T> x = first; x != null; ) {
            Node<T> next = x.next;
            x.value = null;
            x.next = null;
            x.value = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public <R extends Comparable<R>> DoubleLinkedList<R> map(WeirdFunction<? extends R, ? super T> function) {
        if (function == null)
            throw new NullPointerException();
        DoubleLinkedList<R> newList = new DoubleLinkedList<>();
        Node<T> node = this.first;
        for (int i = 0; i < size(); i++) {
            R r = function.apply(node.value);
            newList.add(r);
            node = node.next;
        }
        return newList;
    }


    @Override
    public void sort() {
        if (size < 2)
            return;
        cocktailSort();
    }

    /**
     * Sorting
     */
    private void cocktailSort() {
        Node<T> node = this.first;
        int n = size();
        int i, c = 0;
        do {
            for (i = 0; i < size() - 2; i++) {
                if (node.value.compareTo(node.next.value) > 0) {
                    swap(node, node.next);
                }
                node = node.next;
            }
            n--;
            node = this.last;
            for (i = size(); i > 1; i--) {
                if (node.value.compareTo(node.previous.value) < 0) {
                    swap(node, node.previous);
                }
                node = node.previous;
            }
            c++;
        } while (n != 0 && c != size() - 2);
    }

    /**
     * swap between previous and next nodes
     */
    private void swap(Node<T> xNode, Node<T> yNode) {
        T value = xNode.value;
        xNode.value = yNode.value;
        yNode.value = value;
    }

    @Override
    public void sort(Comparator<T> comp) {
        if (size < 2)
            return;
        if (comp == null)
            throw new NullPointerException("Comparator is not definitions");
        cocktailSort(comp);
    }

    private void cocktailSort(Comparator<T> comp) {
        Node<T> node = this.first;
        int n = size();
        int i, c = 0;
        do {
            for (i = 0; i < size() - 2; i++) {
                if (comp.compare(node.value, node.next.value) > 0) {
                    swap(node, node.next);
                }
                node = node.next;
            }
            n--;
            node = this.last;
            for (i = size(); i > 1; i--) {
                if (comp.compare(node.value, node.previous.value) < 0) {
                    swap(node, node.previous);
                }
                node = node.previous;
            }
            c++;
        } while (n != 0 && c != size() - 2);
    }

    @Override
    public String toString() {
        Node<T> node = this.first;
        String toString = node.value.toString();
        for (int i = 0; i < size() - 1; i++) {
            toString += ", " + node.next.value;
            node = node.next;
        }
        return toString;
    }

}
