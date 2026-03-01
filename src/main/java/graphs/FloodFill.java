package graphs;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/flood-fill/description/">Flood Fill</a>
 * You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
 * To perform a flood fill:
 * Begin with the starting pixel and change its color to color.
 * Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically)
 * and shares the same color as the starting pixel.
 * Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
 * The process stops when there are no more adjacent pixels of the original color to update.
 * Return the modified image after performing the flood fill.
 */
public class FloodFill {
    // We can do this without a visited array as we are only targeting the cells with original color and we are changing the color of the visited cells to the new color,
    // so we won't be visiting the same cell again as it won't match the original color
    private void dfs(int[][] image, int row, int col, int originalColor, int color) {
        int[] rowDirection = new int[]{-1, 0, 1, 0};
        int[] colDirection = new int[]{0, 1, 0, -1};
        image[row][col] = color;
        for (int i = 0; i < 4; i++) {
            int newRow = row + rowDirection[i];
            int newCol = col + colDirection[i];
            if (newRow >= 0 && newRow < image.length && newCol >= 0 && newCol < image[0].length && image[newRow][newCol] == originalColor) {
                dfs(image, newRow, newCol, originalColor, color);
            }
        }
    }

    // Time Complexity: O(NxM) We are going through all the nodes, 4 times is a constant so we can ignore it, where N and M are the number of rows and columns in the image respectively
    // Space Complexity: O(MxN) for the visited and O(NxM) for the recursive stack in the worst case when all the pixels are of the same color
    // We are modifying the input image itself so we don't need a separate res array, we can just return the modified image at the end
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if(originalColor == color) return image;
        dfs(image, sr, sc, originalColor, color);
        return image;
    }

    public static void main(String[] args) {
        FloodFill floodFill = new FloodFill();
//        int[][] image = new int[][]{
//                {1, 1, 1},
//                {1, 1, 0},
//                {1, 0, 1}
//        };
//        int sr = 1;
//        int sc = 1;
//        int color = 2;
        int[][] image = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
        };
        int sr = 1;
        int sc = 0;
        int color = 2;
        int[][] res = floodFill.floodFill(image, sr, sc, color);
        System.out.println(Arrays.deepToString(res));
    }
}
