package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/single-number-ii">...</a>
 * 137. Single Number II
 * Given an integer array nums where every element appears three times except for one, which appears exactly once.
 * Find the single element and return it.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
public class SingleNumber2 {
    // Brute Force Approach - Using For Loops and counter variable
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private int bruteForce(int[] nums) {
        for (int j : nums) {
            int count = 0;
            for (int num : nums) {
                if (j == num) {
                    count++;
                }
            }
            if (count == 1) {
                return j;
            }
        }
        return -1;
    }

    // Better Approach - Using HashMap to store frequency of elements
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private int better(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return -1;
    }

    // Optimal Approach - Using Bit Manipulation
    // We can use two variables to keep track of the bits that have appeared once and twice respectively.
    // When a bit appears the third time, it should be removed from both variables.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int optimal(int[] nums){
        int ones = 0, twos = 0;
        for(int num: nums){
            // If a bit is set in num and not in twos, it means it has appeared once
            ones = (num ^ ones) & ~ twos;
            // If a bit is set in num and also in ones, it means it has appeared twice
            twos = (num ^ twos) & ~ ones;
        }
        return ones;
    }

    public int singleNumber(int[] nums) {
        return optimal(nums);
    }

    public static void main(String[] args) {
        SingleNumber2 sn = new SingleNumber2();
        int[] nums = {0, 1, 0, 1, 0, 1, 99};
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        int singleNumber = sn.singleNumber(nums);
        System.out.println("Single Number: " + singleNumber);
    }
}
