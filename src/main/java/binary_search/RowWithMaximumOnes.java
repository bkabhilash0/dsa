package binary_search;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/row-with-maximum-ones/description/">...</a>
 * Given a m x n binary matrix mat, find the 0-indexed position of the row that contains the maximum count of ones, and the number of ones in that row.
 * In case there are multiple rows that have the maximum count of ones, the row with the smallest row number should be selected.
 * Return an array containing the index of the row, and the number of ones in it.
 * Note: The rows are not sorted.
 */
public class RowWithMaximumOnes {
    // Brute Force Approach - Count Ones in Each Row
    // Time Complexity: O(m * n) where m is the number of rows and n is the number of columns
    // Space Complexity: O(1)
    private int[] bruteForce(int[][] mat){
        int rowIndex = -1;
        int maxOnes = -1;
        int onesCount = 0;
        for(int i = 0; i < mat.length; i++){
            onesCount = 0;
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] == 1){
                    onesCount++;
                }
            }
            if(onesCount > maxOnes){
                maxOnes = onesCount;
                rowIndex = i;
            }
        }
        return new int[]{rowIndex, maxOnes};
    }

    // There is no better approach since the rows are not sorted
    // So we will directly implement the optimal approach which is same as brute force here
    public int[] rowAndMaximumOnes(int[][] mat) {
        return bruteForce(mat);
    }

    public static void main(String[] args) {
        RowWithMaximumOnes rwmo = new RowWithMaximumOnes();
        int[][] mat = {
                {0,0,0,1},
                {0,1,1,1},
                {1,1,1,1}
        };
        int[] result = rwmo.rowAndMaximumOnes(mat);
        System.out.println("Row Index: " + result[0] + ", Number of Ones: " + result[1]);
    }
}
