package sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/subarrays-with-k-different-integers/description/">Subarray With K Different Integers</a>
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * A good array is an array where the number of different integers in that array is exactly k.
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 */
public class SubarrayWithKDifferentIntegers {
    // Time Complex: O(n^2)
    // Space Complex: O(n)
    private int bruteForce(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            set.clear();
            for (int j = i; j < nums.length; j++) {
                set.add(nums[j]);
                if (set.size() == k) {
                    result++;
                }
                if (set.size() > k) {
                    break;
                }
            }
        }
        return result;
    }

    // Time Complex: O(n)
    // Space complex: O(n)
    // This is bit slower due to map, this can be done using array as wel
    // We know the limit of the numbers in the array is 1 <= nums[i] <= nums.length,
    // so we can use an array of size nums.length + 1 to store the frequency of the numbers in the current window instead of using a HashMap
    private int countOfSubarraysWithAtMostK(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int count = 0;
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        while (r < nums.length) {
            freqMap.put(nums[r], freqMap.getOrDefault(nums[r], 0) + 1);
            if (freqMap.get(nums[r]) == 1) {
                k--;
            }
            while (k < 0) {
                freqMap.put(nums[l], freqMap.getOrDefault(nums[l], 0) - 1);
                if (freqMap.get(nums[l]) == 0) {
                    k++;
                }
                l++;
            }
            count += r - l + 1;
            r++;
        }
        return count;
    }

    // Time Complex: O(n)
    // Space Complex: O(n)
    private int countOfSubarraysWithAtMostKSimplified(int[] nums, int k) {
        int distinct = 0;
        int[] map = new int[nums.length + 1];
        int l = 0;
        int count = 0;
        for (int r = 0; r < nums.length; r++) {
            map[nums[r]]++;
            if (map[nums[r]] == 1) {
                distinct++;
            }
            while (distinct > k) {
                map[nums[l]]--;
                if (map[nums[l]] == 0) {
                    distinct--;
                }
                l++;
            }
            count += r - l + 1;
        }
        return count;
    }

    // Time Complex: O(2n) = O(n) as we are calling the countOfSubarraysWithAtMostK function twice
    // Space Complex: O(1) as the size of the freq array is fixed at 10
    private int optimal(int[] nums, int k) {
        return countOfSubarraysWithAtMostKSimplified(nums, k) - countOfSubarraysWithAtMostKSimplified(nums, k - 1);
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return optimal(nums, k);
    }

    public static void main(String[] args) {
        SubarrayWithKDifferentIntegers subarrayWithKDifferentIntegers = new SubarrayWithKDifferentIntegers();
        int[] nums = {27, 27, 43, 28, 11, 20, 1, 4, 49, 18, 37, 31, 31, 7, 3, 31, 50, 6, 50, 46,
                4, 13, 31, 49, 15, 52, 25, 31, 35, 4, 11, 50, 40, 1, 49, 14, 46, 16, 11, 16, 39,
                26, 13, 4, 37, 39, 46, 27, 49, 39, 49, 50, 37, 9, 30, 45, 51, 47, 18, 49, 24, 24,
                46, 47, 18, 46, 52, 47, 50, 4, 39, 22, 50, 40, 3, 52, 24, 50, 38, 30, 14, 12, 1,
                5, 52, 44, 3, 49, 45, 37, 40, 35, 50, 50, 23, 32, 1, 2};
        int k = 20;
        System.out.println(subarrayWithKDifferentIntegers.subarraysWithKDistinct(nums, k));
    }
}
