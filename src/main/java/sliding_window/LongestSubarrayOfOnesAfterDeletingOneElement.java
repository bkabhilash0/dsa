package sliding_window;

/**
 * <a href="https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/">Longest Subarray of 1's After Deleting One Element</a>
 * Given a binary array nums, you should delete one element from it.
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 * Return 0 if there is no such subarray.
 * <p>
 * Think it of a longest subarray of 1s with at most one 0,
 * we can use a sliding window approach to keep track of the number of 0s in the current window and adjust the window size accordingly.
 */
public class LongestSubarrayOfOnesAfterDeletingOneElement {
    // Time Complexity: O(N^2) where N is the length of the input array, we are using two nested loops to check all possible subarrays of the input array
    // Space Complexity: O(1) we are using a constant amount of space to store
    private int bruteForce(int[] nums) {
        int zeroCount = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            zeroCount = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) {
                    zeroCount++;
                }
                if (zeroCount > 1) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength - 1; // We need to subtract 1 from the maxLength because we are deleting one element from the subarray
    }

    // Time Complexity: O(N) where N is the length of the input array
    // Space Complexity: O(1) we are using a constant amount of space to store
    private int optimal(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            // Using if instead of while is optimal as the length is not changed until we find a valid window
            if (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        // We need to subtract 1 from the maxLength because we are deleting one element from the subarray
        return maxLength - 1;
    }

    public int longestSubarray(int[] nums) {
        return optimal(nums);
    }

    public static void main(String[] args) {
        LongestSubarrayOfOnesAfterDeletingOneElement longestSubarrayOfOnesAfterDeletingOneElement = new LongestSubarrayOfOnesAfterDeletingOneElement();
        int[] nums = new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1};
        System.out.println(longestSubarrayOfOnesAfterDeletingOneElement.longestSubarray(nums)); // Output: 5
    }
}
