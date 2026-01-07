package arrays;

/**
 * <a href="https://leetcode.com/problems/reverse-pairs/description/">...</a>
 * Reverse Pairs
 * Given an array of integers, count the number of reverse pairs.
 * A reverse pair is defined as a pair (i, j) such that i < j and arr[i] > 2 * arr[j].
 * Example:
 * Input: arr = [1, 3, 2, 3, 1]
 * Output: 2
 * Explanation: The reverse pairs are (3, 4) and (1, 4).
 */
public class ReversePairs {
    // Brute Force Approach: Check all pairs (i, j) where i < j
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private int bruteForce(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > 2 * nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid + 1;
        int index = 0;

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                temp[index++] = nums[left++];
            } else {
                temp[index++] = nums[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = nums[left++];
        }

        while (right <= high) {
            temp[index++] = nums[right++];
        }

        for (int i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
    }

    private int countReversePairs(int[] nums, int low, int mid, int high) {
        int count = 0;
        int i = low;
        int j = mid + 1;

        while (i <= mid && j <= high) {
            if ((long) nums[i] > (long) 2 * nums[j]) {
                count += (mid - i + 1);
                j++;
            } else {
                i++;
            }
        }
        return count;
    }

    private int optimalUsingMergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int count = 0;
        int mid = (low + high) / 2;
        count += optimalUsingMergeSort(nums, low, mid);
        count += optimalUsingMergeSort(nums, mid + 1, high);
        count += countReversePairs(nums, low, mid, high);
        merge(nums, low, mid, high);
        return count;
    }

    public int reversePairs(int[] nums) {
        return optimalUsingMergeSort(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        ReversePairs rp = new ReversePairs();
        int[] arr = {2, 4, 5, 3, 1};
        System.out.println("Input array: " + java.util.Arrays.toString(arr));
        int result = rp.reversePairs(arr);
        System.out.println("Sorted Array: " + java.util.Arrays.toString(arr));
        System.out.println("Number of reverse pairs: " + result); // Output: 2
    }
}
