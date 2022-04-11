import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Assign1 {
    /**
     * Implementation of Selection Sort Algorithm
     * Code adapted from: Dr. Leonard Manzara Lecture Notes
     *
     * It loops through the index of the array to find the smallest element in the array, then it swaps.
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[smallest]) {
                    smallest = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
            }
        }


    /**
     * Implementation of the Insertion Sort Algorithm
     * Code adapted from: Dr. Leonard Manzara Lecture Notes
     *
     *  It sorts an array by placing an unsorted element in its appropriate place in each iteration
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1, j; i < arr.length; i++) {
            int temp = arr[i];
            for (j = i; j > 0 && temp < arr[j - 1]; j--)
                arr[j] = arr[j - 1];
            arr[j] = temp;
        }
    }
    /**
     * Implementation of Merge Sort Algorithm
     * Code adapted from:  Dr. Leonard Manzara's Lecture Notes and Md Shopon's "Sorintg1.java" Source Code
     *
     * Two methods used to sort an array which are mergeSort(int[] arr, int first, int last) and merge(int[] arr, int first, int mid, int last) .
     * The array is divided into two subarrays, then each subarrays are sorted.
     * The subarrays are merged into a temp array, then the temp array is copied.
     *
     * @param arr
     * @param first
     * @param last
     */
    public static void mergeSort(int[] arr, int first, int last) {
        int mid = (first + last) / 2;
        if (first < last) {
            mergeSort(arr, first, mid);
            mergeSort(arr, mid + 1, last);
            merge(arr, first, mid, last);
        }

    }
    public static void merge(int[] arr, int first, int mid, int last) {
        int smallArray1 = mid - first + 1;
        int[] left = new int[smallArray1];
        int smallArray2 = last - mid;
        int[] right = new int[smallArray2];

        for (int i = 0; i < smallArray1; ++i) {
            left[i] = arr[first + i];
        }

        for (int i = 0; i < smallArray2; ++i) {
            right[i] = arr[mid + i + 1];
        }
        int i = 0;
        int j = 0;
        int f = first;

        while (i < smallArray1 && j < smallArray2) {
            if (left[i] < right[j]) {
                arr[f] = left[i];
                i++;
            } else {
                arr[f] = right[j];
                j++;
            }
            f++;
        }
        while (i < smallArray1) {
            arr[f] = left[i];
            i++;
            f++;
        }
        while (j < smallArray2) {
            arr[f] = right[j];
            j++;
            f++;
        }
    }

    /**
     * Implementation of Quick Sort
     * Code adapted from:  Dr. Leonard Manzara's Lecture Notes
     *
     * Three methods are used to sort an array using quick sort, which are quickSort(int[] arr), quickSort(int[] arr, int first, int last), swap(int[] arr, int i, int j).
     *  Array is divided into subarrays by picking a bound(pivot element). Then it finds the pivot for each subarrays until all of the lists have only one element.
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if (arr.length < 2)
            return;
        int max = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > arr[max]) {
                max = i;
            }
        swap(arr, arr.length - 1, max);
        quickSort(arr, 0, arr.length - 2);
    }


    public static void quickSort(int[] arr, int first, int last) {
        int bound = arr[last];
        int index = first;
        for (int i = first; i < last; i++) {
            if (arr[i] <= bound) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, index, last);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        if (Integer.parseInt(args[1]) < 0) {
            System.out.println("Error: Invalid size.");
            System.exit(0);
        }
/**
 * Fills an array based user's input
 */
        Random rand = new Random();
        int[] array = new int[Integer.parseInt(args[1])];
        int filler = 0;

        switch (args[0]) {
            case "random":
                for (int i = 0; i < array.length; i++) {
                    array[i] = rand.nextInt();
                }
                break;
            case "ascending":
                for (int i = 0; i < array.length; i++) {
                    array[i] = rand.nextInt();
                    Arrays.sort(array);
                }
                break;
            case "descending":
                for (int i = 0; i < array.length; i++) {
                    array[i] = rand.nextInt();
                    Arrays.sort(new int[][]{array}, Collections.reverseOrder());
                }

                break;
            default:
                System.out.println("Error: Invalid order");
                System.exit(1);
        }
        System.out.printf("Array size: %s | Array order: %s .\n", args[1], args[0]);

        long start = 0;
        long end = 0;

        /**
         * Sorts array based on user's input
         */

        switch (args[2]) {
            case "selection":
                start = System.nanoTime();
                selectionSort(array);
                end = System.nanoTime();
                break;
            case "insertion":
                start = System.nanoTime();
                insertionSort(array);
                end = System.nanoTime();
                break;
            case "merge":
                start = System.nanoTime();
                mergeSort(array, 0, array.length - 1);
                end = System.nanoTime();
                break;
            case "quick":
                start = System.nanoTime();
                quickSort(array);
                end = System.nanoTime();
                break;
            default:
                System.out.println("Error: Invalid Algorithm");
                System.exit(0);
        }

        long duration = end - start;

        System.out.println("Time taken for " + (args[2]) + " sort: " + (duration) + " nanoseconds");
        /**
         * Makes an output file to write and store the sorted array
         */
        try {
            FileWriter writeOutput = new FileWriter(args[3]);
            for (int j : array) {
                writeOutput.write(Integer.toString(j));
                writeOutput.write("\r\n");
            }
            writeOutput.close();
        }
        catch (IOException error) {
            System.out.println("Error writing to file!");
            error.printStackTrace();
        }
        System.out.printf("Output file %s is created successfully!\n", args[3]);
    }
}
