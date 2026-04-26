package daily_questions.april;

/**
 * Date: April 26, 2026,
 * <a href="https://leetcode.com/problems/detect-cycles-in-2d-grid">Detect Cycles in 2D Grid</a>
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell.
 * From a given cell, you can move to one of the cells adjacent to it -
 * in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
 * Also, you cannot move to the cell that you visited in your last move.
 * For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid
 * because from (1, 2) we visited (1, 1) which was the last visited cell.
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 */
public class DetectCyclesIn2DGrid {
    static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private boolean doesCycleExists(char[][] grid, boolean[][] visited, int row, int col, int parentRow, int parentCol) {
        // Then cycle is found
//        if (visited[row][col]) return true;
        visited[row][col] = true;
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            // Check bounds
            if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) {
                continue;
            }
            // Verify that its not the parent
            if (newRow == parentRow && newCol == parentCol) {
                continue;
            }
            // If the new cell doesn't have the same value of the path we are travelling skip
            if (grid[newRow][newCol] != grid[row][col]) continue;

            if (visited[newRow][newCol] || doesCycleExists(grid, visited, newRow, newCol, row, col)) return true;

        }
        return false;
    }

    // Time Complexity: O(m*n) where m is the number of rows and n is the number of columns in the grid, we are visiting each cell once
    // Space Complexity: O(m*n) for the visited array and the recursion stack in the worst case when the grid forms a linear chain
    public boolean containsCycle(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!visited[row][col]) {
                    if (doesCycleExists(grid, visited, row, col, -1, -1)) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DetectCyclesIn2DGrid detectCyclesIn2DGrid = new DetectCyclesIn2DGrid();
//        char[][] grid = {
//                {'a', 'a', 'a', 'a'},
//                {'a', 'b', 'b', 'a'},
//                {'a', 'b', 'b', 'a'},
//                {'a', 'a', 'a', 'a'}
//        };
//        char[][] grid = {
//                {'b', 'a', 'c'},
//                {'c', 'a', 'c'},
//                {'d', 'd', 'c'},
//                {'b', 'c', 'c'},
//        };
        char[][] grid = {
                {'d', 'b', 'b'}, {'c', 'a', 'a'}, {'b', 'a', 'c'}, {'c', 'c', 'c'}, {'d', 'd', 'a'}
        };
        System.out.println(detectCyclesIn2DGrid.containsCycle(grid));
    }
}
