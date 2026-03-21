package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href="https://www.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1">Shortest Distance in Binary Maze</a>
 * Given a n * m matrix grid where each element can either be 0 or 1.
 * You need to find the shortest distance between a given source cell to a destination cell.
 * The path can only be created out of a cell if its value is 1.
 * If the path is not possible between source cell and destination cell, then return -1.
 * Note : You can move into an adjacent cell if that adjacent cell is filled with element 1.
 * Two cells are adjacent if they share a side. In other words, you can move in one of the four directions, Up, Down, Left and Right.
 * The source and destination cell are based on the zero based indexing. The destination cell should be 1.
 */
public class ShortestDistanceInBinaryMaze {
    // Time Complexity: O(MN * 4) -> O(MN) since we are visiting each cell at max once and for each cell we are checking its 4 adjacent cells
    // Space Complexity: O(2MN) since we are using a visited array and queue to keep track of the visited cells and in worst case we might have to visit all the cells
    private int executeShortestPath(int[][] grid, int[] source, int[] destination) {
        // Your code here
        // If the source of destination is invalid then return -1
        if (grid[source[0]][source[1]] == 0 || grid[destination[0]][destination[1]] == 0)
            return -1;
        int numOfRows = grid.length;
        int numOfCols = grid[0].length;
        boolean[][] visited = new boolean[numOfRows][numOfCols];
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // Store {row, col, dist}
        queue.offer(new int[]{source[0], source[1], 0});
        visited[source[0]][source[1]] = true;

        // Since its unweighted graph we can use BFS to find the shortest path
        // The first hit on the destination will be the shortest path so we can directly return that
        // We do not need a distance array like in Dijkstra
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];
            if (row == destination[0] && col == destination[1]) {
                return dist;
            }
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (newRow >= 0 && newRow < numOfRows && newCol >= 0 && newCol < numOfCols && grid[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, dist + 1});
                }
            }
        }

        return -1;
    }

    int shortestPath(int[][] grid, int[] source, int[] destination) {
        // Your code here
        return executeShortestPath(grid, source, destination);
    }

    public static void main(String[] args) {
        ShortestDistanceInBinaryMaze shortestDistanceInBinaryMaze = new ShortestDistanceInBinaryMaze();
        int[][] grid = {{1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}};
        int[] source = {0, 1};
        int[] destination = {2, 2};
        System.out.println(shortestDistanceInBinaryMaze.shortestPath(grid, source, destination));
    }
}
