package daily_questions.march;

import java.util.Arrays;

/**
 * Date: March 27, 2026,
 * <a href="https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts">Matrix Similarity After Cyclic Shifts</a>
 * You are given an m x n integer matrix mat and an integer k. The matrix rows are 0-indexed.
 * The following proccess happens k times:
 * Even-indexed rows (0, 2, 4, ...) are cyclically shifted to the left.
 * Odd-indexed rows (1, 3, 5, ...) are cyclically shifted to the right.
 * Return true if the final modified matrix after k steps is identical to the original matrix, and false otherwise.
 */
public class MatrixSimilarityAfterCyclicShifts {
    private void reverse(int[] arr, int start, int end) {
        int left = start, right = end;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    private boolean isRowSimilar(int[] row, int[] rotatedRow) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] != rotatedRow[i]) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(2N)
    // Space Complexity: O(N) where N is the size of the row
    private boolean isRowSimilarAfterCyclicShift(int[] row, int k, int i) {
        int m = row.length;
        int numOfRotations = k % m;
        if (numOfRotations == 0) {
            return true;
        }
        int[] temp = Arrays.copyOf(row, m);
        if (i % 2 == 1) {
            // For odd row do right rotation
            reverse(row, 0, m - numOfRotations - 1);
            reverse(row, m - numOfRotations, m - 1);
        } else {
            // For even row do left rotation
            reverse(row, 0, numOfRotations - 1);
            reverse(row, numOfRotations, m - 1);
        }
        reverse(row, 0, m - 1);
//        System.out.println("Num of rotations: " + numOfRotations);
//        System.out.println("Row: " + Arrays.toString(temp) + ", Rotated Row: " + Arrays.toString(row));
        return isRowSimilar(temp, row);
    }

    // Time Complexity: O(N)
    // Space Complexity:O(1)
    private boolean isRowSimilarAfterCyclicShiftOptimal(int[] row, int k, int i) {
        int n = row.length;
        k = k % n;

        for (int j = 0; j < n; j++) {
            int expected;

            if (i % 2 == 1) {
                // Odd row → right rotation
                expected = row[(j - k + n) % n];
            } else {
                // Even row → left rotation
                expected = row[(j + k) % n];
            }

            if (row[j] != expected) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(M * N) where m is the number of rows and n is the number of columns in the matrix
    // Space Complexity: O(1)
    private boolean execute(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            int[] row = mat[i];
            if (!isRowSimilarAfterCyclicShiftOptimal(row, k, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean areSimilar(int[][] mat, int k) {
        return execute(mat, k);
    }

    public static void main(String[] args) {
        MatrixSimilarityAfterCyclicShifts msacs = new MatrixSimilarityAfterCyclicShifts();
//        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int k = 4;
        int[][] mat = {{1, 2, 1, 2}, {5, 5, 5, 5}, {6, 3, 6, 3}};
        int k = 2;
        System.out.println(msacs.areSimilar(mat, k));
    }
}
