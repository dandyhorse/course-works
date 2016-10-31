package net.solovev.anton;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anton Solovev
 * @since 11/28/2016.
 */
public class ArrayListImplTest {

	private List<Integer> testList;
	private static Integer[] data = new Integer[]{1, 2, 3, 4};

	@Before
	public void setUp() throws Exception {
		testList = new ArrayListImpl<>(data);
	}

	@Test
	public void testGet() throws Exception {
		int index = 2;

		Integer i = testList.get(index);
		assertThat(i, is(data[index]));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOutOfIndex() throws Exception {
		int index = data.length + 10;
		testList.get(index);
	}

	@Test
	public void testAdd() throws Exception {
		Integer element = 205;
		int oldLen = testList.size();

		boolean isAdded = testList.add(element);
		assertThat(oldLen, is(testList.size() - 1));
		assertThat(isAdded, is(true));
	}

	@Test
	public void testAddWithIndex() throws Exception {
		Integer element = 205;
		int index = 2;

		int oldLen = testList.size();
		testList.add(index, element);
		assertThat(oldLen, is(testList.size() - 1));
		assertThat(testList.get(index), is(element));
	}

	@Test
	public void testSet() throws Exception {
		Integer element = 205;
		int index = 2;

		int oldLen = testList.size();
		testList.set(index, element);
		assertThat(oldLen, is(testList.size()));
		assertThat(testList.get(index), is(element));
	}

	@Test
	public void testContains() throws Exception {
		int index = 3;

		Integer i = data[index];
		boolean isContains = testList.contains(i);
		boolean isNotContains = testList.contains(new Object());
		assertThat(isContains, is(true));
		assertThat(isNotContains, is(false));
	}

	@Test
	public void testRemove() throws Exception {
		int index = 2;
		int oldLen = testList.size();

		Integer removed = testList.remove(index);
		assertThat(oldLen, is(testList.size() + 1));
		assertThat(removed, is(data[index]));
	}

	@Test
	public void testRemoveInIterator() throws Exception {
		int index = 2;
		Integer removed = testList.get(index);
		int oldLen = testList.size();

		Iterator<Integer> iterator = testList.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (next.equals(removed)) {
				iterator.remove();
			}
		}
		assertThat(testList.size(), is(oldLen - 1));
		assertThat(testList.contains(removed), is(false));
	}

	@Test(expected = ConcurrentModificationException.class)
	public void testChangeWhileIterating() throws Exception {
		for (int i : testList) {
			testList.remove(0);
		}
	}

	@Test
	public void testForwardAdding() throws Exception {
		Random random = new Random();
		int iterations = 10000;
		int oldSize = testList.size();
		for (int i = 0; i < iterations; i++) {
			testList.add(random.nextInt());
			assertThat(testList.size(), is(oldSize + i + 1));
		}
	}

}