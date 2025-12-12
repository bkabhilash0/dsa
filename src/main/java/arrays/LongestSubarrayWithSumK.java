package arrays;

public class LongestSubarrayWithSumK {
    // This approach works only for non-negative integers
    private int optimalWithTwoPointers(int[] nums, int k) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < nums.length) {
            sum += nums[right];
            while (sum > k && left <= right) {
                sum -= nums[left];
                left++;
            }
            if(sum == k){
                int currentLength = right - left + 1;
                maxLength = Math.max(maxLength, currentLength);
            }
            right++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubarrayWithSumK ls = new LongestSubarrayWithSumK();
        int[] nums = {1, 2, 3, 1, 1, 1, 1, 4, 2, 3};
        int k = 6;
        int length = ls.optimalWithTwoPointers(nums, k);
        System.out.println("Length of longest subarray with sum " + k + " is: " + length);
    }
}
