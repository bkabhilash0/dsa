package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://www.geeksforgeeks.org/problems/next-greater-element-i/1">Next Greater Element I</a>
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
 * If there is no next greater element, then the answer for this query is -1.
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 */
public class NextGreaterElementI {
    // M = nums1.length, N = nums2.length
    // Time Complexity: O(N) + O(M)
    // Space Complexity: O(100001) + (N) for the map and the monotonic stack
    private int[] intuition(int[] nums1, int[] nums2) {
        // Can replace hashmap with an array of size 10000 since the constraints on the values in nums1 and nums2 are between 0 and 10000, this will reduce the space complexity to O(N) for the monotonic stack
        // We can map the values in number 2 to their next greater element rather then their indices
        int[] map = new int[10001];
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums2.length;
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                stack.pop();
            }
            map[nums2[i]] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map[nums1[i]];
        }

        return nums1;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        return intuition(nums1, nums2);
    }

    public static void main(String[] args) {
        NextGreaterElementI nge = new NextGreaterElementI();
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] res = nge.nextGreaterElement(nums1, nums2);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
