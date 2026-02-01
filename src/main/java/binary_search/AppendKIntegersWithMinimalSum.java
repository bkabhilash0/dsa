package binary_search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/append-k-integers-with-minimal-sum/description/">...</a>
 * You are given an integer array nums and an integer k.
 * Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.
 * Return the sum of the k integers appended to nums.
 */
public class AppendKIntegersWithMinimalSum {
    // Brute Force Approach - Using HashSet
    // Time Complexity: O(n) + O(m) where n is the length of nums and m is the number of integers needed to find to append
    // Space Complexity: O(n)
    private long bruteForce(int[] nums, int k) {
        HashSet<Integer> numSet = new HashSet<>();
        long result = 0;
        for (int num : nums) {
            numSet.add(num);
        }

        int count = 1;
        int i = 1;
        while (count <= k) {
            if (!numSet.contains(i)) {
                result += i;
                count++;
            }
            i++;
        }
        return result;
    }

    // Better Approach - Sorting
    // Time Complexity: O(n log n) + O(n) due to sorting
    // Space Complexity: O(1)
    private long optimal(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Long> set = new HashSet<>();
        long blockedSum = 0;
        for (long num : nums) {
            // Assuming there are no duplicates
//            if (num <= k) {
//                blockedSum += num;
//                k++;
//            }
            // In our problem statement, duplicates may exist
            if (!set.contains(num) && num <= k) {
                blockedSum += num;
                k++;
                // We need to add only the number that are below or equal to k as only these are need to check for duplicates and generate block the sum
                set.add(num);
            }
        }
        long actualSum = ((long) k * (k + 1)) / 2;
        return actualSum - blockedSum;
    }

    public long minimalKSum(int[] nums, int k) {
        return optimal(nums, k);
    }

    public static void main(String[] args) {
        AppendKIntegersWithMinimalSum akims = new AppendKIntegersWithMinimalSum();
        int[] nums = {96, 44, 99, 25, 61, 84, 88, 18, 19, 33, 60, 86, 52, 19, 32, 47, 35, 50, 94, 17, 29, 98, 22, 21, 72, 100, 40, 84};
//        int[] nums = {1, 4, 25, 10, 2};
        int k = 35;
        System.out.println("nums: " + java.util.Arrays.toString(nums));
        System.out.println("\nk: " + k);
        long result = akims.minimalKSum(nums, k);
        System.out.println("Result: " + result);
    }
}
