package greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/jump-game-ii/description/">Jump Game II</a>
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 * 0 <= j <= nums[i] and i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 */
public class JumpGameII {
    private int executeBruteForce(int[] nums, int currentPosition, int jumps) {
        if (currentPosition >= nums.length - 1) {
            return jumps;
        }
        int maxJumpLength = nums[currentPosition];
        int result = Integer.MAX_VALUE;
        for (int jumpLength = 1; jumpLength <= maxJumpLength; jumpLength++) {
            result = Math.min(result, executeBruteForce(nums, currentPosition + jumpLength, jumps + 1));
        }
        return result;
    }

    // Time complexity: O(2^n)
    // leading to a branching factor of n at each step.
    private int bruteForce(int[] nums) {
        return executeBruteForce(nums, 0, 0);
    }

    private int executeBetter(int[] nums, int currentPosition, Integer[] dp) {
        if (currentPosition >= nums.length - 1) {
            return 0;
        }
        if (dp[currentPosition] != null) {
            return dp[currentPosition];
        }
        int maxJumpLength = nums[currentPosition];
        int result = Integer.MAX_VALUE;
        for (int jumpLength = 1; jumpLength <= maxJumpLength; jumpLength++) {
            // We need to check if the jump is within the bounds of the array before making the recursive call. This is because we cannot jump beyond the last index of the array.
            if (currentPosition + jumpLength < nums.length) {
                int subResult = executeBetter(nums, currentPosition + jumpLength, dp);
                result = Math.min(result, subResult != Integer.MAX_VALUE ? subResult+ 1 : subResult);
            }
        }
        dp[currentPosition] = result;
        return result;
    }

    // By using dynamic Programming we can store the results of the subproblems and avoid redundant calculations, thus reducing the time complexity to O(n^2).
    // Time complexity: O(n^2) due to the nested loops where the outer loop iterates through each index and the inner loop iterates through the possible jump lengths from that index.
    // Space complexity: O(n) due to the dp array used for memoization.
    private int better(int[] nums) {
        Integer[] dp = new Integer[nums.length];
        return executeBetter(nums, 0, dp);
    }

    private int optimal(int[] nums) {
        int currentEnd = 0;
        int rangeEnd = 0;
        int jumps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // Find the maximum reachable index from the current range of indices that we can jump. A range
            rangeEnd = Math.max(rangeEnd, i + nums[i]);

            // Check if I have covered all the indices in the current range, if so I need to make a jump and update the current end to the range end
            if (i == currentEnd) {
                jumps++;
                currentEnd = rangeEnd;
            }
        }

        return jumps;
    }

    public int jump(int[] nums) {
        return better(nums);
    }

    public static void main(String[] args) {
        JumpGameII jg = new JumpGameII();
        int[] nums = {2, 3, 0, 1, 4};
        System.out.println(jg.jump(nums));
    }
}
