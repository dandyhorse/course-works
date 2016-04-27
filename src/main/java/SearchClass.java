
public class SearchClass {

    public static int search(int[] arr, int element) {

        if ((arr == null) || arr.length == 0)
            return -1;  // key not found.
        if (arr.length == 1)
            return arr[0]; // key found

        return binarySearch(arr, 0, arr.length, element);
    }

    private static int binarySearch(int[] arr, int fromIndex, int toIndex, int element) {
        int left = fromIndex;
        int right = toIndex - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int pivot = arr[mid];

            if (pivot < element)
                left = mid + 1;
            else if (pivot > element)
                right = mid - 1;
            else
                return mid; // key found
        }
        return -1;  // key not found.
    }

}
