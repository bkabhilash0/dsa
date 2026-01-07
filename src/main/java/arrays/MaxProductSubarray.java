package arrays;

/**
 * <a href="https://leetcode.com/problems/maximum-product-subarray/description/">...</a>
 * Given an integer array nums, find a subarray that has the largest product, and return the product.
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * Note that the product of an array with a single element is the value of that element.
 */
public class MaxProductSubarray {
    // Brute Force Approach
    // Explanation: We consider every possible subarray and calculate its product.
    // We keep track of the maximum product found so far.
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private int bruteForce(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                maxProduct = Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }

    // Optimal Prefix and Suffix Product Method
    // Explanation: We calculate the product of elements from the start (prefix) and from the end (suffix) of the array.
    // If at any point the product becomes zero, we reset it to one.
    // This way, we consider all possible subarrays that could yield the maximum product.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int optimalPrefixSuffixMethod(int[] nums) {
        int prefix = 1;
        int suffix = 1;
        int maxProduct = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;
            prefix *= nums[i];
            suffix *= nums[n - i - 1];

            maxProduct = Math.max(maxProduct, Math.max(prefix, suffix));
        }
        return maxProduct;
    }

    // Optimal Kadane's Algorithm Variation
    // Explanation: We maintain two variables, maxSoFar and minSoFar.
    // maxSoFar keeps track of the maximum product subarray ending at the current position,
    // while minSoFar keeps track of the minimum product subarray ending at the current position.
    // This is important because a negative number can turn a minimum product into a maximum product when multiplied.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int optimalKadane(int[] nums) {
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                // Swap maxSoFar and minSoFar
                int temp = maxSoFar;
                maxSoFar = minSoFar;
                minSoFar = temp;
            }

            maxSoFar = Math.max(nums[i], maxSoFar * nums[i]);
            minSoFar = Math.min(nums[i], minSoFar * nums[i]);

            result = Math.max(result, maxSoFar);
        }
        return result;
    }

    public int maxProduct(int[] nums) {
        return optimalPrefixSuffixMethod(nums);
    }

    public static void main(String[] args) {
        MaxProductSubarray instance = new MaxProductSubarray();
        int[] nums = {2, 3, -2, 4};
        int result = instance.maxProduct(nums);
        System.out.println("Maximum Product Subarray: " + result); // Expected Output: 6
    }
}
