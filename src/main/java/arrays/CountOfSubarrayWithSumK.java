package arrays;

import java.util.HashMap;

/*
* https://leetcode.com/problems/subarray-sum-equals-k/description/
* Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
* A subarray is a contiguous non-empty sequence of elements within an array.
* */
public class CountOfSubarrayWithSumK {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            int rem = sum - k;
            if (map.containsKey(rem)) {
                count += map.get(rem);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    public static void main(String[] args) {
        CountOfSubarrayWithSumK csws = new CountOfSubarrayWithSumK();
        int[] nums = {1, 2, 3};
        int k = 3;
        int count = csws.subarraySum(nums, k);
        System.out.println("Count of subarrays with sum " + k + " is: " + count);
    }
}
