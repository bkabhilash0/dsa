package daily_questions.april;

/**
 * Date: April 13, 2026,
 * <a href="https://leetcode.com/problems/minimum-distance-to-the-target-element">Minimum Distance to the Target Element</a>
 * Given an integer array nums (0-indexed) and two integers target and start,
 * find an index i such that nums[i] == target and abs(i - start) is minimized.
 * Note that abs(x) is the absolute value of x.
 * Return abs(i - start).
 * It is guaranteed that target exists in nums.
 */
public class MinimumDistanceToTheTargetElement {
    // Time Complexity: O(N) but average case O(N/2) because we are moving both the pointers in the opposite direction
    // Space Complexity: O(1)
    public int getMinDistance(int[] nums, int target, int start) {
        // The target can be either on the left or right of the start index
        // So we shall have a two pointer where we move both the pointers in the opposite direction
        // Handle the edge case where the start index has the target
        if (nums[start] == target) return 0;
        int left = start - 1;
        int right = start + 1;
        int minDistance = Integer.MAX_VALUE;
        while (left >= 0 || right < nums.length) {
            if (left >= 0 && nums[left] == target) {
                minDistance = Math.min(minDistance, Math.abs(left - start));
                break;
            }
            if (right < nums.length && nums[right] == target) {
                minDistance = Math.min(minDistance, Math.abs(right - start));
                break;
            }
            if (left >= 0) {
                left--;
            }
            if (right < nums.length) {
                right++;
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 5;
        int start = 3;
        MinimumDistanceToTheTargetElement minimumDistanceToTheTargetElement = new MinimumDistanceToTheTargetElement();
        int result = minimumDistanceToTheTargetElement.getMinDistance(nums, target, start);
        System.out.println("Minimum distance: " + result);
    }
}
