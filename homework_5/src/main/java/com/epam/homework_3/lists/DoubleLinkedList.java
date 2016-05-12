package com.epam.homework_3.lists;

import com.epam.homework_3.lists.interfaces.WeirdFunction;
import com.epam.homework_3.lists.interfaces.WeirdList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T extends Comparable<? super T>> implements WeirdList<T> {

    private int size;
    private Node<T> last;
    private Node<T> first;

    private static class Node<T> {
        T value;
        Node<T> previous;
        Node<T> next;

        Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }

    }

    @SuppressWarnings("unused")
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new UnsupportedOperationException("clone");
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

        protected int currentIndex;
        protected Node<T> currentNode;

        JustIterator() {
            this.currentIndex = 0;
            this.currentNode = node(currentIndex);
        }

        @Override
        public boolean hasNext() {
            return (currentIndex < size);
        }

        @Override
        public T next() {
            Node<T> result = this.currentNode;
            currentNode = currentNode.next;
            currentIndex++;
            return result.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

    }

    private class TwoWaysListIterator extends JustIterator implements ListIterator<T> {

        TwoWaysListIterator() {
            this.currentIndex = size;
            this.currentNode = node(currentIndex);
        }

        @Override
        public boolean hasPrevious() {
            return (currentIndex > 0);
        }

        @Override
        public T previous() {
            Node<T> result = currentNode;
            currentIndex--;
            currentNode = result.previous;
            return result.value;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("nextIndex");
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("previousIndex");
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
    public void add(T t) {
        isObjectNull(t, "try to call - add(null)");
        if (size() == 0)
            linkFirstElement(t);
        else
            linkLast(t);
    }

    private void linkFirstElement(T t) {
        Node<T> newNode = new Node<>(last, t, first);
        last = newNode;
        first = newNode;
        size++;
    }

    private void linkLast(T t) {
        Node<T> last = this.last;
        Node<T> newNode = new Node<>(last, t, first);
        this.last = newNode;
        last.next = newNode;
        first.previous = newNode;
        size++;
    }

    @Override
    public void add(int index, T t) {
        checkIndex(index);
        isObjectNull(t, "try to call - delete( " + index + ", null)");
        if (index == size) {
            linkLast(t);
        } else {
            linkBefore(t, node(index));
        }
    }

    private void linkBefore(T t, Node<T> node) {
        final Node<T> previous = node.previous;
        final Node<T> newNode = new Node<>(previous, t, node);
        node.previous = newNode;

        if (previous == null)
            first = newNode;
        else
            previous.next = newNode;
        size++;
    }

    private Node<T> node(int index) {
        if (index < (size >> 1)) {
            Node<T> node = first;
            for (int i = 0; i < index; i++)
                node = node.next;
            return node;
        } else {
            Node<T> node = last;
            for (int i = size - 1; i > index; i--)
                node = node.previous;
            return node;
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

    private void checkIndex(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("index is not between zero and size of list");
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return node(index).value;
    }

    @Override
    public void delete(T t) {
        isObjectNull(t, "try to call - delete(null)");
        for (Node<T> node = first; node != last; node = node.next) {
            if (t.equals(node.value)) {
                unlink(node);
                return;
            }
        }
    }

    @Override
    public void delete(int index) {
        checkIndex(index);
        if (index == size()) {
            unlinkLast();
            return;
        }
        if (index == 0) {
            unlinkFirst();
            return;
        }
        unlink(node(index));
    }

    private void unlinkFirst() {
        Node<T> node = this.first;
        Node<T> next = this.first.next;
        if (next == first) {
            last = null;
            first = null;
        } else {
            first = next;
            last.next = next;
            next.previous = last;
        }
        fillNullNode(node);
        size--;
    }

    private void fillNullNode(Node<T> node) {
        node.value = null;
        node.previous = null;
        node.next = null;
    }

    private void unlinkLast() {
        Node<T> node = this.last;
        Node<T> previous = this.last.previous;
        if (previous == last) {
            last = null;
            first = null;
        } else {
            last = previous;
            first.previous = previous;
            previous.next = first;
        }
        fillNullNode(node);
        size--;
    }

    private void unlink(Node<T> node) {
        Node<T> next = node.next;
        Node<T> previous = node.previous;
        previous.next = next;
        next.previous = previous;
        fillNullNode(node);
        size--;
    }

    @Override
    public void set(int index, T t) {
        checkIndex(index);
        Node<T> node = node(index);
        node.value = t;
    }

    @Override
    public void clear() {
        for (Node<T> node = first; node != null; ) {
            Node<T> next = node.next;
            fillNullNode(node);
            node = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public <R extends Comparable<? super R>> DoubleLinkedList<R> map(WeirdFunction<? super T, ? extends R> function) {
        isObjectNull(function, "Function is not defined");
        DoubleLinkedList<R> newList = new DoubleLinkedList<>();
        for (T t : this) {
            R r = function.apply(t);
            newList.add(r);
        }
        return newList;
    }

    private void isObjectNull(Object o, String message) {
        if (o == null)
            throw new NullPointerException(message);
    }

    @Override
    public void sort() {
        if (size < 2)
            return;
        cocktailSort();
    }

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
    public void sort(Comparator<? super T> comp) {
        isObjectNull(comp, "Comparator is not defined");
        if (size < 2)
            return;
        cocktailSort(comp);
    }

    private void cocktailSort(Comparator<? super T> comp) {
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
        String toString = "";
        if (size() == 0) {
            toString = "Empty List";
        } else {
            Node<T> node = this.first;
            toString += node.value.toString();
            for (int i = 0; i < size() - 1; i++) {
                toString += ", " + node.next.value;
                node = node.next;
            }
        }
        return toString;
    }

}
