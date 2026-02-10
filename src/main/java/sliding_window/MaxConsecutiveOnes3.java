package sliding_window;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 * You need at most 2 0's in the longest subarray of 1's. So we can use a sliding window approach to find the longest subarray with at most 2 0's.
 * Think of it as the longest subarray with at most 2 0's. We can use a sliding window approach to find the longest subarray with at most 2 0's.
 */
public class MaxConsecutiveOnes3 {
    // Time Complexity: O(n^3) This is because we are generating all subarrays and counting the number of 0's in each subarray. This is a brute force approach and is not efficient for large input sizes.
    // Space Complexity: O(1)
    private int bruteForce(int[] nums, int k) {
        // Generate all subarrays and count the number of 0's in each subarray. If the number of 0's is less than or equal to k, update the maximum length.
        return 0;
    }

    // Time Complexity: O(n+n) = O(2n) = O(n)
    // Space Complexity: O(1)
    private int better(int[] nums, int k) {
        int l = 0, r = 0;
        int zeroCount = 0;
        int maxLength = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                zeroCount++;
            }

            // If the number of 0's in the current window exceeds k, shrink the window from the left until the number of 0's is less than or equal to k
            while (zeroCount > k) {
                if (nums[l] == 0) {
                    zeroCount--;
                }
                l++;
            }

            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }
        return maxLength;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int optimal(int[] nums, int k) {
        int l = 0, r = 0;
        int zeroCount = 0;
        int maxLength = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                zeroCount++;
            }

            if (zeroCount > k) {
                if (nums[l] == 0) {
                    zeroCount--;
                }
                l++;
            }

            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }
        return maxLength;
    }

    public int longestOnes(int[] nums, int k) {
        return optimal(nums, k);
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes3 maxConsecutiveOnes3 = new MaxConsecutiveOnes3();
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        int result = maxConsecutiveOnes3.longestOnes(nums, k);
        System.out.println(result);
    }
}
