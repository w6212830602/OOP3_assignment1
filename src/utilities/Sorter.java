package utilities;

import shapes.Shape;
import java.util.Comparator;

public class Sorter {
    // Sorts an array of Comparable objects using the bubble sort algorithm in descending order
    public static  void bubbleSort(Shape[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) < 0) {
                    // Swap elements
                    swap(array, j, j + 1);
                }
            }
        }
    }
    
    public static void bubbleSort(Shape[] array, Comparator<Shape> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    // Swap elements
                    swap(array, j, j + 1);
                }
            }
        }
    }

    // =================
    //  INSERTION SORT
    // =================
    
    // Sorts the Shape array by height using the insertion sort algorithm in descending order
    public static void insertionSort(Shape[] array) {
        for (int i = 1; i < array.length; i++) {
            Shape key = array[i];
            int j = i - 1;

            // Move elements of array[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Sorts the Shape array by volume or base area using the insertion sort algorithm with a custom comparator
    public static void insertionSort(Shape[] array, Comparator<Shape> comparator) {
        for (int i = 1; i < array.length; i++) {
            Shape key = array[i];
            int j = i - 1;

            // Move elements of array[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && comparator.compare(array[j], key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // =================
    //  SELECTION SORT
    // =================
    
    // Sorts the Shape array by height using the selection sort algorithm in descending order
    public static void selectionSort(Shape[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[maxIndex]) < 0) {
                    maxIndex = j;
                }
            }
            // Swap the found maximum element with the first element
            swap(array, maxIndex, i);
        }
    }

    // Sorts the Shape array by volume or base area using the selection sort algorithm with a custom comparator
    public static void selectionSort(Shape[] array, Comparator<Shape> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[j], array[maxIndex]) < 0) {
                    maxIndex = j;
                }
            }
            // Swap the found maximum element with the first element
            swap(array, maxIndex, i);
        }
    }

    // =================
    //  MERGE SORT
    // =================

    // Sorts the Shape array by height using the merge sort algorithm in descending order
    public static void mergeSort(Shape[] array) {
        if (array.length < 2) {
            return; // Array is already sorted
        }
        int mid = array.length / 2;
        Shape[] left = new Shape[mid];
        Shape[] right = new Shape[array.length - mid];

        // Fill the left and right arrays
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        // Recursively sort both halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(array, left, right);
    }

    // Sorts the Shape array by volume or base area using the merge sort algorithm with a custom comparator
    public static void mergeSort(Shape[] array, Comparator<Shape> comparator) {
        if (array.length < 2) {
            return; // Array is already sorted
        }
        int mid = array.length / 2;
        Shape[] left = new Shape[mid];
        Shape[] right = new Shape[array.length - mid];

        // Fill the left and right arrays
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        // Recursively sort both halves
        mergeSort(left, comparator);
        mergeSort(right, comparator);

        // Merge the sorted halves
        merge(array, left, right, comparator);
    }

    // Merges two sorted arrays into one
    private static void merge(Shape[] array, Shape[] left, Shape[] right) {
        int i = 0, j = 0, k = 0;

        // Merge the two arrays in descending order
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) < 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy remaining elements of left array, if any
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copy remaining elements of right array, if any
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Merges two sorted arrays into one using a custom comparator
    private static void merge(Shape[] array, Shape[] left, Shape[] right, Comparator<Shape> comparator) {
        int i = 0, j = 0, k = 0;

        // Merge the two arrays in descending order using the comparator
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) < 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy remaining elements of left array, if any
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copy remaining elements of right array, if any
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
    
    // =================
    // QUICK SORT
    // =================

    // Sorts the Shape array by height using the quick sort algorithm in descending order
    public static void quickSort(Shape[] array) {
        quickSort(array, 0, array.length - 1);
    }

    // Sorts the Shape array by volume or base area using the quick sort algorithm with a custom comparator
    public static void quickSort(Shape[] array, Comparator<Shape> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }

    // Recursive quick sort method
    private static void quickSort(Shape[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Recursive quick sort method with a custom comparator
    private static void quickSort(Shape[] array, int low, int high, Comparator<Shape> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quickSort(array, low, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, high, comparator);
        }
    }

    // Partition method for quick sort
    private static int partition(Shape[] array, int low, int high) {
        Shape pivot = array[high]; // Choose the last element as pivot
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) { // Change to > for descending order
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high); // Swap the pivot element with the element at i + 1
        return i + 1;
    }

    // Partition method for quick sort with a custom comparator
    private static int partition(Shape[] array, int low, int high, Comparator<Shape> comparator) {
        Shape pivot = array[high]; // Choose the last element as pivot
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) < 0) { // Change to > for descending order
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high); // Swap the pivot element with the element at i + 1
        return i + 1;
    }

    // =================
    // HEAP SORT
    // =================

    // Sorts the Shape array by height using the heap sort algorithm in descending order
    public static void heapSort(Shape[] array) {
        int n = array.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i); // Move current root to end
            heapify(array, i, 0); // Call max heapify on the reduced heap
        }
    }

    // Sorts the Shape array by volume or base area using the heap sort algorithm with a custom comparator
    public static void heapSort(Shape[] array, Comparator<Shape> comparator) {
        int n = array.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, comparator);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i); // Move current root to end
            heapify(array, i, 0, comparator); // Call max heapify on the reduced heap
        }
    }

    // Heapify a subtree rooted with node i which is an index in array[].
    // n is the size of the heap
    private static void heapify(Shape[] array, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(array, i, largest);
            // Recursively heapify the affected subtree
            heapify(array, n, largest);
        }
    }

    // Heapify a subtree rooted with node i which is an index in array[].
    // n is the size of the heap, using a custom comparator
    private static void heapify(Shape[] array, int n, int i, Comparator<Shape> comparator) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && comparator.compare(array[left], array[largest]) > 0) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && comparator.compare(array[right], array[largest]) > 0) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(array, i, largest);
            // Recursively heapify the affected subtree
            heapify(array, n, largest, comparator);
        }
    }

    // private swap helper method
    private static void swap(Shape[] array, int i, int j) {
        Shape temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
