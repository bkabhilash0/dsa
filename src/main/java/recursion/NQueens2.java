package recursion;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 */
public class NQueens2 {
    private boolean printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(java.util.Arrays.toString(row));
        }
        System.out.println("=========================================");
        return true;
    }

    private int solveNQueens( int n, int row, int[] colMap, int[] leftDiagonal, int[] rightDiagonal) {
        if (row == n) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < n; col++) {
            boolean canPlace = colMap[col] != 1 && rightDiagonal[(n - 1) + (col - row)] != 1 && leftDiagonal[row + col] != 1;
            if (!canPlace) {
                if(col == n - 1){
                    return count;
                }
                continue;
            }
            leftDiagonal[row + col] = 1;
            rightDiagonal[(n - 1) + (col - row)] = 1;
            colMap[col] = 1;
            count += solveNQueens( n, row + 1, colMap, leftDiagonal, rightDiagonal);
            colMap[col] = 0;
            leftDiagonal[row + col] = 0;
            rightDiagonal[(n - 1) + (col - row)] = 0;
        }

        return count;
    }

    public int totalNQueens(int n) {
        int[] leftDiagonal = new int[2 * n - 1];
        int[] rightDiagonal = new int[2 * n - 1];
        int[] colMap = new int[n];
        return solveNQueens( n, 0, colMap, leftDiagonal, rightDiagonal);
    }

    public static void main(String[] args) {
        NQueens2 nQueens = new NQueens2();
        int res = nQueens.totalNQueens(4);
        System.out.printf("The number of distinct solutions to the n-queens puzzle is %d", res);
    }
}
