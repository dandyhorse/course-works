package com.epam.homework_2.algorithms;

public class CocktailAndInterpolation extends AbstractSortAndSearch {

    @Override
    public void sort(int[] array) {
        if ((array == null) || (array.length < 2))
            return;
        coctailSort(array);
    }

    @Override
    public int search(int[] arr, int element) {
        if ((arr == null) || arr.length == 0)
            return -1;  // key not found.
        if (arr.length == 1)
            return arr[0]; // key found
        return interpolationSearch(arr, element);
    }

    /**
     * @param array   must be sorted
     * @param element is a key looked for
     * @return an Index of the element
     */
    private int interpolationSearch(int[] array, int element) {
        int mid;
        int low = 0;
        int high = array.length - 1;
        while (array[low] < element && array[high] > element) {
            mid = getLinearInterpolation(array, element, low, high);
            if (array[mid] < element)
                low = mid + 1;
            else if (array[mid] > element)
                high = mid - 1;
            else
                return mid;
        }
        if (array[low] == element)
            return low;
        else if (array[high] == element)
            return high;
        else
            return -1; // Not found
    }

    /**
     * y - indexes, x - values.
     *
     * @return y = y0 + (y1 - y0)*((x - x0)/(x1 - x0))
     */
    private int getLinearInterpolation(int[] array, int element, int lowIndex, int highIndex) {
        return lowIndex + (highIndex - lowIndex) * ((element - array[lowIndex]) / (array[highIndex] - array[lowIndex]));
//        return lowIndex + ((element - array[lowIndex]) * (highIndex - lowIndex)) / (array[highIndex] - array[lowIndex]);
    }

    private void cocktailSort(int[] array) {
        int n = array.length;
        int i, c = 0;
        do {
            for (i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
            n--;
            for (i = array.length - 1; i > c; i--) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                }
            }
            c++;
        } while (n != 0 && c != array.length - 1);
    }

}
