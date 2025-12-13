package arrays;

/*
 * https://leetcode.com/problems/rearrange-array-elements-by-sign
 * "You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
 * You should return the array of nums such that the array follows the given conditions:
 * Every consecutive pair of integers have opposite signs.
 * For all integers with the same sign, the order in which they were present in nums is preserved.
 * The rearranged array begins with a positive integer.
 * Return the modified array after rearranging the elements to satisfy the aforementioned conditions."
 */
public class RearrangeElementBySign {
    // Time Complexity: O(2n)
    // Space Complexity: O(n)
    private void bruteForce(int[] nums){
        int[] pos = new int[nums.length / 2];
        int[] neg = new int[nums.length / 2];
        int pIndex = 0;
        int nIndex = 0;
        for(int num : nums){
            if(num > 0){
                pos[pIndex++] = num;
            }else{
                neg[nIndex++] = num;
            }
        }
        for(int i = 0; i < nums.length / 2; i++){
            nums[2 * i] = pos[i];
            nums[2 * i + 1] = neg[i];
        }
    }

    private int[] better(int[] nums){
        int pIndex = 0;
        int nIndex = 1;
        int[] result = new int[nums.length];
        for (int num : nums) {
            if (num > 0) {
                result[pIndex] = num;
                pIndex += 2;
            } else {
                result[nIndex] = num;
                nIndex += 2;
            }
        }
        return result;
    }

    // Not Working, Need to fix => Not maintaining order
    private int[] optimal(int[] nums){
        // Since the problem guarantees equal number of positive and negative integers
        // We can rearrange in place
        int pIndex = 0;
        int nIndex = 1;
        int n = nums.length;
        while (pIndex < n && nIndex < n) {
            if (nums[pIndex] > 0) {
                pIndex += 2;
            } else if (nums[nIndex] < 0) {
                nIndex += 2;
            } else {
                // Swap
                int temp = nums[pIndex];
                nums[pIndex] = nums[nIndex];
                nums[nIndex] = temp;
                pIndex += 2;
                nIndex += 2;
            }
        }
        return nums;
    }

    public int[] rearrangeArray(int[] nums) {
        return better(nums);
    }
    public static void main(String[] args) {
        RearrangeElementBySign rea = new RearrangeElementBySign();
        int[] nums = {28,-41,22,-8,-37,46,35,-9,18,-6,19,-26,-37,-10,-9,15,14,31};
        int[] rearranged = rea.rearrangeArray(nums);
        System.out.println("Rearranged array: " + java.util.Arrays.toString(rearranged));
    }
}
