package net.solovev.anton;


import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Anton Solovev
 * @since 11/27/2016.
 */
public class ArrayListImpl<E> implements List<E> {

	private static final int DEFAULT_CAPACITY = 10;
	private static final Object[] DEFAULT_EMPTY_DATA = {};
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	private Object[] elementData;
	private int size;
	private int modCount = 0;

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError();
		}
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	public ArrayListImpl() {
		elementData = DEFAULT_EMPTY_DATA;
	}

	public ArrayListImpl(E[] elementData) {
		size = elementData.length;
		this.elementData = Arrays.copyOf(elementData, size);
	}

	@Override
	@SuppressWarnings("unchecked")
	public E get(int index) {
		rangeCheck(index);
		return (E) elementData[index];
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		ensureCapacityInternal(size + 1);
		System.arraycopy(
				elementData,
				index,
				elementData,
				index + 1,
				size - index);
		elementData[index] = element;
		size++;
	}

	public boolean add(E e) {
		ensureCapacityInternal(size + 1);
		elementData[size++] = e;
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E set(int index, E element) {
		rangeCheck(index);
		E oldValue = (E) elementData[index];
		elementData[index] = element;
		return oldValue;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E remove(int index) {
		rangeCheck(index);
		modCount++;
		E oldValue = (E) elementData[index];
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(
					elementData,
					index + 1,
					elementData, index,
					numMoved);
		}
		elementData[--size] = null;
		return oldValue;
	}

	@Override
	public void clear() {
		modCount++;
		for (int i = 0; i < size; i++) {
			elementData[i] = null;
		}
		size = 0;
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (elementData == DEFAULT_EMPTY_DATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		modCount++;
		if (minCapacity - elementData.length > 0) {
			grow(minCapacity);
		}
	}

	private void grow(int minCapacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (int) (oldCapacity * 1.5);
		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			newCapacity = hugeCapacity(minCapacity);
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	private void rangeCheck(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private void rangeCheckForAdd(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private String outOfBoundsMsg(int index) {
		return String.format("Index: %d, Size: %s", index, size);
	}

	private class Itr implements Iterator<E> {
		int cursor;
		int lastRet = -1;
		int expectedModCount = modCount;

		public boolean hasNext() {
			return cursor != size;
		}

		@SuppressWarnings("unchecked")
		public E next() {
			checkForComodification();
			int i = cursor;
			if (i >= size)
				throw new NoSuchElementException();
			Object[] elementData = ArrayListImpl.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i + 1;
			return (E) elementData[lastRet = i];
		}

		final void checkForComodification() {
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
		}

		@Override
		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();
			checkForComodification();

			try {
				ArrayListImpl.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}
	}
}
