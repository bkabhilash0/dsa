package stacks;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/trapping-rain-water/description/">Trapping Rain Water</a>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * For example, given the input [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * The above elevation map (black section) is represented by array [4,2,0,3,2,5]. In this case, 9 units of rain water (blue section) are being trapped.
 */
public class TrappingRainWater {
    // For every building, we need to get the max height of both left and right buildings and then take the min of that.
    // Subtract the height of the current building from the min to get the amount of water trapped in that building or pos
    // Time Complexity: O(N^2) where N is the number of buildings, we are iterating through the array and for each building we are iterating through the left and right side to get the max height
    // Space Complexity: O(1) since we are not using any extra space
    private int bruteForce(int[] height) {
        int water = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = Integer.MIN_VALUE;
            int rightMax = Integer.MIN_VALUE;
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            water += Math.min(leftMax, rightMax) - height[i];
        }
        return water;
    }

    // Rather than always looping to find the left and right max, we shall pre create two arrays which holds the left and right max for each building
    // Time Complexity: O(3N) where N is the number of buildings, we are iterating through the array three times, once to create the left max array, once to create the right max array and once to calculate the water trapped
    // Space Complexity: O(2N) for the left and right max arrays
    private int better(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int water = 0;

        for (int i = 1; i < height.length - 1; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return water;
    }

    // Time Complexity: O(N) where N is the number of buildings, we are iterating through the array once
    // Space Complexity: O(1) since we are not using any extra space
    private int optimal(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    // We do not add water here coz, since the current building is the tallest in the left side now, we do not have any tallest before this so it can't trap any water as left side do not have any taller building to trap water
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    // We do not trap water here coz, the current building is the new tallest so there are no taller building after this to trap water,
                    // left side has a taller building but right side do not have any taller building to trap water
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }

    public int trap(int[] height) {
        return optimal(height);
    }

    private static void runTest(TrappingRainWater trw, int[] height, int expected) {
        int result = trw.trap(height);

        System.out.print("Input: [");
        for (int i = 0; i < height.length; i++) {
            System.out.print(height[i]);
            if (i < height.length - 1) System.out.print(", ");
        }
        System.out.println("] -> Output: " + result + " | Expected: " + expected);
    }

    public static void main(String[] args) {
        TrappingRainWater trw = new TrappingRainWater();
        runTest(trw, new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
        runTest(trw, new int[]{4, 2, 0, 3, 2, 5}, 9);
        runTest(trw, new int[]{2, 0, 2}, 2);
        runTest(trw, new int[]{3, 0, 0, 2, 0, 4}, 10);
        runTest(trw, new int[]{1, 2, 3, 4, 5}, 0);
        runTest(trw, new int[]{5, 4, 3, 2, 1}, 0);
    }

}
