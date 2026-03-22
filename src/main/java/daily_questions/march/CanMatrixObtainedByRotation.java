package daily_questions.march;


/**
 * Date: March 22, 2026,
 * <a href="https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation">Determine Whether Matrix Can be Obtained by Rotation</a>
 * Given two n x n binary matrices mat and target,
 * return true if it is possible to make mat equal to target by rotating mat in 90-degree increments,
 * or false otherwise.
 */
public class CanMatrixObtainedByRotation {
    private boolean isEqual(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int n = mat1[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat1[i][j] != mat2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] rotateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] rotated = new int[n][m];
        // Create transpose of the matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][i] = mat[i][j];
            }
        }
        // Now reverse all the rows or swap the first col to last, 2nd col to 2nd last ....
        for (int[] row : rotated) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = row[left];
                row[left] = row[right];
                row[right] = temp;
                left++;
                right--;
            }
        }
        return rotated;
    }

    // Time Complexity: O(4) * (O(m*n) + O(m*n) + O(m*n)) = O(mn^2) Ignore one mn as we are considering operation wise
    // Space Complexity: O(m*n) for the rotated matrix
    private boolean executeFindRotation(int[][] mat, int[][] target) {
        int m = mat.length;
        int n = mat[0].length;
        // Try 4 kinds of rotation - 90, 180, 270, 360
        for (int i = 0; i < 4; i++) {
            if (isEqual(mat, target)) {
                return true;
            }
            mat = rotateMatrix(mat);
        }
        return false;
    }

    // Time Complexity: O(m*n) as we are checking for all 4 rotations in one pass
    // Space Complexity: O(1) as we are not using any extra space
    private boolean optimal(int[][] mat, int[][] target) {
        int m = mat.length;
        int n = mat[0].length;

        boolean is0Rotated = true;
        boolean is90Rotated = true;
        boolean is180Rotated = true;
        boolean is270Rotated = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Check for no rotation
                // If 0 rotated this mapping should always pass
                if (mat[i][j] != target[i][j]) {
                    is0Rotated = false;
                }

                // Check for 90 degree rotation
                if (mat[i][j] != target[j][n - 1 - i]) {
                    is90Rotated = false;
                }

                // Check for 180 degrees rotation
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) {
                    is180Rotated = false;
                }

                // Check for 270 degrees rotation
                if (mat[i][j] != target[n - 1 - j][i]) {
                    is270Rotated = false;
                }
            }
        }
        return is0Rotated || is90Rotated || is180Rotated || is270Rotated;
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        return optimal(mat, target);
    }

    public static void main(String[] args) {
        int[][] mat = {{0, 1}, {1, 0}};
        int[][] target = {{1, 0}, {0, 1}};
        CanMatrixObtainedByRotation cmor = new CanMatrixObtainedByRotation();
        System.out.println(cmor.findRotation(mat, target));
    }
}
