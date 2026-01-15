package arrays;

import java.util.HashMap;

/**
 * <a href="https://leetcode.com/problems/single-element-in-a-sorted-array">...</a>
 * 540. Single Element in a Sorted Array
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 */
public class SingleElementInASortedArray {
    // Brute Force Approach - Using For Loops and counter variable
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private int bruteForce(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count = 0;
            for (int otherNum : nums) {
                if (num == otherNum) {
                    count++;
                }
            }
            if (count == 1) {
                return num;
            }
        }
        return -1;
    }

    // Better Approach - Since the array is sorted, we can check pairs of elements
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int better(int[] nums) {
        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // Better Using Maps - Using HashMap to store frequency of elements
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private int betterUsingMaps(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    // Optimal Approach - Modified Binary Search
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private int optimal(int[] nums) {
        // Edge Cases
        if (nums.length == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[nums.length - 1] != nums[nums.length - 2]) return nums[nums.length - 1];

        int left = 1, right = nums.length - 2;
        while (left <= right) {
            int mid = (left + right) / 2;

            // Check if this is the single element
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            // Check if the pattern is correct on the left side i.e. till the mid element
            if ((mid % 2 == 0 && nums[mid] == nums[mid + 1]) || (mid % 2 == 1 && nums[mid] == nums[mid - 1])) {
                // If the pattern is correct on the left side, move to the right side
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // This line should never be reached if the input is valid
    }

    public int singleNonDuplicate(int[] nums) {
        return optimal(nums);
    }

    public static void main(String[] args) {
        SingleElementInASortedArray finder = new SingleElementInASortedArray();
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        int singleElement = finder.singleNonDuplicate(nums);
        System.out.println("Single Element: " + singleElement);
    }
}
