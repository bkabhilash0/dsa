package arrays;

/*
* https://leetcode.com/problems/maximum-subarray/description/
* Given an integer array nums, find the subarray with the largest sum, and return its sum.
*/
public class SubarrayWithMaxSum {
    // Kadane's Algorithm
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // Key Insight: If the current sum becomes negative, reset it to zero, as starting a new subarray may yield a higher sum.
    // Explanation: We iterate through the array, maintaining a running sum of the current subarray.
    // If at any point this sum becomes negative, we reset it to zero, as continuing with a negative sum would only decrease the potential maximum sum of future subarrays.
    // Throughout the iteration, we keep track of the maximum sum encountered.
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int result = Integer.MIN_VALUE;
        for(int num : nums){
            sum += num;
            // Check the max first else for an array with a single element fails like [-1]
            result = Math.max(sum, result);
            if(sum < 0){
                sum = 0;
            }
        }
        System.gc();
        return result;
    }
    public static void main(String[] args) {
        SubarrayWithMaxSum sm = new SubarrayWithMaxSum();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int maxSum = sm.maxSubArray(nums);
        System.out.println("Maximum subarray sum is: " + maxSum);
    }
}
