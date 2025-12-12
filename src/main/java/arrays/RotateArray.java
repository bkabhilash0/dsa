package arrays;

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 */
public class RotateArray {
    private void reverse(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int rotations = k % n;
        if(rotations == 0){
            return;
        }
        reverse(nums, 0, n - 1);
        reverse(nums, 0, rotations - 1);
        reverse(nums, rotations, n - 1);
    }

    public static void main(String[] args) {
        RotateArray ra = new RotateArray();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.println(java.util.Arrays.toString(nums)); // Output: [1, 2, 3, 4, 5, 6, 7]
        ra.rotate(nums, k);
        System.out.println(java.util.Arrays.toString(nums)); // Output: [5, 6, 7, 1, 2, 3, 4]

    }
}
