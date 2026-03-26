package daily_questions.march;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: March 26, 2026,
 * <a href="https://leetcode.com/problems/equal-sum-grid-partition-ii">Equal Sum Grid Partition 2</a>
 * You are given an m x n matrix grid of positive integers.
 * Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:
 * Each of the two resulting sections formed by the cut is non-empty.
 * The sum of elements in both sections is equal, or can be made equal by discounting at most one single cell in total (from either section).
 * If a cell is discounted, the rest of the section must remain connected.
 * Return true if such a partition exists; otherwise, return false.
 * Note: A section is connected if every cell in it can be reached from any other cell by moving up, down, left, or right through other cells in the section.
 */
public class EqualSumGridPartition2 {
    public boolean optimal(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;

        HashMap<Long, Integer> bottom = new HashMap<>();
        HashMap<Long, Integer> top = new HashMap<>();
        HashMap<Long, Integer> left = new HashMap<>();
        HashMap<Long, Integer> right = new HashMap<>();

        // Initialize bottom and right maps
        for (int[] row : grid) {
            for (int x : row) {
                total += x;
                bottom.put((long) x, bottom.getOrDefault((long) x, 0) + 1);
                right.put((long) x, right.getOrDefault((long) x, 0) + 1);
            }
        }

        System.out.println(bottom);
        System.out.println(right);

        long sumTop = 0;

        // Horizontal cuts
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                sumTop += val;

                top.put((long) val, top.getOrDefault((long) val, 0) + 1);
                bottom.put((long) val, bottom.get((long) val) - 1);
            }

            long sumBottom = total - sumTop;

            if (sumTop == sumBottom) return true;

            long diff = Math.abs(sumTop - sumBottom);

            if (sumTop > sumBottom) {
                if (check(top, grid, 0, i, 0, n - 1, diff)) return true;
            } else {
                if (check(bottom, grid, i + 1, m - 1, 0, n - 1, diff)) return true;
            }
        }

        long sumLeft = 0;

        // Vertical cuts
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int val = grid[i][j];
                sumLeft += val;

                left.put((long) val, left.getOrDefault((long) val, 0) + 1);
                right.put((long) val, right.get((long) val) - 1);
            }

            long sumRight = total - sumLeft;

            if (sumLeft == sumRight) return true;

            long diff = Math.abs(sumLeft - sumRight);

            if (sumLeft > sumRight) {
                if (check(left, grid, 0, m - 1, 0, j, diff)) return true;
            } else {
                if (check(right, grid, 0, m - 1, j + 1, n - 1, diff)) return true;
            }
        }

        return false;
    }

    private boolean check(HashMap<Long, Integer> mp, int[][] grid,
                          int r1, int r2, int c1, int c2, long diff) {

        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;

        // single cell
        if (rows * cols == 1) return false;

        // 1D row
        if (rows == 1) {
            return grid[r1][c1] == diff || grid[r1][c2] == diff;
        }

        // 1D column
        if (cols == 1) {
            return grid[r1][c1] == diff || grid[r2][c1] == diff;
        }

        return mp.getOrDefault(diff, 0) > 0;
    }

    public boolean canPartitionGrid(int[][] grid) {
        return optimal(grid);
    }

    public static void main(String[] args) {
        EqualSumGridPartition2 eqsgp2 = new EqualSumGridPartition2();
//        int[][] grid = ArrayConvertor.parse2DArray("[[1,2],[3,4]]");
//        int[][] grid = ArrayConvertor.parse2DArray("[[1,2],[3,4]]");
//        int[][] grid = ArrayConvertor.parse2DArray("[[1,2,4],[2,3,5]]");
//        int[][] grid = ArrayConvertor.parse2DArray("[[25372],[100000],[100000]]");
        int[][] grid = new int[][]{{10, 5, 4, 5}};
        System.out.println(Arrays.deepToString(grid));
        boolean result = eqsgp2.canPartitionGrid(grid);
        System.out.println("Result: " + result);
    }
}
