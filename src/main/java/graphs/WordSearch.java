package graphs;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/word-search/description/">Word Search</a>
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 */
public class WordSearch {
    private boolean dfs(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
        // Return true only if the index == word Length, which means all chars were processed and we found the expected word in the grid
        if (index == word.length()) return true;

        // Check if the row and col is valid
        // using board[row] rather than board[0] is faster
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) return false;

        // Check if the next word is the expected word and is not already visited
        if (board[row][col] != word.charAt(index) || visited[row][col]) return false;

        visited[row][col] = true;
        int newIndex = index + 1;

        boolean res = dfs(board, word, newIndex, row - 1, col, visited) || dfs(board, word, newIndex, row + 1, col, visited) ||
                dfs(board, word, newIndex, row, col - 1, visited) || dfs(board, word, newIndex, row, col + 1, visited);

        visited[row][col] = false;
        return res;
    }

    // Inside DFS: From one cell you can move in 4 directions
    // But you cannot go back to the previous cell because of visited
    // So after the first move, you effectively have at most 3 choices per step.
    // Time Complexity: O(M × N × 3^L) L is the length of the word
    // Space Complexity: O(M × N + L) The recursion stack at a time goes max to the size of the word
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean isFound;
        boolean[][] visited = new boolean[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                char ch = word.charAt(0);
                // If the current cell has the starting word of the expected word then start the dfs from here
                if (board[row][col] == ch) {
                    // Instead of using a visited array we
                    // Start the dfs from this cell and check if we can find the expected word in the grid
                    isFound = dfs(board, word, 0, row, col, visited);
                    if (isFound) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
//        char[][] board = {
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//        String word = "ABCCED";
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCB";
//        char[][] board = {
//                {'a'}
//        };
//        String word = "a";
        System.out.println(wordSearch.exist(board, word));
    }
}
