package com.epam.homework_2.algorithms;

public class QuickAndBinary extends AbstractSortAndSearch {

    @Override
    public int search(int[] arr, int element) {

        if ((arr == null) || arr.length == 0)
            return -1;  // key not found.
        if (arr.length == 1)
            return arr[0]; // key found

        return binarySearch(arr, 0, arr.length, element);
    }

    /**
     * @param array     must be sorted
     * @param fromIndex the index of the first element (inclusive) to be
     *                  searched
     * @param toIndex   the index of the last element (exclusive) to be searched
     * @param element   is a key looked for
     * @return an Index of the element
     */
    private int binarySearch(int[] array, int fromIndex, int toIndex, int element) {
        int left = fromIndex;
        int right = toIndex - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int pivot = array[mid];

            if (pivot < element)
                left = mid + 1;
            else if (pivot > element)
                right = mid - 1;
            else
                return mid; // key found
        }
        return -1;  // key not found.
    }

    @Override
    public void sort(int[] arr) {

        if ((arr == null) || (arr.length < 2))
            return;

        int fromIndex = 0;
        int toIndex = arr.length - 1;

        quickSort(arr, fromIndex, toIndex);
    }

    /**
     * algorithm solving
     */
    private void quickSort(int[] arr, int fromIndex, int toIndex) {
        int left = fromIndex, right = toIndex;
        int midIndex = (toIndex + fromIndex) >>> 1;
        int pivot = arr[midIndex];
        while (left <= right) {
            while ((arr[left] < pivot) && (left <= toIndex)) {
                left++;
            }
            while ((arr[right] > pivot) && (right >= fromIndex)) {
                right--;
            }
            if (left <= right) {
                swap(arr, left++, right--);
            }
        }
        if (right > fromIndex) {
            quickSort(arr, fromIndex, right);
        }
        if (left < toIndex) {
            quickSort(arr, left, toIndex);
        }
    }

}
