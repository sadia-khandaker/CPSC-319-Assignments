/**
 * The QuickSort Class
 * Code adapted from an article on GeeksforGeeks
 *
 */


public class QuickSort {
    public void quickSort(Vector arr) {
        if (arr.size () < 2)
            return;
        int max = 0;
        for (int i = 1; i < arr.size (); i++)
            if (arr.storageData[i].head.data.compareTo (arr.storageData[max].head.data) > 0)
                max = i;
        swap (arr, arr.size () - 1, max);
        quickSort (arr, 0, arr.size () - 2);

    }

    public void quickSort(Vector array, int first, int last) {
        int left = first + 1, right = last;
        swap (array, first, (first + last) / 2);

        String pivot = array.storageData[first].head.data;

        while (left <= right) {
            while (array.storageData[left].head.data.compareTo (pivot) <= 0)
                left++;
            while (array.storageData[right].head.data.compareTo (pivot) > 0)
                right--;
            if (left < right)
                swap (array, left++, right--);
            else
                left++;
        }
        swap (array, right, first);
        if (first < right - 1)
            quickSort (array, first, right - 1);
        if (right + 1 < last)
            quickSort (array, right + 1, last);

    }

    public void swap(Vector array, int x, int y) {
        LinkedList temp = array.storageData[x];
        array.storageData[x] = array.storageData[y];
        array.storageData[y] = temp;
    }
}