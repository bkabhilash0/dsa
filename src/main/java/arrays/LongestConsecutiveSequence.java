package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * <a href="https://leetcode.com/problems/longest-consecutive-sequence/description/">...</a>
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 */
public class LongestConsecutiveSequence {
    private int bruteForce(int[] nums) {
        return 0;
    }

    // Time Complexity: O(n log n) + O(n) = O(n log n)
    // Space Complexity: O(1)
    // This is accepted on LeetCode with 21 ms
    private int better(int[] nums) {
        Arrays.sort(nums);
        int currentNumber = Integer.MIN_VALUE;
        int currentStreak = 0;
        int maxStreak = 0;

        for (int num : nums) {
            if (num == currentNumber) {
                continue;
            } else if (num == currentNumber + 1) {
                currentNumber = num;
                currentStreak++;
            } else {
                currentStreak = 1;
                currentNumber = num;
            }
            maxStreak = Math.max(maxStreak, currentStreak);
        }

        return maxStreak;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // This is exceeding the Time Limit on LeetCode
    private int optimal(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        // The default result is 1 as a single element is also a sequence
        int result = 1;
        HashSet<Integer> set = new HashSet<>();
        // Add all elements to the set
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            // Check if it's the start of a sequence
            if (set.contains(num - 1)) {
                continue;
            }
            int count = 1;
            int nextNum = num + 1;
            // Count the length of the sequence
            while (set.contains(nextNum)) {
                count++;
                nextNum++;
            }
            result = Math.max(result, count);
        }
        return result;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // This is showing high runtime on LeetCode
    private int optimalUsingOrderedSet(int[] nums) {
        // The default result is 1 as a single element is also a sequence
        int result = 0;
        TreeSet<Integer> set = new TreeSet<>();
        // Add all elements to the set
        for (int num : nums) {
            set.add(num);
        }
        System.out.println(set);
        int prev = Integer.MIN_VALUE;
        int count = 0;
        for (int num : set) {
            // Since the set is in sorted order, use can compare the previous element to detect if it's a sequence
            if (prev == Integer.MIN_VALUE || prev == num - 1) {
                count++;
            } else {
                count = 1;
            }
            result = Math.max(result, count);
            prev = num;
        }
        return result;
    }

    public int longestConsecutive(int[] nums) {
        return optimalUsingOrderedSet(nums);
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int length = lcs.longestConsecutive(nums);
        System.out.println("The length of the longest consecutive sequence is: " + length);
    }
}
