package daily_questions.march;

/**
 * Date: March 24, 2026,
 * <a href="https://leetcode.com/problems/construct-product-matrix">Construct Product Matrix</a>
 * Given a 0-indexed 2D integer matrix grid of size n * m,
 * we define a 0-indexed 2D matrix p of size n * m as the product matrix of grid if the following condition is met:
 * Each element p[i][j] is calculated as the product of all elements in grid except for the element grid[i][j].
 * This product is then taken modulo 12345.
 * Return the product matrix of grid.
 */
public class ConstructProductMatrix {
    // Time Complexity: O(n*m) to calculate the product and O(n*m) to fill the product matrix, overall O(2*n*m) which simplifies to O(n*m)
    // Space Complexity: O(n*m) for the product matrix
    private int[][] executeProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] productMatrix = new int[n][m];
        long product = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                product *= grid[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                productMatrix[i][j] = (int) ((product / grid[i][j]) % 12345);
            }
        }

        return productMatrix;
    }

    // Time Complexity: O(n * m)
    // Space Complexity: O(n * m)  (can be optimized to O(1) extra)
    public int[][] optimal(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int size = n * m;

        int[] flat = new int[size];

        // Flatten
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flat[i * m + j] = grid[i][j] % 12345;
            }
        }

        int[] prefix = new int[size];
        int[] suffix = new int[size];

        // Prefix
        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            prefix[i] = (prefix[i - 1] * flat[i - 1]) % 12345;
        }

        // Suffix
        suffix[size - 1] = 1;
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (suffix[i + 1] * flat[i + 1]) % 12345;
        }

        // Build result
        int[][] result = new int[n][m];
        for (int i = 0; i < size; i++) {
            int val = (prefix[i] * suffix[i]) % 12345;
            result[i / m][i % m] = val;
        }

        return result;
    }

    public int[][] constructProductMatrix(int[][] grid) {
        return optimal(grid);
    }

    public static void main(String[] args) {
        ConstructProductMatrix solution = new ConstructProductMatrix();
        int[][] grid = {{1, 2}, {3, 4}};
        int[][] productMatrix = solution.constructProductMatrix(grid);
        System.out.println(java.util.Arrays.deepToString(productMatrix)); // Output: [[24, 12], [8, 6]]
    }
}
