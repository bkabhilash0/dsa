package arrays;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/description/">...</a>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 */
public class MedianOfTwoSortedArrays {
    // Time Complexity: O(m + n)
    // Space Complexity: O(m + n)
    private double mergeSortMethod(int[] nums1, int[] nums2) {
        int left = 0, right = 0;
        int[] temp = new int[nums1.length + nums2.length];
        int index = 0;
        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] < nums2[right]) {
                temp[index++] = nums1[left++];
            } else {
                temp[index++] = nums2[right++];
            }
        }

        while (left < nums1.length) {
            temp[index++] = nums1[left++];
        }

        while (right < nums2.length) {
            temp[index++] = nums2[right++];
        }

        if (temp.length % 2 == 0) {
            int number = temp.length / 2;
            int number2 = temp.length / 2 - 1;
            return (temp[number] + temp[number2]) / 2.0;
        }
        return temp[temp.length / 2];
    }

    // Time Complexity: O(log(min(m, n)))
    // Space Complexity: O(1)
    private double binarySearchMethod(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

        // Ensure nums1 is the smaller array for simplicity
        if (m > n)
            return findMedianSortedArrays(nums2, nums1);

        int low = 0, high = m;
        int leftCount = (m + n + 1) / 2;
        while(low <=  high){
            int i = (low + high) / 2;
            int j = leftCount - i;

            int leftMaxA = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int rightMinA = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int leftMaxB = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int rightMinB = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if(leftMaxA <= rightMinB && leftMaxB <= rightMinA){
                // Now Check for even or odd
                if((m + n) % 2 == 0){
                    return (Math.max(leftMaxA, leftMaxB) + Math.min(rightMinA, rightMinB)) / 2.0;
                }else{
                    return Math.max(leftMaxA, leftMaxB);
                }
            }else if(leftMaxA > rightMinB){
                high = i - 1;
            }else{
                low = i + 1;
            }
        }
        return 0; // If the code reaches here, the input arrays were not sorted.
    }

    // Time Complexity: O(m + n)
    // Space Complexity: O(m + n) if we merge, O(1) if we use binary search
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return binarySearchMethod(nums1, nums2);
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays mtos = new MedianOfTwoSortedArrays();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double median = mtos.findMedianSortedArrays(nums1, nums2);
        System.out.println("Median of the two sorted arrays: " + median);
    }
}
