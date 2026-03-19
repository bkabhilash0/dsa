package stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/sliding-window-maximum/description/">Sliding Window Maximum</a>
 * You are given an array of integers nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 * Return the max sliding window.
 */
public class SlidingWindowMaximum {
    // Time Complexity: O(n*k)
    // Space Complexity: O(1), we aren't using any extra space, we are just storing the result in an array
    private int[] bruteForce(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[index++] = max;
        }
        return res;
    }

    // Time Complexity: O(n) + O(k)
    // Space Complexity: O(k)
    private int[] optimal(int[] nums, int k) {
        // Maintain a decreasing monotonic stack - Store the indexes
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // Remove Elements from back of the dequeue until the top is > current
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                // Since its a dequeue we are using the remove Last term
                deque.removeLast();
            }

            // Check if the front index is inside the current window, the front index has to be > (i - k)
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                // Pop from the front
                deque.removeFirst();
            }
            // Add the current index to the back of the dequeue
            // We use addLast since its a dequeue and not a stack
            deque.addLast(i);
            if(i >= k - 1){
                res[index++] = nums[deque.getFirst()];
            }
        }
        return res;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        return optimal(nums, k);
    }

    public static void main(String[] args) {
        SlidingWindowMaximum swm = new SlidingWindowMaximum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = swm.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
