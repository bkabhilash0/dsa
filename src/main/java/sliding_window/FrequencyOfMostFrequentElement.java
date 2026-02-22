package sliding_window;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/">Frequency of the Most Frequent Element</a>
 * You are given an array of integers nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
 * Return the maximum possible frequency of an element after performing at most k operations.
 */
public class FrequencyOfMostFrequentElement {


    private int bruteForce(int[] nums, int k) {
        return 0;
    }

    // Time Complex: O(n log n) where n is the length of the nums array, we are sorting the array and then doing a single pass through the array to find the maximum frequency
    // Space Complex: O(1) since we are sorting the array in place
    private int optimal(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int maxFreq = 0;
        // Use long to prevent overflows
        long sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            // We need k + sum to be equal to the right side. So if it gets less than the right side,
            // it means we need to remove some elements from the left side to make it equal to the right side.
            if (k + sum < (long) nums[right] * (right - left + 1)) {
                sum -= nums[left];
                left++;
            }
//            maxFreq = Math.max(maxFreq, right - left + 1);
            // Using Math.max is bit slower then if
            if(right - left + 1 > maxFreq){
                maxFreq = right - left + 1;
            }
        }
        return maxFreq;
    }

    public int maxFrequency(int[] nums, int k) {
        return optimal(nums, k);
    }

    public static void main(String[] args) {
        FrequencyOfMostFrequentElement frequencyOfMostFrequentElement = new FrequencyOfMostFrequentElement();
        int[] nums = {1, 2, 4};
        int k = 5;
        System.out.println(frequencyOfMostFrequentElement.maxFrequency(nums, k)); // Output: 3
    }
}
