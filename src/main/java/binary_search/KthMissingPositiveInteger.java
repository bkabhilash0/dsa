package binary_search;

/**
 * <a href="https://leetcode.com/problems/kth-missing-positive-integer/description/">...</a>
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * Find the kth positive integer missing from this array.
 */
public class KthMissingPositiveInteger {
    // Brute Force Approach - Linear Search
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // Mathematical Approach: We can iterate through the array and for each number less than or equal to k, we increment k.
    private int bruteForce(int[] arr, int k) {
        for(int num: arr){
            if(num <= k){
                k++;
            } else {
                break;
            }
        }
        return k;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    // The idea is to use binary search to find the largest index 'right' such that the number of missing integers before arr[right] is less than or equal to k.
    private int optimal(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            int missing = arr[mid] - (mid + 1);
            if(missing < k){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return right + k + 1;
    }

    public int findKthPositive(int[] arr, int k) {
        return optimal(arr, k);
    }

    public static void main(String[] args) {
        KthMissingPositiveInteger kmpi = new KthMissingPositiveInteger();
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;
        System.out.println("arr: " + java.util.Arrays.toString(arr));
        System.out.println("\nk: " + k);
        int result = kmpi.findKthPositive(arr, k);
        System.out.println("Result: " + result);
    }
}
