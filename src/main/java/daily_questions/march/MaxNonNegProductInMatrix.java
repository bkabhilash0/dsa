package daily_questions.march;

/**
 * Date: March 23, 2026,
 * <a href="https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix">Max Non Neg Product in a Matrix</a>
 * You are given a m x n matrix grid. Initially,
 * you are located at the top-left corner (0, 0),
 * and in each step, you can only move right or down in the matrix.
 * Among all possible paths starting from the top-left corner (0, 0)
 * and ending in the bottom-right corner (m - 1, n - 1),
 * find the path with the maximum non-negative product.
 * The product of a path is the product of all integers in the grid cells visited along the path.
 * Return the maximum non-negative product modulo 109 + 7.
 * If the maximum product is negative, return -1.
 * Notice that the modulo is performed after getting the maximum product.
 */
public class MaxNonNegProductInMatrix {
    static class Node implements Comparable<Node> {
        int row;
        int col;
        int product;

        Node(int row, int col, int product) {
            this.row = row;
            this.col = col;
            this.product = product;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.product, this.product);
        }
    }

    public int executeMaxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] maxDp = new long[m][n];
        long[][] minDp = new long[m][n];

        maxDp[0][0] = grid[0][0];
        minDp[0][0] = grid[0][0];

        // First column
        for (int i = 1; i < m; i++) {
            maxDp[i][0] = maxDp[i-1][0] * grid[i][0];
            minDp[i][0] = maxDp[i][0];
        }

        // First row
        for (int j = 1; j < n; j++) {
            maxDp[0][j] = maxDp[0][j-1] * grid[0][j];
            minDp[0][j] = maxDp[0][j];
        }

        // Fill rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];

                long a = maxDp[i-1][j] * val;
                long b = minDp[i-1][j] * val;
                long c = maxDp[i][j-1] * val;
                long d = minDp[i][j-1] * val;

                maxDp[i][j] = Math.max(Math.max(a, b), Math.max(c, d));
                minDp[i][j] = Math.min(Math.min(a, b), Math.min(c, d));
            }
        }

        long res = maxDp[m-1][n-1];

        if (res < 0) return -1;
        return (int)(res % 1_000_000_007);
    }

    // Time Complexity: O(MN)
    // Space Complexity: O(MN)
    public int maxProductPath(int[][] grid) {
        return executeMaxProductPath(grid);
    }

    public static void main(String[] args) {
        MaxNonNegProductInMatrix maxNonNegProductInMatrix = new MaxNonNegProductInMatrix();
//        int[][] grid = {{-1, -2, -3}, {-2, -3, -3}, {-3, -3, -2}};
//        int[][] grid = {{1, -2, 1}, {1, -2, 1}, {3, -4, 1}};
//        int[][] grid = {{1, 3}, {0, -4}};
        int[][] grid = {{1, 4, 4, 0}, {-2, 0, 0, 1}, {1, -1, 1, 1}};
        System.out.println(maxNonNegProductInMatrix.maxProductPath(grid)); // return -1
    }
}
