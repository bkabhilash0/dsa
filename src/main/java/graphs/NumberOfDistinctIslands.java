package graphs;

import java.util.*;

/**
 * Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s
 * (horizontally or vertically) forms an island.
 * Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).
 */
public class NumberOfDistinctIslands {
    private String encodePaths(List<int[]> path) {
        if (path.size() == 1) return "0,0";
        StringBuilder sb = new StringBuilder();
        int[] startingPoint = path.getFirst();
        for (int[] coordinate : path) {
            sb.append("(")
                    .append(coordinate[0] - startingPoint[0])
                    .append(",")
                    .append(coordinate[1] - startingPoint[1])
                    .append(")->");
        }
        return sb.toString();
    }

    private void dfs(int row, int col, int[][] grid, int[][] visited, List<int[]> path) {
        if (col >= grid[0].length || col < 0 || row >= grid.length || row < 0 || grid[row][col] != 1 || visited[row][col] == 1)
            return;

        visited[row][col] = 1;
        path.add(new int[]{row, col});

        dfs(row - 1, col, grid, visited, path);
        dfs(row, col + 1, grid, visited, path);
        dfs(row + 1, col, grid, visited, path);
        dfs(row, col - 1, grid, visited, path);
    }

    /**
     * DFS          → O(mn)
     * Encoding     → O(mn)
     * Hashing      → O(mn)
     * --------------------------------
     * Total        → O(mn)
     */
    // Time Complexity: O(m * n) + O(m * n) where m and n are the number of rows and columns in the grid, we are visiting each cell in the grid once and encoding the path of each island once
    // Space Complexity: O(m * n) when max island is of size m * n, we are storing the path of the island in the list and the visited array
    private int solve(int[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        Set<String> encodedIslands = new HashSet<>();
        ArrayList<int[]> paths = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    dfs(i, j, grid, visited, paths);
                    encodedIslands.add(encodePaths(paths));
                    paths.clear();
                }
            }
        }
        return encodedIslands.size();
    }

    int countDistinctIslands(int[][] grid) {
        // Your Code here
        return solve(grid);
    }

    public static void main(String[] args) {
        NumberOfDistinctIslands numberOfDistinctIslands = new NumberOfDistinctIslands();
        int[][] grid = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };
        System.out.println(numberOfDistinctIslands.countDistinctIslands(grid));
    }
}
