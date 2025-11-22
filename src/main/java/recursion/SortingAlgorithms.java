package recursion;

import java.util.Arrays;

/*
 * Given an array of integers nums, sort the array in ascending order and return it.
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 *
 * */

public class SortingAlgorithms {
    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Selection Sort - Put the largest element at the end
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    void selectionSort(int[] arr, int n) {
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        int min = arr[0];
        int pos = 0;

        for (int i = 0; i < n - 1; i++) {
            min = arr[i];
            pos = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    pos = j;
                }
            }
            swap(arr, i, pos);
        }
    }

    // Bubble Sort - Repeatedly swap the adjacent elements if they are in wrong order
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    // Best Case: O(n) when the array is already sorted
    void bubbleSort(int[] arr, int n) {
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        boolean swapped = false;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break; // If no two elements were swapped by inner loop, then break thus best case being O(n)
        }
    }

    // Insertion Sort - Build the final sorted array one item at a time
    // Intuition: Take an element from the unsorted array and insert it into the correct position in the sorted part of the array
    // Like a deck of cards, we pick one card at a time and place it in the correct position among the already sorted cards usually left side of a point
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    // Best Case: O(n) when the array is already sorted
    void insertionSort(int[] arr, int n) {
        if (arr.length == 0 || arr.length == 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            int j = i;
            // Can be replaced using while loop as well
            // If the previous element is less than the current one then every other previous element will also be less than the current one as the left side is already sorted
            // When an array is sorted the while loop wont run making best case O(n)
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
//            for(int j = i; j > 0; j--){
//                if(arr[j] < arr[j - 1]){
//                    swap(arr, j, j - 1);
//                }
//            }
        }
    }

    // Quick Sort - Divide and Conquer
    // Time Complexity: O(nlog(n)) on average, O(n^2) in the worst case
    // Space Complexity: O(log(n)) due to recursive stack space
    // Place an element at its correct position such that all elements smaller than it are on the left and all elements greater than it are on the right
    void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = arr[low];
        int left = low;
        int right = high;

        while (left < right) {
            while (arr[left] <= pivot && left < high) {
                left++;
            }

            while (arr[right] >= pivot && right > low) {
                right--;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, low, right);
        quickSort(arr, low, right - 1);
        quickSort(arr, right + 1, high);
    }

    void merge(int[] arr, int left, int mid, int right) {
        int i = left; // Starting index for left subarray
        int j = mid + 1; // Starting index for right subarray
        int[] temp = new int[right - left + 1];
        int k = 0; // Starting index for temporary merged array

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i];
                i++;
            } else {
                temp[k++] = arr[j];
                j++;
            }
        }

        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        // Copy the merged temporary array back to the original array
        for(int z = left; z <= right; z++){
            arr[z] = temp[z - left];
        }
//        System.out.println("Array after merging: " + Arrays.toString(Arrays.copyOfRange(arr, left, right+1)));;
    }

    // Merge Sort - Divide and Conquer
    // Time Complexity: O(nlog(n))
    // Space Complexity: O(n) due to temporary arrays used for merging
    void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void main(String[] args) {
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
//        int[] arr = {5, 4, 3,2,1};
//        sortingAlgorithms.insertionSort(arr, arr.length);
        int[] arr = {8, 3, 1, 7, 0, 10, 2, 3, 5, 9, 8, 6, 4};
        System.out.println("Original Array: " + Arrays.toString(arr));
//        sortingAlgorithms.quickSort(arr, 0, arr.length - 1);
        sortingAlgorithms.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
