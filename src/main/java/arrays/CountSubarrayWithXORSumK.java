package arrays;

import java.util.HashMap;

/*
 * https://www.geeksforgeeks.org/problems/count-subarray-with-given-xor/1
 * Given an array of integers arr[] and a number k, count the number of subarrays having XOR of their elements as k.
 */
public class CountSubarrayWithXORSumK {
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private long bruteForce(int[] arr, int k) {
        int n = arr.length;
        long count = 0;
        for (int i = 0; i < n; i++) {
            int xor = 0;
            for (int j = i; j < n; j++) {
                xor ^= arr[j];
                if (xor == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private long optimal(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int xor = 0;
        long count = 0;

        for (int num : arr) {
            xor ^= num;
            int rem = xor ^ k;
            if (map.containsKey(rem)) {
                count += map.get(rem);
            }
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }

        return count;
    }

    public long subarrayXor(int[] arr, int k) {
        // code here
        return optimal(arr, k);
    }

    public static void main(String[] args) {
        CountSubarrayWithXORSumK countSubarrayWithXORSumK = new CountSubarrayWithXORSumK();
        int[] arr = {4, 2, 2, 6, 4};
        int k = 6;
        System.out.println("Count of subarrays with XOR sum " + k + " is: " + countSubarrayWithXORSumK.subarrayXor(arr, k));
    }
}
