package graphs;

/**
 * <a href="https://leetcode.com/problems/number-of-enclaves/">Number of Enclaves</a>
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 */
public class NumberOfEnclaves {
    private void dfs(int row, int col, int m, int n, int[][] grid) {
        if (col >= n || col < 0 || row >= m || row < 0 || grid[row][col] != 1) return;

        grid[row][col] = 2;
        int[] rowDirection = new int[]{-1, 0, 1, 0};
        int[] colDirection = new int[]{0, 1, 0, -1};
        dfs(row - 1, col, m, n, grid);
        dfs(row, col + 1, m, n, grid);
        dfs(row + 1, col, m, n, grid);
        dfs(row, col - 1, m, n, grid);
    }

    // Time Complexity: O(2M) + O(2N) + O(4MN) where M and N are the number of rows and columns in the grid respectively,
    // Space Complexity: O(MxN) for the queue in the worst case when all the cells are land
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) dfs(i, 0, m, n, grid);
            if (grid[i][n - 1] == 1) dfs(i, n - 1, m, n, grid);
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) dfs(0, j, m, n, grid);
            if (grid[m - 1][j] == 1) dfs(m - 1, j, m, n, grid);
        }

        int count = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfEnclaves numberOfEnclaves = new NumberOfEnclaves();
        int[][] grid = new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(numberOfEnclaves.numEnclaves(grid));
    }
}
