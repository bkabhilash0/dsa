package daily_questions.march;

import java.util.Arrays;

/**
 * Date: March 21, 2026,
 * <a href="https://leetcode.com/problems/flip-square-submatrix-vertically">Flip Square Submatrix Vertically</a>
 * You are given an m x n integer matrix grid, and three integers x, y, and k.
 * The integers x and y represent the row and column indices of the top-left corner of a square submatrix
 * and the integer k represents the size (side length) of the square submatrix.
 * Your task is to flip the submatrix by reversing the order of its rows vertically.
 * Return the updated matrix.
 */
public class FlipSquareSubmatrixVertically {
    // Time Complexity: O(k^2) as we are iterating through k columns and for each column we are iterating through k rows
    // Space Complexity: O(1) as we are doing in place swapping
    private int[][] optimal(int[][] grid, int x, int y, int k) {
        int top;
        int bottom;
        for (int i = y; i < y + k; i++) {
            top = x;
            bottom = x + k - 1;
            while (top < bottom) {
                int temp = grid[top][i];
                grid[top][i] = grid[bottom][i];
                grid[bottom][i] = temp;
                top++;
                bottom--;
            }
        }
        return grid;
    }

    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // Go through each column
        return optimal(grid, x, y, k);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int x = 1;
        int y = 0;
        int k = 3;
        FlipSquareSubmatrixVertically fssv = new FlipSquareSubmatrixVertically();
        int[][] result = fssv.reverseSubmatrix(grid, x, y, k);
        System.out.println(Arrays.deepToString(result));
    }
}
