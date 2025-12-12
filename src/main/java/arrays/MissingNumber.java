package arrays;

/**
 * Missing Number in an array
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 */
public class MissingNumber {
    private int solveUsingMap(int[] nums){
        int[] map = new int[nums.length + 1];
        for(int num : nums) {
            map[num] = 1;
        }
        int missingNumber = -1;
        for(int i = 0; i < map.length; i++){
            if(map[i] == 0){
                missingNumber = i;
                break;
            }
        }
        return missingNumber;
    }

    // Do not need to explicitly check for 0 since the array contains n numbers for 0-9 if it doesn't contain 0 then the sum will be n*(n+1)/2 - sum of an array
    private int solveUsingAddition(int[] nums){
        int requiredSum = (nums.length * (nums.length + 1)) / 2;
        for(int num : nums) {
            requiredSum -= num;
        }
        return requiredSum;
    }

    private int solveUsingXOR(int[] nums){
        int xor = 0;
        for(int i = 0; i < nums.length; i++){
            xor ^= nums[i];
            xor ^= i;
        }
        xor ^= nums.length;
        return xor;
    }

    public int missingNumber(int[] nums) {
        return solveUsingXOR(nums);
    }

    public static void main(String[] args) {
        MissingNumber mn = new MissingNumber();
        int[] nums = {3,0,1};
        int missing = mn.missingNumber(nums);
        System.out.println("Missing number is: " + missing);
    }
}
