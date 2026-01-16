package binary_search;

/**
 * <a href="https://leetcode.com/problems/search-insert-position">...</a>
 * 35. Search Insert Position
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class SearchInsertPosition {
    // Brute Force Approach - Linear Search
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int bruteForce(int[] nums, int target){
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= target){
                return i;
            }
        }
        return nums.length;
    }

    // Optimal Approach - Binary Search - Find Lower Bound
    // Lower Bound - the min index which has the element greater than or equal to target
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private int optimal(int[] nums, int target){
        int start = 0, end = nums.length - 1;
        int index = nums.length;
        while (start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] >= target){
                index = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return index;
        // By careful observation, at the end of the loop, start will be pointing to the index where the target can be inserted
//        return start;
    }

    public int searchInsert(int[] nums, int target) {
        return optimal(nums, target);
    }
    public static void main(String[] args) {
        SearchInsertPosition sip = new SearchInsertPosition();
        int[] nums = {1, 3, 5, 6};
        int target = 7;
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("\nTarget: " + target);
        int index = sip.searchInsert(nums, target);
        System.out.println("Index: " + index);
    }
}
