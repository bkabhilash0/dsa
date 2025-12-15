package arrays;

import java.util.Arrays;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/set-matrix-zeroes/description/">...</a>
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 */
public class SetMatrixZeroes {
    private void fillRows(int[] nums) {
        Arrays.fill(nums, 0);
    }

    private void fillCols(int[][] matrix, int columnIndex) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][columnIndex] = 0;
        }
    }

    // Using int maps instead of Sets to reduce overhead
    // Time Complexity: O(m * n) + O(m + n) ~ O(m * n)
    // Space Complexity: O(m + n)
    // Also the better solution
    private void intuition(int[][] matrix) {
        int[] rows = new int[matrix.length];
        int[] cols = new int[matrix[0].length];
        // First pass: Identify all rows and columns that need to be zeroed
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }

        // Fill identified rows with zeroes
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == 1) {
                fillRows(matrix[i]);
            }
        }

        // Fill identified columns with zeroes
        for (int j = 0; j < cols.length; j++) {
            if (cols[j] == 1) {
                fillCols(matrix, j);
            }
        }
    }

    private void bruteForce(int[][] matrix) {
        // To be implemented
    }

    // Time Complexity: O(m * n)
    // Space Complexity: O(1)
    private void optimized(int[][] matrix) {
        // To be implemented
        // In place swapping using first row and first column as markers
        int col0 = 1;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    // Mark respective column
                    if (j == 0) {
                        col0 = 0;
                    } else {
                        matrix[0][j] = 0;
                    }
                    // Mark respective row
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        if (col0 == 0) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        optimized(matrix);
    }

    public static void main(String[] args) {
        SetMatrixZeroes smz = new SetMatrixZeroes();
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        System.out.println(Arrays.deepToString(matrix));
        smz.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
