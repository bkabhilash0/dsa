package arrays;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/4sum/description/">...</a>
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 */
public class FourSum {
    private void optimal(int[] nums, int target, List<List<Integer>> result) {
        // Implementation goes here
        int n = nums.length;
        java.util.Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates for the first number
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicates for the second number
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];
                    if (sum == target) {
                        List<Integer> quadruplet = new java.util.ArrayList<>();
                        quadruplet.add(nums[i]);
                        quadruplet.add(nums[j]);
                        quadruplet.add(nums[left]);
                        quadruplet.add(nums[right]);
                        result.add(quadruplet);
                        while (left < right && nums[left] == nums[left + 1])
                            left++; // Skip duplicates for the third number
                        while (left < right && nums[right] == nums[right - 1])
                            right--; // Skip duplicates for the fourth number
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> quadruplets;

            void init() {
                quadruplets = new ArrayList<>();
                optimal(nums, target, quadruplets);
            }

            @Override
            public List<Integer> get(int index) {
                return quadruplets.get(index);
            }

            @Override
            public int size() {
                if (quadruplets == null) {
                    init();
                }
                return quadruplets.size();
            }
        };
    }

    public static void main(String[] args) {
        FourSum fs = new FourSum();
        int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};
        int target = -294967296;
        java.util.List<java.util.List<Integer>> result = fs.fourSum(nums, target);
        System.out.println(result);
    }
}
