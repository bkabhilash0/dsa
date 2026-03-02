package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/surrounded-regions/">Surrounded Regions</a>
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell.
 * Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.
 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
 */
public class SurroundedRegions {

    private void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') return;

        board[row][col] = 'E';

        int[] rowDirection = new int[]{-1, 0, 1, 0};
        int[] colDirection = new int[]{0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int newRow = row + rowDirection[i];
            int newCol = col + colDirection[i];
            dfs(board, newRow, newCol);
        }
    }

    // Time Complexity: O(2M) + O(2N) + O(4MN) + O(MxN) where M and N are the number of rows and columns in the grid respectively,
    // Space Complexity: O(MxN) for the queue in the worst case when all the cells are 'O'
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // Iterate through the first and last column for every row
        // We do not need the if condition as the dfs checks the condition of being 0
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
//            if (board[i][0] == 'O') {
//                dfs(board, i, 0);
//            }
//            if (board[i][n - 1] == 'O') {
//                dfs(board, i, n - 1);
//            }
        }

        // Iterate through the first and last row for every column
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
//            if (board[0][j] == 'O') {
//                dfs(board, 0, j);
//            }
//            if (board[m - 1][j] == 'O') {
//                dfs(board, m - 1, j);
//            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        surroundedRegions.solve(board);
        for (char[] chars : board) {
            System.out.println(chars);
        }
    }
}
