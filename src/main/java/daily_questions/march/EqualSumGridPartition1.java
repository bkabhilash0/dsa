package daily_questions.march;

import utils.ArrayConvertor;

/**
 * Date: March 25, 2026,
 * <a href="https://leetcode.com/problems/equal-sum-grid-partition-i">Equal Sum Grid Partition</a>
 * You are given an m x n matrix grid of positive integers.
 * Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:
 * Each of the two resulting sections formed by the cut is non-empty.
 * The sum of the elements in both sections is equal.
 * Return true if such a partition exists; otherwise return false.
 */
public class EqualSumGridPartition1 {
    // Time Complexity: O(mn) + O(m + n)
    // Space Complexity: O(m + n)
    private boolean execute(int[][] grid) {
        int numOfRows = grid.length, numOfCols = grid[0].length;
        long[] rowSums = new long[numOfRows];
        long[] colSums = new long[numOfCols];
        long totalSum = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                rowSums[i] += grid[i][j];
                colSums[j] += grid[i][j];
                totalSum += grid[i][j];
            }
        }
        if (totalSum % 2 != 0) {
            return false;
        }

        long targetSum = totalSum / 2;
        long runningSum = 0;
        // Do a row wise cut
        for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
            runningSum += rowSums[rowIndex];
            if (runningSum == targetSum) {
                return true;
            }
        }

        runningSum = 0;
        // Do a column wise cut
        for (int colIndex = 0; colIndex < numOfCols; colIndex++) {
            runningSum += colSums[colIndex];
            if (runningSum == targetSum) {
                return true;
            }
        }

        return false;
    }

    public boolean canPartitionGrid(int[][] grid) {
        return execute(grid);
    }

    public static void main(String[] args) {
        EqualSumGridPartition1 eqsgp = new EqualSumGridPartition1();
        int[][] grid = ArrayConvertor.parse2DArray("[[1,4],[2,3]]");
        boolean result = eqsgp.canPartitionGrid(grid);
        System.out.println("Result: " + result);
    }
}
