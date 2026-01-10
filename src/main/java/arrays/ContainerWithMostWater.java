package arrays;

/*
 * <a href="https://leetcode.com/problems/container-with-most-water/description/">...</a>
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 */
public class ContainerWithMostWater {
    // Brute Force Approach: Check all pairs of lines
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private int bruteForce(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length - 1; i++){
            for(int j = i + 1; j < height.length; j++){
                int minHeight = Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i));
            }
        }
        return maxArea;
    }

    // Optimal Approach: Two Pointer Technique
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int optimal(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int maxArea = 0;
        while (start < end) {
//            int currentLeft = height[start];
//            int currentRight = height[end];
            int minHeight = Math.min(height[start], height[end]);
            int area = minHeight * (end - start);
            maxArea = Math.max(area, maxArea);

            while (start <= end && height[start] <= minHeight) {
                start++;
            }
            while (start <= end && height[end] <= minHeight) {
                end--;
            }
        }
        return maxArea;
    }

    public int maxArea(int[] height) {
        return bruteForce(height);
    }

    public static void main(String[] args) {
        ContainerWithMostWater cmw = new ContainerWithMostWater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxWater = cmw.maxArea(height);
        System.out.println("Maximum water that can be contained is: " + maxWater);
    }
}
