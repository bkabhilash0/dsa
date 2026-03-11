package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/sum-of-subarray-ranges/description/">Sum of Subarray Ranges</a>
 * Given an integer array nums of size n, return the sum of all subarray ranges of nums.
 * The range of a subarray is the difference between the maximum and minimum element in the subarray.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * For example, given the input [1,2,3], return 4.
 * The subarrays are [1], [2], [3], [1,2], [2,3], [1,2,3]. The ranges are 0, 0, 0, 1, 1, 2. The sum is 4.
 */
public class SumOfSubarrayRanges {
    // Time Complexity: O(N^2)
    // Space Complexity: O(1)
    private long bruteForce(int[] nums) {
        long res = 0;
        int min;
        int max;
        for (int i = 0; i < nums.length; i++) {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            for (int j = i; j < nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                res += max - min;
            }
        }

        return res;
    }

    private int[] getPrevMinsIndex(int[] nums) {
        int[] prevMins = new int[nums.length];
        prevMins[0] = -1;
        Deque<Integer> stack = new ArrayDeque<>();
        // We push the index of the element
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                stack.pop();
            }
            prevMins[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return prevMins;
    }

    private int[] getNextEqsOrMinsIndex(int[] nums) {
        int[] nextEqsOrMins = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        // Instead of marking it as -1 we can mark it as len of the array for our calculations later on
        nextEqsOrMins[nums.length - 1] = nums.length;
        stack.push(nums.length - 1);
        for (int i = nums.length - 2; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
                stack.pop();
            }
            nextEqsOrMins[i] = stack.isEmpty() ? nums.length : stack.peek();
            stack.push(i);
        }
        return nextEqsOrMins;
    }

    private int[] getPrevMaxIndex(int[] nums) {
        int[] prevMax = new int[nums.length];
        prevMax[0] = -1;
        Deque<Integer> stack = new ArrayDeque<>();
        // We push the index of the element
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                stack.pop();
            }
            prevMax[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return prevMax;
    }

    private int[] getNextEquOrMaxIndex(int[] nums) {
        int[] nextEqOrMax = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        // Instead of marking it as -1 we can mark it as len of the array for our calculations later on
        nextEqOrMax[nums.length - 1] = nums.length;
        stack.push(nums.length - 1);
        for (int i = nums.length - 2; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                stack.pop();
            }
            nextEqOrMax[i] = stack.isEmpty() ? nums.length : stack.peek();
            stack.push(i);
        }
        return nextEqOrMax;
    }

    // Time Complexity: O(5N)
    // Space Complexity: O(4N)
    // Another approach would be rather than pre-calculating the prev and next mins and max,
    // We calculate the count of next and prev mins and max in the same loop and calculate. This doesn't require stack
    private long optimal(int[] nums) {
        // We can use the same approach as sum of subarray minimums and sum of subarray maximums to get the sum of subarray ranges
        int[] prevMins = getPrevMinsIndex(nums);
        int[] nextEqsOrMins = getNextEqsOrMinsIndex(nums);
        int[] prevMax = getPrevMaxIndex(nums);
        int[] nextEqOrMax = getNextEquOrMaxIndex(nums);
        int leftMinCount,rightEqOrMinCount,leftMaxCount,rightEqOrMaxCount;
        long sumOfSubarrayMins = 0;
        long sumOfSubarrayMax = 0;
        for (int i = 0; i < nums.length; i++) {
            leftMinCount = i - prevMins[i];
            rightEqOrMinCount = nextEqsOrMins[i] - i;
            leftMaxCount = i - prevMax[i];
            rightEqOrMaxCount = nextEqOrMax[i] - i;
            sumOfSubarrayMins += (long) leftMinCount * rightEqOrMinCount * nums[i];
            sumOfSubarrayMax += (long) leftMaxCount * rightEqOrMaxCount * nums[i];
        }
        return sumOfSubarrayMax - sumOfSubarrayMins;
    }

    public long subArrayRanges(int[] nums) {
        return optimal(nums);
    }

    public static void main(String[] args) {
        SumOfSubarrayRanges sosr = new SumOfSubarrayRanges();
        int[] nums = {1, 2, 3};
        long res = sosr.subArrayRanges(nums);
        System.out.println(res);
    }
}
