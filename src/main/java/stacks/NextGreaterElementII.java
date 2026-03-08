package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://www.geeksforgeeks.org/problems/next-greater-element-ii/1">Next Greater Element II</a>
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 * The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number.
 * If it doesn't exist, return -1 for this number.
 */
public class NextGreaterElementII {
    // Time Complexity: O(N) where N is the length of the input array, we are iterating through the array twice in the worst case
    // Space Complexity: O(N) in the worst case when the input array is in decreasing order, we are storing all the elements in the stack
    private int[] solveNextGreaterElement(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            int val = nums[i % nums.length];
            while (!stack.isEmpty() && val >= stack.peek()) {
                stack.pop();
            }
            if(i < nums.length) {
                nums[i % nums.length] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(val);
        }
        return nums;
    }

    public int[] nextGreaterElements(int[] nums) {
        return solveNextGreaterElement(nums);
    }

    public static void main(String[] args) {
        NextGreaterElementII nge = new NextGreaterElementII();
        int[] nums = {1, 2, 1};
        int[] res = nge.nextGreaterElements(nums);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
