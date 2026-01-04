package arrays;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/3sum/description/">...</a>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 */
public class ThreeSum {
    // Time Complexity: O(n^3)
    // Space Complexity: O(1) excluding the result list
    private void bruteForce(int[] nums, List<List<Integer>> result) {
        // Implementation goes here
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        triplet.sort(Integer::compareTo);
                        // Can also use a set here to avoid duplicates. Using set can remove the if condition below.
                        if (!result.contains(triplet)) {
                            result.add(triplet);
                        }
                    }
                }
            }
        }
        // If using a set to avoid duplicates, convert set to list here and store that to the result
    }

    // Use a hashset to reduce one loop
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) excluding the result list
    private void better(int[] nums, List<List<Integer>> result) {
        // Implementation goes here
        HashSet<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            set.clear();
            for (int j = i + 1; j < n; j++) {
                int rem = -(nums[i] + nums[j]);
                if (set.contains(rem)) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(rem);
                    triplet.sort(Integer::compareTo);
                    if (!result.contains(triplet)) {
                        result.add(triplet);
                    }
                }
                set.add(nums[j]);
            }
        }
    }

    // Use two pointers after sorting the array
    // Time Complexity: O(n^2)
    // Space Complexity: O(1) excluding the result list
    private void optimal(int[] nums, List<List<Integer>> result) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[left]);
                    triplet.add(nums[right]);
                    result.add(triplet);
                    // Move left and right to the next different numbers to avoid duplicates
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> result;

            private void init() {
                result = new ArrayList<>();
                optimal(nums, result);
            }

            @Override
            public List<Integer> get(int index) {
                return result.get(index);
            }

            @Override
            public int size() {
                if (result == null) {
                    init();
                }
                return result.size();
            }
        };
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum.threeSum(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(result);
    }
}
