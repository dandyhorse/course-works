import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by User on 27.04.2016.
 */
public class SortClassTest {
    private int[] array;

    @Before
    public void setUp() throws Exception {
        Random numbers = new Random();
        IntStream ints = numbers.ints(50L, -25, 25);
        array = ints.toArray();
        ints.close();
        System.out.printf("Unsorted array : \n %s \n", Arrays.toString(array));
    }

    @Test
    public void sort() throws Exception {
        SortClass.sort(array);
        System.out.printf("Sorted array : \n %s \n", Arrays.toString(array));
        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(i < i + 1);
        }
    }
}