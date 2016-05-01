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
    private int[] normalArray;
    private int[] nullArr;
    private int[] emptyArr;

    @Before
    public void setUp() throws Exception {
        Random numbers = new Random();
        IntStream ints = numbers.ints(50L, -25, 25);
        normalArray = ints.toArray();
        nullArr = null;
        emptyArr = new int[10];
        ints.close();
        System.out.printf("Unsorted normalArray : \n %s \n", Arrays.toString(normalArray));
    }

    @Test
    public void sort() throws Exception {
        SortClass.sort(normalArray);
        SortClass.sort(nullArr);    //if will not be crashed test will be passed
        SortClass.sort(emptyArr);   //if will not be crashed test will be passed

        System.out.printf("Sorted normalArray : \n %s \n", Arrays.toString(normalArray));
        for (int i = 0; i < normalArray.length - 1; i++) {
            assertTrue(i < i + 1);
        }

    }
}