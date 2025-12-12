package arrays;

import java.util.Arrays;

/**
 * Union and Intersection of two arrays
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. Nums2 has a length of n.
 */
public class Union {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = 0;
        int right = 0;
        int index = 0;
        int [] merged = new int[m + n];
        while(left < m && right < n){
            if(nums1[left] <= nums2[right]){
                merged[index++] = nums1[left++];
            } else {
                merged[index++] = nums2[right++];
            }
        }

        while(left < m){
            merged[index++] = nums1[left++];
        }

        while(right < n){
            merged[index++] = nums2[right++];
        }
        System.out.println(Arrays.toString(merged));
        for (int i = 0; i < m + n; i++) {
            nums1[i] = merged[i];
        }
    }

    // Doing a reverse two pointer approach to optimize space complexity to O(1)
    // Similar to merging two sorted linked lists but starting from the end
    // Since the end has zeros we can fill from the back
    private void optimalMerge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        while(p2 >= 0){
            if(p1 >=0 && nums1[p1] > nums2[p2]){
                nums1[p--] = nums1[p1--];
            }else{
                nums1[p--] = nums2[p2--];
            }
        }
    }

    public static void main(String[] args) {
        Union uai = new Union();
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        uai.optimalMerge(nums1, m, nums2, n);
        System.out.println(java.util.Arrays.toString(nums1)); // Output: [1,2,2,3,5,6]
    }
}
