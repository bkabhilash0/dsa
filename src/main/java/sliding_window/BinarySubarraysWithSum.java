package sliding_window;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/binary-subarrays-with-sum/description/">Binary Subarrays with Sum</a>
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 */
public class BinarySubarraysWithSum {
    // Time Complex: O(n^2)
    // Space Complex: O(1)
    private int bruteForce(int[] nums, int goal) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == goal) {
                    count++;
                }
                // There are no negative numbers so we are sure that once the sum is greater than the goal, it will never be equal to the goal again.
                if (sum > goal) {
                    break;
                }
            }
        }
        return count;
    }

    // Time Complex: O(n)
    // Space Complex: O(1)
    private int countOfSubarraysWithSumLessThanOrEqualToGoal(int[] nums, int goal) {
        int left = 0;
        int right = 0;
        int count = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum > goal && left <= right) {
                sum -= nums[left];
                left++;
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }

    // Time Complex: O(2n) We are doing 2 passes, one for goal and other for goal - 1. So the time complexity is O(2n) which is equivalent to O(n)
    private int optimal(int[] nums, int goal) {
        // We shall use a constructive algorithm where the
        // number of subarrays with sum equal to goal is equal to the number of subarrays with sum less than or equal to goal
        // minus the number of subarrays with sum less than or equal to goal - 1.
        return countOfSubarraysWithSumLessThanOrEqualToGoal(nums, goal) - countOfSubarraysWithSumLessThanOrEqualToGoal(nums, goal - 1);
    }

    // Time Complex: O(n)
    // Space Complex: O(n) We are using an array to store the count of prefix sums. The size of the array is n + 1 where n is the length of the input array.
    private int moreOptimal(int[] nums, int goal) {
        int sum = 0, res = 0;
        int[] count = new int[nums.length + 1];
        count[0] = 1;

        for (int i : nums) {
            sum += i;
            int rem = sum - goal;
            if (sum >= goal) {
                res += count[rem];
            }
            count[sum]++;
        }
        return res;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        return moreOptimal(nums, goal);
    }

    public static void main(String[] args) {
        BinarySubarraysWithSum binarySubarraysWithSum = new BinarySubarraysWithSum();
        int[] nums = {1, 0, 1, 0, 1};
//        int[] nums = {0, 0, 0, 0, 0};
        int goal = 2;
        System.out.println(binarySubarraysWithSum.numSubarraysWithSum(nums, goal));
    }
}
