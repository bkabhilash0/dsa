package arrays;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/rotate-image/description/">...</a>
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 */
public class RotateImage {
    private void swap(int[][] matrix, int i, int j){
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    private void reverse(int[] row, int start, int end){
        while(start < end){
            int temp = row[start];
            row[start] = row[end];
            row[end] = temp;
            start++;
            end--;
        }
    }

    private void transpose(int[][] matrix){
        // Use the diagonal to swap
        for(int i = 0; i < matrix.length - 1; i++){
            for(int j = i + 1; j < matrix[0].length; j++){
                swap(matrix, i, j);
            }
        }
    }

    // Get the transpose and then reverse each row
    private void optimal(int[][] matrix) {
        int n = matrix.length;
        transpose(matrix);
        // Reverse each row
        for (int[] ints : matrix) {
            reverse(ints, 0, n - 1);
        }
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public void rotate(int[][] matrix) {
        optimal(matrix);
    }
    public static void main(String[] args) {
        RotateImage ri = new RotateImage();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        ri.rotate(matrix);
        System.out.println("Rotated Matrix:" + Arrays.deepToString(matrix));
    }
}
