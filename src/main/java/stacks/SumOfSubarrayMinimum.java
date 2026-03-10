package stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/sum-of-subarray-minimums/description/">Sum of Subarray Minimums</a>
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * For example, given the input [3,1,2,4], return 17.
 * The subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. The minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1. The sum is 17.
 */
public class SumOfSubarrayMinimum {
    int MOD = 1_000_000_007;

    // Time Complexity: O(N^3)
    // Space Complexity: O(1)
    private int bruteForce(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                sum += min;
            }
        }
        return sum;
    }

    private int[] getPrevMin(int[] arr) {
        int[] res = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();
        res[0] = -1;
        stack.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = stack.peek();
            }
            stack.push(i);
        }
        return res;
    }

    private int[] getNextMin(int[] arr) {
        int[] res = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();
        res[arr.length - 1] = arr.length;
        stack.push(arr.length - 1);
        for (int i = arr.length - 2; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[i] = arr.length;
            } else {
                res[i] = stack.peek();
            }
            stack.push(i);
        }
        return res;
    }

    // Time Complexity: O(3N)
    // Space Complexity: O(3N) for the prevMin and nextMin arrays and stack
    private int optimal(int[] arr) {
        int[] prevMin = getPrevMin(arr);
        int[] nextMin = getNextMin(arr);
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftCount = i - prevMin[i];
            int rightCount = nextMin[i] - i;
            count += (long) arr[i] * leftCount * rightCount;
            count %= MOD;
        }
        return (int)count;
    }

    public int sumSubarrayMins(int[] arr) {
        return optimal(arr);
    }

    public static void main(String[] args) {
        SumOfSubarrayMinimum ssm = new SumOfSubarrayMinimum();
        int[] arr = {3, 1, 2, 4};
        System.out.println(ssm.sumSubarrayMins(arr));
    }
}
