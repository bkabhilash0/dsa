package binary_search;

/**
 * <a href="https://leetcode.com/problems/search-a-2d-matrix/description/">...</a>
 * 74. Search a 2D Matrix
 * You are given an m x n integer matrix matrix with the following two properties:
 * 1. Each row is sorted in non-decreasing order.
 * 2. The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * You must write a solution in O(log(m * n)) time complexity.
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 */
public class SearchIn2DMatrix {
    // Brute Force Approach - Linear Search
    // Time Complexity: O(m * n) where m is the number of rows and n is the number of columns
    // Space Complexity: O(1)
    private boolean bruteForce(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            for (int num : ints) {
                if (num == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(log(m * n)) where m is the number of rows and n is the number of columns
    // Space Complexity: O(1)
    public boolean optimal(int[][] matrix, int target) {
        int start = 0;
        int end = (matrix.length * matrix[0].length) - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int element = matrix[mid / matrix[0].length][mid % matrix[0].length];
            if (element == target) {
                return true;
            }else if(element < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        return optimal(matrix, target);
    }

    public static void main(String[] args) {
        SearchIn2DMatrix sm = new SearchIn2DMatrix();
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;
        boolean result = sm.searchMatrix(matrix, target);
        System.out.println("Target Found: " + result);
    }
}
