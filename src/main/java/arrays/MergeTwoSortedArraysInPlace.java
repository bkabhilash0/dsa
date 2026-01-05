package arrays;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge both the arrays in place in non-decreasing order.
 * eg: arr1 = [1,3,5,7], arr2 = [2,4,6,8,10]
 * output: arr1 = [1,2,3,4], arr2 = [5,6,7,8,10]
 * Note: Do not use any extra space for merging the arrays.
 */
public class MergeTwoSortedArraysInPlace {
    private void swapBetweenArrays(int[] arr1, int i, int[] arr2, int j) {
        int temp = arr1[i];
        arr1[i] = arr2[j];
        arr2[j] = temp;
    }

    private void swapBetweenArrayElementsIfGreater(int[] arr1, int m, int[] arr2, int n) {
        if (arr1[m] > arr2[n]) {
            swapBetweenArrays(arr1, m, arr2, n);
        }
    }

    // Brute Force Approach: Use a temporary array to hold elements of both arrays, sort them and copy back
    // Time Complexity: O(m +n log m +n) due to sorting
    // Space Complexity: O(m+n) for the temporary array
    private void bruteForce(int[] arr1, int m, int[] arr2, int n) {
        // Use a temporary array to hold elements of arr1 and arr2
        int[] temp = new int[m + n];
        int start1 = 0, start2 = 0, index = 0;
        while (start1 < m && start2 < n) {
            if (arr1[start1] <= arr2[start2]) {
                temp[index++] = arr1[start1++];
            } else {
                temp[index++] = arr2[start2++];
            }
        }
        while (start1 < m) {
            temp[index++] = arr1[start1++];
        }
        while (start2 < n) {
            temp[index++] = arr2[start2++];
        }
        // Copy back the elements to arr1 and arr2
        for (int i = 0; i < m + n; i++) {
            if (i < m) {
                arr1[i] = temp[i];
            } else {
                arr2[i - m] = temp[i];
            }
        }
        // Sort them both once its done
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    // Using 2 pointers one from end of array 1 and one from start of array 2
    // Time Complexity: O(m log m + n log n) due to sorting
    // Space Complexity: O(1)
    private void optimalOne(int[] arr1, int m, int[] arr2, int n) {
        int pointer1 = m - 1;
        int pointer2 = 0;
        while (pointer1 >= 0 && pointer2 < n) {
            if (arr1[pointer1] > arr2[pointer2]) {
                swapBetweenArrays(arr1, pointer1, arr2, pointer2);
            }
            pointer1--;
            pointer2++;
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    // Using Gap method
    // Time Complexity: O((m + n) log(m + n))
    // Space Complexity: O(1)
    private void optimalTwo(int[] arr1, int m, int[] arr2, int n) {
        // Implementation of the Gap method can be done here
        int gap = ((m + n) / 2) + ((m + n) % 2);
        while (gap > 0) {
            int left = 0;
            int right = left + gap;
            while (right < m + n) {
                // When one pointer is in array in and another in array 2
                if (left < m && right >= m) {
                    swapBetweenArrayElementsIfGreater(arr1, left, arr2, right - m);
                } else if (left >= m) { // Both pointers are in array 2
                    swapBetweenArrayElementsIfGreater(arr2, left - m, arr2, right - m);
                } else { // Both pointers are in array 1
                    swapBetweenArrayElementsIfGreater(arr1, left, arr1, right);
                }
                left++;
                right++;
            }
            if (gap == 1) {
                break;
            }
            gap = (gap / 2) + (gap % 2);
        }
    }

    private void execute(int[] arr1, int m, int[] arr2, int n) {
        optimalTwo(arr1, m, arr2, n);
    }

    public static void main(String[] args) {
        MergeTwoSortedArraysInPlace mtsai = new MergeTwoSortedArraysInPlace();
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8, 10};
        System.out.println("Before merging:");
        System.out.print("Array 1: " + java.util.Arrays.toString(arr1));
        System.out.print("\tArray 2: " + java.util.Arrays.toString(arr2));
        mtsai.execute(arr1, arr1.length, arr2, arr2.length);
        System.out.println("\nAfter merging:");
        System.out.print("Array 1: " + java.util.Arrays.toString(arr1));
        System.out.print("\tArray 2: " + java.util.Arrays.toString(arr2));
    }
}
