import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by User on 27.04.2016.
 */
public class SearchClassTest {
    private int[] array;
    private int element;

    @Before
    public void setUp() throws Exception {
        Random numbers = new Random();
        IntStream ints = numbers.ints(50L, -25, 25);
        element = numbers.nextInt(25);
        array = ints.toArray();
        ints.close();
        array[25] = element;
        SortClass.sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void search() throws Exception {
        int foundedIndex = SearchClass.search(array, element);
        System.out.printf("Element is  %d and his Index : %d\n", element, foundedIndex);
        assertTrue(element == array[foundedIndex]);
    }
}