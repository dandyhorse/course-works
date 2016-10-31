package net.solovev.anton;

import java.util.Iterator;

/**
 * @author Anton Solovev
 * @since 11/27/2016.
 */
public interface List<E> extends Iterable<E> {

	E get(int index);

	void add(int index, E element);

	boolean add(E e);

	E set(int index, E element);

	int size();

	E remove(int index);

	void clear();

	default boolean contains(Object o) {
		Iterator<E> it = iterator();
		if (o == null) {
			while (it.hasNext())
				if (it.next() == null)
					return true;
		} else {
			while (it.hasNext())
				if (o.equals(it.next()))
					return true;
		}
		return false;
	}

	default boolean isEmpty() {
		return size() == 0;
	}
}
