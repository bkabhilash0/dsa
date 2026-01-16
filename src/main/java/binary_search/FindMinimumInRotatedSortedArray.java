package binary_search;

/**
 * <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array">...</a>
 * 153. Find Minimum in Rotated Sorted Array
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 * - [4,5,6,7,0,1,2] if it was rotated 4 times.
 * - [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 */
public class FindMinimumInRotatedSortedArray {
    // Brute Force Approach - Linear Search
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int bruteForce(int[] nums){
        int min = Integer.MAX_VALUE;
        for(int num: nums){
            min = Math.min(min, num);
        }
        return min;
    }

    // Optimal Approach - Modified Binary Search
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private int optimal(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        int min = Integer.MAX_VALUE;
        // We can use left <= right as the loop condition but on doing so the start pointer won't always point to the minimum element
        while(left < right){
            int mid = (left + right) / 2;

            // Find the sorted side
            if(nums[mid] >= nums[right]){
                min = Math.min(min, nums[left]);
                left = mid + 1;
            }else{
                min = Math.min(min, nums[mid]);
                // Move the right pointer to mid because mid could be the minimum element
                right = mid;
//                right = mid - 1;
            }
        }
        // If we observe, the left pointer will eventually point to the minimum element
        // But for that to be true, we need to ensure that the loop condition is left < right
        return nums[left];
//        return min;
    }

    public int findMin(int[] nums) {
        return optimal(nums);
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray finder = new FindMinimumInRotatedSortedArray();
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        int minElement = finder.findMin(nums);
        System.out.println("Minimum Element: " + minElement);
    }
}
