package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 * You are given an m x n grid where each cell can have one of three values:
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 */
public class RottingOranges {
    // Time Complexity: (NxM) + O(NxM) = O(NxM) where N and M are the number of rows and columns in the grid respectively,
    // Space Complexity: O(NxM) for the queue in the worst case when all the oranges are rotten
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int freshOranges = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j, 0});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        if(freshOranges == 0) return 0;

        System.out.println(queue);
        System.out.println(freshOranges);
        int[] rowDirection = new int[]{-1, 0, 1, 0};
        int[] colDirection = new int[]{0, 1, 0, -1};

        // We can also do level by level traversal and increment the time after each level,
        // but I am adding the time in the queue itself to avoid having to keep track of the levels and the time separately
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            int row = element[0];
            int col = element[1];
            int time = element[2];
            if (grid[row][col] == 1) {
                grid[row][col] = 2;
                freshOranges--;
                if (freshOranges == 0) return time;
            }
            for (int i = 0; i < 4; i++) {
                int newRow = row + rowDirection[i];
                int newCol = col + colDirection[i];
                // We are modifying the input and we do not need the visited array as we are only targeting the fresh oranges and
                // we are changing the value of the rotten oranges to 2, so we won't be visiting the same cell again
                // as it won't match the condition of being a fresh orange
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                    queue.add(new int[]{newRow, newCol, time + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        RottingOranges ro = new RottingOranges();
        int[][] grid = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        grid = new int[][]{
                {0, 2}
        };
        int res = ro.orangesRotting(grid);
        System.out.println(res);
    }
}
