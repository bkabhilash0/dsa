package sliding_window;

/**
 * <a href="https://leetcode.com/problems/count-number-of-nice-subarrays/description/">Count Number of Nice Subarrays</a>
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 * Return the number of nice sub-arrays.
 */
public class CountNumberOfNiceSubarrays {
    // Time Complex: O(n^2)
    // Space Complex: O(1)
    private int bruteForce(int[] nums, int k) {
        int count = 0;
        int oddCount = 0;
        for (int i = 0; i < nums.length; i++) {
            oddCount = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] % 2 != 0) {
                    oddCount++;
                }
                if (oddCount == k) {
                    count++;
                } else if (oddCount > k) {
                    break;
                }
            }
        }
        return count;
    }

    // Time Complex: O(n)
    // Space Complex: O(n) We are using an array to store the count of prefix sums. The size of the array is n + 1 where n is the length of the input
    private int better(int[] nums, int k) {
        int sum = 0;
        int[] prefixSumMap = new int[nums.length + 1];
        prefixSumMap[0] = 1; // We have one way to have a sum of 0, which is to have an empty subarray.
        int count = 0;
        for (int num : nums) {
            sum += num % 2; // We are considering odd numbers as 1 and even numbers as 0.
            if (sum >= k) {
                count += prefixSumMap[sum - k]; // If we have a sum of sum - k, then we can add the current number to it to get a sum of sum which is equal to k.
            }
            prefixSumMap[sum]++;
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
            sum += nums[right] % 2; // We are considering odd numbers as 1 and even numbers as 0.
            while (sum > goal && left <= right) {
                sum -= nums[left] % 2; // We are considering odd numbers as 1 and even numbers as 0.
                left++;
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }

    // Convert this to the context of binary sum problem where we will consider odd numbers as 1 and even numbers as 0.
    // So the number of subarrays with sum equal to k is equal to the number of subarrays with sum less than or equal to k
    // minus the number of subarrays with sum less than or equal to k - 1.
    // Time Complex: O(2n) We are doing 2 passes, one for k and other for k - 1. So the time complexity is O(2n) which is equivalent to O(n)
    // Space Complex: O(1)
    private int optimal(int[] nums, int k) {
        return countOfSubarraysWithSumLessThanOrEqualToGoal(nums, k) - countOfSubarraysWithSumLessThanOrEqualToGoal(nums, k - 1);
    }

    // Prefix sum is faster as its O(n) time complexity and O(n) space complexity. Tradeoff is that it takes more space
    // The optimal approach is O(2n) time complexity and O(1) space complexity but it takes time as its 2 Pass.
    public int numberOfSubarrays(int[] nums, int k) {
        return better(nums, k);
    }

    public static void main(String[] args) {
        CountNumberOfNiceSubarrays countNumberOfNiceSubarrays = new CountNumberOfNiceSubarrays();
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        System.out.println(countNumberOfNiceSubarrays.numberOfSubarrays(nums, k));
    }
}
