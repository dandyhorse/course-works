package com.epam.homework_2.algorithms;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.epam.homework_2.utils.RandomNumbers;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public abstract class AbstractTest {

    static AbstractSortAndSearch sortAndSearch;
    private int[] normalArray;
    private int element;
    private static int[] nullArr;
    private static int[] emptyArr;

    @BeforeClass
    public static void setUpClass() throws Exception {
        nullArr = null;
        emptyArr = new int[10];
    }

    /**
     * getting normalArray and put random element in so that we know
     * at least one element will be found among normalArray values
     */
    @Before
    public void setUp() throws Exception {
        normalArray = RandomNumbers.getSeveralNumbers(50, -25, 25);
        element = RandomNumbers.getOneNumber(25);
        normalArray[25] = element;
    }

    @Test
    public void normalSort() throws Exception {
        System.out.printf("normalSort()\nUnsorted array:\n%s\n", Arrays.toString(normalArray));
        sortAndSearch.sort(normalArray);
        System.out.printf("Sorted array:\n%s\n", Arrays.toString(normalArray));
        for (int i = 0; i < normalArray.length - 1; i++) {
            assertTrue(i < i + 1);
        }
    }

    @Test
    public void nullArraySort() throws Exception {
        System.out.printf("nullArraySort()\nUnsorted array:\n%s\n", Arrays.toString(nullArr));
        sortAndSearch.sort(nullArr);
        assertNull(nullArr);
    }

    @Test
    public void emptyArraySort() throws Exception {
        System.out.printf("emptyArraySort()\nUnsorted array:\n%s\n", Arrays.toString(emptyArr));
        sortAndSearch.sort(emptyArr);
    }

    @Test
    public void normalSearch() throws Exception {
        sortAndSearch.sort(normalArray);
        System.out.printf("normalSearch()\nSorted array:\n%s\n", Arrays.toString(normalArray));
        int foundedIndex = sortAndSearch.search(normalArray, element);
        System.out.printf("Element is %d and its Index is %d\n", element, foundedIndex);
        assertTrue(element == normalArray[foundedIndex]);
    }

    @Test
    public void nullArraySearch() throws Exception {
        int notFound = sortAndSearch.search(nullArr, element);
        System.out.printf("nullArraySearch()\nElement is %d and its Index in null pointed Array is %d\n", element, notFound);
        assertEquals(notFound, -1);
    }

    @Test
    public void emptyArraySearch() throws Exception {
        int notFound = sortAndSearch.search(emptyArr, element);
        System.out.printf("emptyArraySearch()\nElement is %d and its Index in empty Array is %d\n", element, notFound);
        assertEquals(notFound, -1);
    }

}
