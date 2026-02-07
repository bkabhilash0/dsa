package binary_search;

/**
 * <a href="https://leetcode.com/problems/find-peak-element-ii/description/">...</a>
 * 1901. Find a Peak Element II
 * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
 * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
 * Note that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
 * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
 */
public class FindPeakElement2 {
    // Brute Force Approach - Check Each Element
    // Time Complexity: O(m * n) where m is the number of rows and n is the number of columns
    // Space Complexity: O(1)
    private int[] bruteForce(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int top = (i - 1 >= 0) ? mat[i - 1][j] : -1;
                int bottom = (i + 1 < mat.length) ? mat[i + 1][j] : -1;
                int left = (j - 1 >= 0) ? mat[i][j - 1] : -1;
                int right = (j + 1 < mat[0].length) ? mat[i][j + 1] : -1;
                if (mat[i][j] > top && mat[i][j] > bottom && mat[i][j] > left && mat[i][j] > right) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    private int findMaxInColumn(int[][] mat, int col) {
        int maxRow = 0;
        for (int i = 1; i < mat.length; i++) {
            if (mat[i][col] > mat[maxRow][col]) {
                maxRow = i;
            }
        }
        return maxRow;
    }

    // Optimal Approach - Binary Search on Columns
    // Time Complexity: O(m log(n)) where m is the number of rows and n is the number of columns
    // Space Complexity: O(1)
    private int[] optimal(int[][] mat) {
        int low = 0;
        int high = mat[0].length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int max = findMaxInColumn(mat, mid);

            int left = (mid - 1 >= 0) ? mat[max][mid - 1] : -1;
            int right = (mid + 1 < mat[0].length) ? mat[max][mid + 1] : -1;
            if (mat[max][mid] > left && mat[max][mid] > right) {
                return new int[]{max, mid};
            } else if (mat[max][mid] > left) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new int[2];
    }

    public int[] findPeakGrid(int[][] mat) {
        return optimal(mat);
    }

    public static void main(String[] args) {
        FindPeakElement2 fpe = new FindPeakElement2();
        int[][] mat = {
                {10, 20, 15},
                {21, 30, 14},
                {7, 16, 32}
        };
        int[] result = fpe.findPeakGrid(mat);
        System.out.println("Peak Element found at: [" + result[0] + ", " + result[1] + "]");
    }
}
