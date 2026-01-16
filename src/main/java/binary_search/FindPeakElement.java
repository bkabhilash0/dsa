package binary_search;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/find-peak-element">...</a>
 * 162. Find Peak Element
 * A peak element is an element that is strictly greater than its neighbors.
 * Given an integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 * You may imagine that nums[-1] = nums[n] = -∞.
 * You must write an algorithm that runs in O(log n) time.
 */
public class FindPeakElement {
    // Brute Force Approach - Linear Search
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int bruteForce(int[] nums) {
        if (nums.length == 1) return 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] > nums[i + 1]) {
                    return i;
                }
            } else if (i == nums.length - 1) {
                if (nums[i] > nums[i - 1]) {
                    return i;
                }
            } else {
                if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Optimal Approach - Modified Binary Search
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private int optimal(int[] nums) {
        // Check for edge cases
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;

        int left = 1;
        int right = nums.length - 2;
        while (left <= right) {
            int mid = (left + right) / 2;
            // Check if mid is a peak
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            // Check which side is the mid -> the increasing half or the decreasing half
            if (nums[mid + 1] > nums[mid]) {
                // Mid is in the increasing half, so the peak must be on the right side
                left = mid + 1;
            } else if (nums[mid + 1] < nums[mid]) {
                // Mid is in the decreasing half, so the peak must be on the left side
                right = mid - 1;
            } else {
                // If there is a plateau, we can move either side
                // Plateau means a dip where nums[mid] !> nums[mid - 1] and nums[mid] !< nums[mid + 1] -  a dip in a V
                // This happens when there are multiple peaks
                // This condition will never be met as per the problem constraints
                left++;
            }
        }
        return -1;
    }

    public int findPeakElement(int[] nums) {
        return optimal(nums);
    }

    public static void main(String[] args) {
        FindPeakElement finder = new FindPeakElement();
//        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        int[] nums = {3, 4, 3, 2, 1};
        System.out.println(Arrays.toString(nums));
        System.out.println(finder.findPeakElement(nums));
    }
}
