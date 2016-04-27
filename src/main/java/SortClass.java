
public class SortClass {

    public static void sort(int[] arr) {

        if ((arr == null) || (arr.length < 2))
            return;

        int fromIndex = 0;
        int toIndex = arr.length - 1;

        quickSort(arr, fromIndex, toIndex);
    }

    /**
     * algorithm solving
     */
    private static void quickSort(int[] arr, int fromIndex, int toIndex) {
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

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(int[] x, int a, int b) {
        int i = x[a];
        x[a] = x[b];
        x[b] = i;
    }


}
