package greedy;

/**
 * <a href="https://leetcode.com/problems/jump-game/description/">Jump Game</a>
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 */
public class JumpGame {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private boolean optimal(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if(nums.length == 1){
            return true;
        }
        int goal = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            // Doing a reverse look up - we are checking that if from the current position I can reach the goal index
            // If so now our new goal is that index - At a certain point we will reach the last first index position
            // If we do not reach the end then its not possible to travel from end to end
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }

    // Time Complex: O(n) where n is the length of the input array, we are iterating through the array once
    // Space Complex: O(1) since we are using only a constant amount of space to keep track of the current maximum reachable index
    private boolean greedy(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if(nums.length == 1){
            return true;
        }
        int currentMaxReachableIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > currentMaxReachableIndex) {
                return false;
            }
            currentMaxReachableIndex = Math.max(currentMaxReachableIndex, i + nums[i]);
            if (currentMaxReachableIndex >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean canJump(int[] nums) {
        return greedy(nums);
    }

    public static void main(String[] args) {
        JumpGame jg = new JumpGame();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jg.canJump(nums));
    }
}
