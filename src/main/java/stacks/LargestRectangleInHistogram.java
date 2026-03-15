package stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/largest-rectangle-in-histogram/description/">Largest Rectangle in Histogram</a>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 */
public class LargestRectangleInHistogram {
    // Time Complexity: O(N^2)
    // Space Complexity: O(1)
    private int bruteForce(int[] heights) {
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, (j - i + 1) * minHeight);
            }
        }
        return maxArea;
    }

    // Time Complexity: O(3N)
    // Space Complexity: O(2N) for the pse and nse arrays
    private int better(int[] heights) {
        // We can use the PSE and NSE concept of Stack
        int[] pse = new int[heights.length];
        int[] nse = new int[heights.length];
        int res = Integer.MIN_VALUE;
        // Create the pse and nse using stacks
        Deque<Integer> stack = new ArrayDeque<>();
        // Create the pse array
        pse[0] = -1;
        int pseIndex = 1;
        stack.push(0);
        for (int i = 1; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            pse[pseIndex++] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Initialise the nse
        stack.clear();
        // Instead of -1 we set it as n
        nse[heights.length - 1] = heights.length;
        stack.push(heights.length - 1);
        int nseIndex = heights.length - 2;
        for (int i = heights.length - 2; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            nse[nseIndex--] = stack.isEmpty() ? heights.length : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < heights.length; i++) {
            res = Math.max(res, (nse[i] - pse[i] - 1) * heights[i]);
        }
        return res;
    }

    // Time Complexity: O(N) + O(N)
    // Space Complexity: O(N) for the stack
    private int optimal(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;
        int n = heights.length;
        int currentHeight;
        for (int i = 0; i <= n; i++) {
            // Maintain an Increasing stack
            // If the current building is smaller than the top we can pop the last element and gets its pse
            // The current element is nse
            // Then find the area and update
            currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && currentHeight <= heights[stack.peek()]) {
                int building = stack.pop();
                int pse = stack.isEmpty() ? -1 : stack.peek();
                int area = (i - pse - 1) * heights[building];
                res = Math.max(res, area);
            }
            stack.push(i);
        }

        // Handle the left over elements in the stack
//        while (!stack.isEmpty()) {
//            int building = stack.pop();
//            int pse = stack.isEmpty() ? -1 : stack.peek();
//            int area = (heights.length - pse - 1) * heights[building];
//            res = Math.max(res, area);
//        }
        return res;
    }

    public int largestRectangleArea(int[] heights) {
        return optimal(heights);
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram lrh = new LargestRectangleInHistogram();
//        int[] heights = {2, 1, 5, 6, 2, 3};
        int[] heights = {1,2,3,4,5};
        System.out.println(Arrays.toString(heights));
        System.out.println(lrh.largestRectangleArea(heights));
    }
}
