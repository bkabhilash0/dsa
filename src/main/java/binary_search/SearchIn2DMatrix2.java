package binary_search;

/**
 * <a href="https://leetcode.com/problems/search-a-2d-matrix-ii/description/">...</a>
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Return true if target is found in the matrix, and false otherwise.
 */
public class SearchIn2DMatrix2 {
    // Brute Force Approach - Linear Search
    // Time Complexity: O(m * n) where m is the number of rows and n is the number of columns
    // Space Complexity: O(1)
    private boolean bruteForce(int[][] matrix, int target) {
        for(int[] ints : matrix){
            for(int num : ints){
                if(num == target){
                    return true;
                }
            }
        }
        return false;
    }

    // Optimal Approach - Search Space Reduction
    // Time Complexity: O(m + n) where m is the number of rows and n is the number of columns
    // Space Complexity: O(1)
    private boolean optimal(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col >= 0){
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] > target){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        return optimal(matrix, target);
    }
    public static void main(String[] args) {
        SearchIn2DMatrix2 sm = new SearchIn2DMatrix2();
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;
        boolean result = sm.searchMatrix(matrix, target);
        System.out.println("Target Found: " + result);
    }
}
