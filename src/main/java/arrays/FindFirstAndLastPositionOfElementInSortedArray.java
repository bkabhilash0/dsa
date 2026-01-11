package arrays;

/**
 * <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array">...</a>
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If the target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int[] bruteForce(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (result[0] == -1) {
                    result[0] = i;
                } else {
                    result[1] = i;
                }
            }
        }

        return result;
    }

    // Lower bound - the min index which has the element greater than or equal to target
    // This is usually the first occurrence of the target if it is present in the array
    // We modify the lower bound logic to only search for the target element rather than greater than or equal to
    // We move the end pointer to mid - 1 when we find the target to search for any earlier occurrence
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private int getLowerBound(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int index = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                index = mid;
                end = mid - 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }

    // Upper bound - the min index which has the element greater than target,
    // But here we do not need greater than an element, but we need the last occurrence of the target
    // By tweaking the upper-bound logic we can get the last occurrence of the target by moving the start pointer to the right partition when we find the target
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private int getUpperBound(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int index = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                System.out.println("nums[mid]: " + nums[mid] + " == target: " + target);
                index = mid;
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }

    private int[] optimal(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        result[0] = getLowerBound(nums, target);
        result[1] = getUpperBound(nums, target);
        return result;
    }

    public int[] searchRange(int[] nums, int target) {
        return optimal(nums, target);
    }

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray finder = new FindFirstAndLastPositionOfElementInSortedArray();
        int[] nums = {5, 7, 7, 8, 8, 8, 10};
        int target = 8;
        int[] result = finder.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + " in the array: [" + result[0] + ", " + result[1] + "]");
    }
}
