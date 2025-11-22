package recursion;


public class SudokuSolver {
    void printBoard(char[][] board) {
        System.out.println("┌───┬───┬───┬───┬───┬───┬───┬───┬───┐");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("│ " + board[i][j] + " ");
            }
            System.out.println("│");

            if (i == 8) {
                System.out.println("└───┴───┴───┴───┴───┴───┴───┴───┴───┘");
            } else if (i % 3 == 2) {
                System.out.println("├───┼───┼───┼───┼───┼───┼───┼───┼───┤");
            } else {
                System.out.println("├───┼───┼───┼───┼───┼───┼───┼───┼───┤");
            }
        }
    }

    boolean isValid(char[][] board, int row, int col, char num, int n) {
        // Check if the number exists in the current row
        for (int c = 0; c < n; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        // Check if the number exists in the current column
        for (int r = 0; r < n; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // Check if the number exists in the current 3x3 sub-grid
        int startRow = row - row % 3;
        int endRow = startRow + 3;
        int startCol = col - col % 3;
        int endCol = startCol + 3;
        for (int r = startRow; r < endRow; r++) {
            for (int c = startCol; c < endCol; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve(char[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (int k = 1; k <= n; k++) {
                    boolean isSafe = isValid(board, i, j, (char) (k + '0'), n);
                    if (!isSafe) {
                        continue;
                    }
                    board[i][j] = (char) (k + '0');
                    boolean isSolvable = solve(board, n);
                    if(isSolvable){
                        return true;
                    }
                    board[i][j] = '.';
                }
                return false;
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        solve(board, board.length);
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        SudokuSolver sudoku = new SudokuSolver();
        sudoku.solveSudoku(board);
        sudoku.printBoard(board);
    }
}
