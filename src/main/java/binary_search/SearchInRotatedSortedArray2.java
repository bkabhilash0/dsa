package binary_search;

/**
 * <a href="https://leetcode.com/problems/search-in-rotated-sorted-array-ii">...</a>
 * 81. Search in Rotated Sorted Array II
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 * You must decrease the overall operation steps as much as possible.
 */
public class SearchInRotatedSortedArray2 {
    // Brute Force Approach - Do a linear search
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private boolean bruteForce(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    // Optimal Approach - Modified Binary Search
    // Time Complexity: O(log n) in average case, O(n) in worst case
    // Space Complexity: O(1)
    private boolean optimal(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
//            System.out.println("Start: " + start + ", End: " + end + ", Mid: " + mid);

            // Check if the mid element is the target
            if (nums[mid] == target) {
                return true;
            }

            // Handle duplicates: If start, mid, and end are equal, we cannot determine the sorted side
            if(nums[start] == nums[mid] && nums[mid] == nums[end]){
                start++;
                end--;
                continue;
            }

            // Check which side is sorted
            // Left side is sorted
            if (nums[mid] > nums[end]) {
                // Check if the target is in the left side range
                if (nums[start] <= target && nums[mid] >= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] <= target && nums[end] >= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        return optimal(nums, target);
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray2 srs = new SearchInRotatedSortedArray2();
        int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        int target = 2;
        System.out.print("Target: " + target);
        boolean isPresent = srs.search(nums, target);
        System.out.println("\tIs Target Present: " + isPresent);
//        for (int num : nums) {
//            System.out.print("Target: " + num);
//            boolean isPresent = srs.search(nums, num);
//            System.out.println("\tIs Target Present: " + isPresent);
//        }
    }
}
