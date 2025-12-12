package arrays;

/*
* Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
* Note that you must do this in-place without making a copy of the array.
* Example 1:

* Input: nums = [0,1,0,3,12]
* Step 1: [1,0,0,3,12]
* Step 2: [1,3,0,0,12]
* Step 3: [1,3,12,0,0]
* Output: [1,3,12,0,0]
*
* [1,2,0,0,0,5,6]
* [1,2,5,0,0,0,6]
* */
public class MoveZeros {
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int findFirstZero(int[] nums, int start){
        for(int i = start; i < nums.length; i++){
            if(nums[i] == 0){
                return i;
            }
        }
        return -1;
    }

    public void moveZeroesOptimised(int[] nums){
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[k++] = nums[i];
            }
        }

        while(k < nums.length){
            nums[k++] = 0;
        }
    }

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int zeroIndex = findFirstZero(nums, 0);
        if(zeroIndex == -1){
            return;
        }
        int left = zeroIndex;
        int right = zeroIndex + 1;
        while(left < n && right < n){
            if(nums[right] != 0){
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
    public static void main(String[] args) {
        MoveZeros move = new MoveZeros();
        int[] nums = {0};
        System.out.println(java.util.Arrays.toString(nums)); // Output: [0, 1, 0, 3, 12]
        move.moveZeroesOptimised(nums);
        System.out.println(java.util.Arrays.toString(nums)); // Output: [1, 3, 12, 0, 0]
    }
}
