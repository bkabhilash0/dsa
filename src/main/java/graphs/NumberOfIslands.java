package graphs;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/description/">Number of Islands</a>
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class NumberOfIslands {
    // Time Complexity: O(m * n) where m and n are the number of rows and columns in the grid, we are visiting each cell in the grid once
    // Space Complexity: O(m * n) for the visited array, in the worst case we might have to visit all the cells in the grid and mark them as visited
    public void visitNeighbours(int row, int col, char[][] grid, int[][] visited) {
        visited[row][col] = 1;
        // Now check the neighbours of the current cell, we can only move up, down, left and right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == '1' && visited[newRow][newCol] == 0) {
                visitNeighbours(newRow, newCol, grid, visited);
            }
        }
    }

    // Time Complexity: O(m * n) where m and n are the number of rows and columns in the grid, we are visiting each cell in the grid once
    // Space Complexity: O(m * n) for the visited array, in the worst case we might have to visit all the cells in the grid and mark them as visited
    public int numIslands(char[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && visited[i][j] == 0) {
                    count++;
                    visitNeighbours(i, j, grid, visited);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numberOfIslands.numIslands(grid)); // Output: 3
    }
}
