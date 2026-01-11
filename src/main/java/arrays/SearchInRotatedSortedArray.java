package arrays;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/search-in-rotated-sorted-array
 * 33. Search in Rotated Sorted Array
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class SearchInRotatedSortedArray {
    // Brute Force Approach - Do a linear search
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int bruteForceSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Optimal Approach - Modified Binary Search
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private int optimal(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // Check which side is sorted
            // Left side is sorted - if the mid element is greater than or equal to the right most or the last element
            if (nums[mid] >= nums[right]) {
                // Check if the left side has the target
                if(nums[left] <= target && target <= nums[mid]){
                    right = mid - 1;
                }
                // Target is in the right side
                else{
                    left = mid + 1;
                }
            } else {
                // Check if the right side has the target
                if(nums[mid] <= target && target <= nums[right]){
                    left = mid + 1;
                }
                // Target is in the left side
                else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        return optimal(nums, target);
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray sisr = new SearchInRotatedSortedArray();
        int[] nums = {3, 4, 5, 6, 1, 2};
        System.out.println(Arrays.toString(nums));
        for (int num : nums) {
//            int target = 4;
            System.out.println("Num to be searched: " + num + " Found in index: " + sisr.search(nums, num));
        }
        System.out.println("Num to be searched: " + 9 + " Found in index: " + sisr.search(nums, 9));
    }
}
