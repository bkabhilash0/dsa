package binary_search;

import commons.Utils;

/**
 * <a href="https://leetcode.com/problems/split-array-largest-sum/description/">...</a>
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 * Return the minimized largest sum of the split.
 * A subarray is a contiguous part of the array.
 */
public class SplitArrayLargestSum {
    private int getSubarraysCount(int[] nums, int maxSumAllowed) {
        int subarraysCount = 1;
        int currentSubarraySum = 0;
        for (int num : nums) {
            if (currentSubarraySum + num > maxSumAllowed) {
                subarraysCount++;
                currentSubarraySum = 0;
            }
            currentSubarraySum += num;
        }
        return subarraysCount;
    }

    // Brute Force Approach - Linear Search
    // Time Complexity: O(n * m) where n is the number of elements in nums and m is the range between max element and sum of elements
    // Space Complexity: O(1)
    private int bruteForce(int[] nums, int k) {
        int start = Utils.getMax(nums);
        int end = Utils.getSummation(nums);
        for (int i = start; i <= end; i++) {
            int subarraysNeeded = getSubarraysCount(nums, i);
            if (subarraysNeeded <= k) {
                return i;
            }
        }
        return -1;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(n * log m) where n is the number of elements in nums and m is the range between max element and sum of elements
    // Space Complexity: O(1)
    private int optimal(int[] nums, int k) {
        int start = Utils.getMax(nums);
        int end = Utils.getSummation(nums);
        while (start <= end) {
            int mid = (start + end) / 2;
            int subarraysNeeded = getSubarraysCount(nums, mid);
            if (subarraysNeeded <= k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

        public int splitArray ( int[] nums, int k){
            if (k > nums.length) return -1;
            return bruteForce(nums, k);
        }
        public static void main (String[]args){
            SplitArrayLargestSum sals = new SplitArrayLargestSum();
            int[] nums = {2, 3, 1, 1, 1, 1, 1};
            int k = 5;
            System.out.println("nums: " + java.util.Arrays.toString(nums));
            System.out.println("\nk: " + k);
            int result = sals.splitArray(nums, k);
            System.out.println("Result: " + result);
            // Expected Output: 3
        }
    }
