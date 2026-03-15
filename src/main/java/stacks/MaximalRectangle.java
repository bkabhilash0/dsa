package stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/maximal-rectangle/description/">Maximal Rectangle</a>
 * Given a rows x cols binary matrix filled with 0's and 1's,
 * find the largest rectangle containing only 1's and return its area.
 */
public class MaximalRectangle {
    // Time Complexity: O(M x N) where M is the no of rows and N is the no of cols
    // Space Complexity: O(2N) for the histogram array and the stack
    private int optimal(char[][] matrix) {
        // We consider each row as a histogram and generate histogram from the matrix
        if(matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] histogram = new int[n];
        Deque<Integer> stack;
        int maxArea = 0;
        for (char[] chars : matrix) {
            // Create histogram for each row
            // When we see a 0, reset the col
            for (int col = 0; col < n; col++) {
                if(chars[col] == '1') {
                    histogram[col]++;
                }else{
                    histogram[col] = 0;
                }
            }
            maxArea = Math.max(maxArea, findMaxArea(histogram));
        }
        return maxArea;
    }

    private int findMaxArea(int[] histogram){
        // We need to do it row by row
        // Each row is considered as a histogram
        int n = histogram.length;
        int maxArea = 0;
        int currHeight;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i <= n; i++) {
            currHeight = (i == n) ? 0 : histogram[i];
            while(!stack.isEmpty() && currHeight < histogram[stack.peek()]) {
                int building = stack.pop();
                int pse = stack.isEmpty() ? -1 : stack.peek();
                // i is the nse
                maxArea = Math.max(maxArea, (i - pse - 1) * histogram[building]);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        return optimal(matrix);
    }

    public static void main(String[] args) {
        MaximalRectangle mr = new MaximalRectangle();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
//        char[][] matrix = {
//                {'0', '1'},
//                {'1', '0'},
//        };
        System.out.println(mr.maximalRectangle(matrix));
    }
}
