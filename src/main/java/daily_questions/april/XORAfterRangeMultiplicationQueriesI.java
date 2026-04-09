package daily_questions.april;

/**
 * Date: April 8, 2026,
 * <a href="https://leetcode.com/problems/xor-after-range-multiplication-queries-i/">XOR After Range Multiplication Queries I</a>
 * You are given an integer array nums of length n and
 * a 2D integer array queries of size q, where queries[i] = [li, ri, ki, vi].
 * For each query, you must apply the following operations in order:
 * Set idx = li.
 * While idx <= ri:
 * Update: nums[idx] = (nums[idx] * vi) % (109 + 7)
 * Set idx += ki.
 * Return the bitwise XOR of all elements in nums after processing all queries.
 */
public class XORAfterRangeMultiplicationQueriesI {
    final int mod = 1000000007;

    // Time Complexity: O(q), where q is the total number of queries
    // Space Complexity: O(1), as we are modifying the input array in place and using only a constant amount of extra space
    public int xorAfterQueries(int[] nums, int[][] queries) {

        // Process each query
        for (int[] t : queries) {
            int l = t[0];
            int r = t[1];
            int k = t[2];
            int v = t[3];

            int idx = l;

            // Apply operation at step k
            while (idx <= r) {
                long temp = nums[idx];
                nums[idx] = (int) ((temp * v) % mod);
                idx += k;
            }
        }

        // Compute XOR of final array
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int[][] queries = {{0, 2, 1, 4}};
        XORAfterRangeMultiplicationQueriesI xorAfterRangeMultiplicationQueriesI = new XORAfterRangeMultiplicationQueriesI();
        System.out.println(xorAfterRangeMultiplicationQueriesI.xorAfterQueries(nums, queries)); // Output: 12
    }
}
