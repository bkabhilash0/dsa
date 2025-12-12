package arrays;

/*
* Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
* You must implement a solution with a linear runtime complexity and use only constant extra space.
* */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            nums[0] ^= nums[i];
        }
        return nums[0];
    }
    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();
        int[] nums = {4,1,2,1,2};
        int single = sn.singleNumber(nums);
        System.out.println("The single number is: " + single);
    }
}
