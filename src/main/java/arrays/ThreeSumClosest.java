package arrays;

/**
 * <a href="https://leetcode.com/problems/3sum-closest">...</a>
 * 16. 3Sum Closest
 * Given an integer array nums of length n and an integer target, find three integers at distinct indices in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 */
public class ThreeSumClosest {
    private int optimal(int[] nums, int target){
        java.util.Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        int difference = Math.abs(target - closestSum);
        for(int i = 0; i < nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                int currentDifference = Math.abs(target - sum);
                if(currentDifference < difference){
                    closestSum = sum;
                    difference = currentDifference;
                }
                if(sum <= target){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return closestSum;
    }

    public int threeSumClosest(int[] nums, int target) {
        return optimal(nums, target);
    }
    public static void main(String[] args) {
        ThreeSumClosest tsc = new ThreeSumClosest();
        int[] nums = {0,0,0};
        int target = 1;
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("\nTarget: " + target);
        int closestSum = tsc.threeSumClosest(nums, target);
        System.out.println("Closest Sum: " + closestSum);
    }
}
