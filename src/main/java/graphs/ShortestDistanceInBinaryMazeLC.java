package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
 * If there is no clear path, return -1.
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 */
public class ShortestDistanceInBinaryMazeLC {
    // Time Complexity: O(MN * 8)
    // Space Complexity: O(2MN), we might need to store MN cells or items
    private int executeShortestPath(int[][] grid) {
        // Invalid cell
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) return -1;
        int numOfRows = grid.length;
        int numOfCols = grid[0].length;
        // Lets not use a seperate visited grid
//        boolean[][] visited = new boolean[numOfRows][numOfCols];
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] directions = {
                {-1, 0}, // Top
                {1, 0}, // Bottom
                {0, -1}, // Left
                {0, 1}, // Right
                {-1, -1}, // Top Left
                {-1, 1}, // Top Right
                {1, 1}, // Bottom Right
                {1, -1} // Bottom Left
        };
        // Store {row, col, dist} - Initializing dist as 1 coz the problem considers the source also as +1
        queue.offer(new int[]{0, 0, 1});
        // Lets reuse the input grid as visited
        grid[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];
            if (row == numOfRows - 1 && col == numOfCols - 1) {
                return dist;
            }
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                // We need to follow the paths with number 0 as per the problem statement
                // Thats why we check grid[newRow][newCol] == 0 rather than 1
                if (newRow >= 0 && newRow < numOfRows && newCol >= 0 && newCol < numOfCols && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = 1;
                    queue.offer(new int[]{newRow, newCol, dist + 1});
                }
            }
        }
        return -1;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        return executeShortestPath(grid);
    }

    public static void main(String[] args) {
        ShortestDistanceInBinaryMazeLC shortestDistanceInBinaryMazeLC = new ShortestDistanceInBinaryMazeLC();
//        int[][] grid = {{0, 1},
//                {1, 0}};
        int[][] grid = {
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 0}};
        System.out.println(shortestDistanceInBinaryMazeLC.shortestPathBinaryMatrix(grid));
    }
}
