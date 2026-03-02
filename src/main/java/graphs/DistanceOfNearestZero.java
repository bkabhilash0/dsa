package graphs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/01-matrix/description/">01 Matrix</a>
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two cells sharing a common edge is 1.
 */
public class DistanceOfNearestZero {
    // Time Complexity: O(NxM) + O(4MN) where N and M are the number of rows and columns in the grid respectively,
    // Space Complexity: O(NxM) for the queue in the worst case when all the cells are 0
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] visited = new int[m][n];
        int[][] res = new int[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = 1;
                }
            }
        }

        int[] directionRow = new int[]{-1, 0, 1, 0};
        int[] directionCol = new int[]{0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            int row = element[0];
            int col = element[1];
            int distance = element[2];
            if (mat[row][col] != 0) {
                res[row][col] = distance;
            }
            for (int i = 0; i < 4; i++) {
                int newRow = row + directionRow[i];
                int newCol = col + directionCol[i];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && visited[newRow][newCol] == 0) {
                    queue.add(new int[]{newRow, newCol, distance + 1});
                    visited[newRow][newCol] = 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        DistanceOfNearestZero distanceOfNearestZero = new DistanceOfNearestZero();
        // A confusion happens here that since all the zeros and the traversal starts from (0,0) and the distance is calculated from there,
        // but we are adding all the zeros to the queue at the beginning and marking all the 0s as visited,
        // so the distance is calculated from the nearest zero and not from (0,0)
        int[][] mat = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        int[][] res = distanceOfNearestZero.updateMatrix(mat);
        System.out.println(Arrays.deepToString(res));
    }
}
