package graphs;

import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/path-with-minimum-effort/description/">Path With Minimum Effort</a>
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col).
 * You are situated in the top-left cell, (0, 0),
 * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 */
public class PathWithMinimumEffort {
    static class Node implements Comparable<Node> {
        int effort;
        int row;
        int col;

        Node(int effort, int row, int col) {
            this.effort = effort;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Node o) {
            return this.effort - o.effort;
        }
    }

    // Time Complexity: O(m*n*log(m*n)) where m and n are the number of rows and columns in the heights matrix, respectively.
    // Space Complexity: O(m*n) for the priority queue and the distances array.
    private int executePathWithMinEffort(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] distances = new int[m][n];
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++){
                distances[i][j] = Integer.MAX_VALUE;
            }
        }
        pq.add(new Node(0, 0, 0));
        distances[0][0] = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int row = node.row;
            int col = node.col;
            int effort = node.effort;
            if (distances[row][col] < effort) continue;
            if (row == m - 1 && col == n - 1) return effort;
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                    int newEffort = Math.max(Math.abs(heights[newRow][newCol] - heights[row][col]), effort);
                    if (newEffort < distances[newRow][newCol]) {
                        distances[newRow][newCol] = newEffort;
                        pq.add(new Node(newEffort, newRow, newCol));
                    }
                }
            }
        }
        return 0;
    }

    public int minimumEffortPath(int[][] heights) {
        return executePathWithMinEffort(heights);
    }

    public static void main(String[] args) {
        PathWithMinimumEffort pathWithMinimumEffort = new PathWithMinimumEffort();
//        int[][] heights = {
//                {1, 2, 2},
//                {3, 8, 2},
//                {5, 3, 5}
//        };
        int[][] heights = {{1, 10, 6, 7, 9, 10, 4, 9}};
        System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));
    }
}
