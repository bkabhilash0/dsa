package arrays;

/*
* Given a binary array nums, return the maximum number of consecutive 1's in the array.
* */
public class MaxConsecutiveOnes {
    private int findMaxConsecutiveBasicApproach(int[] nums){
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                if (count > max) {
                    max = count;
                }
                count = 0;
            }
        }
        return Math.max(max, count);
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int right = 0;
        int maxConsecutiveOnes = 0;
        while (right < nums.length) {
            if (nums[right] == 1) {
                int currentCount = right - left + 1;
                maxConsecutiveOnes = Math.max(maxConsecutiveOnes, currentCount);
                right++;
            } else {
                right++;
                left = right;
            }
        }
        return maxConsecutiveOnes;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes mc = new MaxConsecutiveOnes();
        int[] nums = {1,1,0,1,1,1};
        int maxConsecutiveOnes = mc.findMaxConsecutiveOnes(nums);
        System.out.println("Maximum consecutive ones: " + maxConsecutiveOnes);

    }
}
