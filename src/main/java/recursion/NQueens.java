package recursion;

import java.util.AbstractList;
import java.util.List;

/*
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 * */
public class NQueens {
    private void printBoard(List<String> board) {
        for (String row : board) {
            System.out.println(row);
        }
        System.out.println("=========================================");
    }

    private boolean isValid(List<String> board, int row, int col, int n, int[] diag1, int[] diag2) {
        // Check the right inclined diagonal
        // row + col gives the unique index in the diag map
        // All the values in the chosen diagonal will have the same value using this formula
        if (diag1[row + col] == 1) {
            return false;
        }

        // Check the left inclined diagonal
        if (diag2[(n - 1) + (col - row)] == 1) {
            return false;
        }

        // Extract each row and do the validation
        // The destination row wont have the queen so we shall not do a check for the row
        for (int i = 0; i < n; i++) {
            // Get the first row
            String actualRow = board.get(i);
            // For the destination column check if that row has a queen
            if (actualRow.charAt(col) == 'Q') {
                return false;
            }
        }
        return true;
    }

    private void solve(int row, List<List<String>> result, List<String> board, int[] diag1, int[] diag2) {
        if (row == board.size()) {
            result.add(List.copyOf(board));
            return;
        }

        for (int col = 0; col < board.size(); col++) {
            boolean isSafe = isValid(board, row, col, board.size(), diag1, diag2);
            // Check if there is a valid location to place the queen
            if (!isSafe) {
                // If none of the columns in this row is valid to place this then go back the previous row and try other combination
                if (col == board.size() - 1) {
                    return;
                }
                continue;
            }
            String actualRow = board.get(row);
            actualRow = actualRow.substring(0, col) + "Q" + actualRow.substring(col + 1);
            board.set(row, actualRow);
            diag1[row + col] = 1;
            diag2[(board.size() - 1) + (col - row)] = 1;
            solve(row + 1, result, board, diag1, diag2);
            actualRow = actualRow.substring(0, col) + "." + actualRow.substring(col + 1);
            board.set(row, actualRow);
            diag1[row + col] = 0;
            diag2[(board.size() - 1) + (col - row)] = 0;
            // If no location is found for any column then backtrack
        }
    }

    private void execute(int n, List<List<String>> res) {
        List<String> board = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            board.add(new String(new char[n]).replace("\0", "."));
        }
        int[] diag1 = new int[(2 * n) - 1];
        int[] diag2 = new int[(2 * n) - 1];
        solve(0, res, board, diag1, diag2);
    }

    public List<List<String>> solveNQueens(int n) {
        return new AbstractList<List<String>>() {
            List<List<String>> res;

            private void init() {
                res = new java.util.ArrayList<>();
                execute(n, res);
            }

            @Override
            public List<String> get(int index) {
                return res.get(index);
            }

            @Override
            public int size() {
                if (res == null) {
                    init();
                }
                return res.size();
            }
        };
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> res = nQueens.solveNQueens(4);
        System.out.println(res);
    }
}
