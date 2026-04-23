package daily_questions.april;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Date: April 23, 2026,
 * <a href="https://leetcode.com/problems/sum-of-distances/description">Sum of Distances</a>
 * You are given a 0-indexed integer array nums. There exists an array arr of length nums.length,
 * where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i.
 * If there is no such j, set arr[i] to be 0.
 * Return the array arr.
 */
public class SumOfDistances {
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    private long[] bruteForce(int[] nums) {
        long[] result = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            long sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j] && i != j) {
                    sum += Math.abs(i - j);
                }
            }
            result[i] = sum;
        }
        return result;
    }

    // Time Complexity: O(N)
    // Space Complexity: O(N)
    private long[] better(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new java.util.ArrayList<>()).add(i);
        }
        for (List<Integer> indices : map.values()) {
            int size = indices.size();
            if (size == 1) continue;
            long[] prefixSum = new long[size];
            prefixSum[0] = indices.getFirst();
            for (int i = 1; i < size; i++) {
                prefixSum[i] = prefixSum[i - 1] + indices.get(i);
            }

            // Now calculate for each index group
            // [1,2,3,5,6] -> currentIndex = 3 so left side is [1,2] and right side is [5]
            // (3 - 1) + (3 - 2) + (5 - 3) + (6 - 3) = (3 + 3) - (1 + 2) + (5 + 6) - (3 + 3)
            // (leftCount * currentIndex - (left Sum)) + (rightSum - (rightCount * currentIndex))
            for (int i = 0; i < size; i++) {
                long currentIndex = indices.get(i);
                long leftCount = i;
                long leftSum = i == 0 ? 0 : prefixSum[i - 1];
                long leftContribution = (currentIndex * leftCount) - leftSum;

                int rightCount = size - i - 1;
                long rightSum = prefixSum[size - 1] - prefixSum[i];
                long rightContribution = rightSum - (currentIndex * rightCount);
                result[(int) currentIndex] = leftContribution + rightContribution;
            }
        }
        return result;
    }

    public long[] distance(int[] nums) {
        return better(nums);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 1, 2};
        SumOfDistances sumOfDistances = new SumOfDistances();
        long[] result = sumOfDistances.distance(nums);
        System.out.println("Sum of Distances: " + Arrays.toString(result));
    }
}
