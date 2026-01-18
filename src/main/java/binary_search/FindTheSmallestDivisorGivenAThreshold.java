package binary_search;

/**
 * <a href="https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/">...</a>
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all the array elements by it and sum the division results.
 * Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 * Each result of the division is rounded to the nearest integer greater than or equal to that element.
 * <p>
 * For example:
 * 7/3 = 3
 * 10/2 = 5
 */
public class FindTheSmallestDivisorGivenAThreshold {
    private int getMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private int getDivisionSum(int[] nums, int divisor, int threshold) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;
            if(sum > threshold){
                return sum;
            }
        }
        return sum;
    }

    // Brute Force Approach - Linear Search
    // Time Complexity: O(n * m) where n is the number of elements in nums and m is the maximum element in nums
    // Space Complexity: O(1)
    private int bruteForce(int[] nums, int threshold) {
        int maxDivisor = getMax(nums);
        for (int i = 1; i <= maxDivisor; i++) {
            if(getDivisionSum(nums, i, threshold) <= threshold){
                return i;
            }
        }
        return -1;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(n * log m) where n is the number of elements in nums and m is the maximum element in nums
    // Space Complexity: O(1)
    private int optimal(int[] nums, int threshold){
        int start = 1;
        int end = getMax(nums);
        while(start <= end){
            int mid = (start + end) / 2;
            if(getDivisionSum(nums, mid, threshold) <= threshold){
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        return optimal(nums, threshold);
    }

    public static void main(String[] args) {
        FindTheSmallestDivisorGivenAThreshold fsd = new FindTheSmallestDivisorGivenAThreshold();
        int[] nums = {1, 2, 5, 9};
        int threshold = 6;
        System.out.println("Nums: " + java.util.Arrays.toString(nums));
        System.out.println("\nThreshold: " + threshold);
        int result = fsd.smallestDivisor(nums, threshold);
        System.out.println("Result: " + result);
    }
}
