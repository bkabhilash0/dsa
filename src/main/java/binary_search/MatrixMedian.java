package binary_search;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1">...</a>
 * Given a row-wise sorted matrix mat[][] of size n*m,
 * where the number of rows and columns is always odd.
 * Return the median of the matrix.
 */
public class MatrixMedian {
    // Brute Force Approach - Flatten and Sort
    // Time Complexity: O(n*m) + O(n * m log(n * m)) where n is the number of rows and m is the number of columns
    // Space Complexity: O(n * m)
    private int bruteForce(int[][] mat){
        int[] temp = new int[mat.length * mat[0].length];
        int index = 0;
        for (int[] ints : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                temp[index++] = ints[j];
            }
        }
        Arrays.sort(temp);
        return temp[temp.length / 2];
    }

    private int getMin(int[][] mat){
        int min = Integer.MAX_VALUE;
        for (int[] ints : mat) {
            if (ints[0] < min) {
                min = ints[0];
            }
        }
        return min;
    }

    private int getMax(int[][] mat){
        int max = Integer.MIN_VALUE;
        for (int[] ints : mat) {
            if (ints[ints.length - 1] > max) {
                max = ints[ints.length - 1];
            }
        }
        return max;
    }

    private int upperBound(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > target) {
               high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int getCountLessThanOrEqualTo(int[][] mat, int target){
        int count = 0;
        for (int[] row : mat) {
            count += upperBound(row, target);
        }
        return count;
    }

    // Optimal Approach - Binary Search on Value Range
    // Time Complexity: O(log(max - min) x rows × log(cols))
    // Space Complexity: O(1)
    private int optimal(int[][] mat){
        int left = getMin(mat);
        int right = getMax(mat);
        int desiredCount = (mat.length * mat[0].length + 1) / 2;
        while (left < right) {
            int mid = (left + right) / 2;
            int count = getCountLessThanOrEqualTo(mat, mid);
            if(count < desiredCount) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int median(int[][] mat){
        return optimal(mat);
    }

    public static void main(String[] args) {
        MatrixMedian mm = new MatrixMedian();
        int[][] mat = {
                {1, 3, 5},
                {2, 6, 9},
                {3, 6, 9}
        };
        int result = mm.median(mat);
        System.out.println("Median: " + result);
    }
}
