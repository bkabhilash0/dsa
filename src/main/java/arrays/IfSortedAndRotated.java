package arrays;

import java.util.Arrays;

/*
* Given an array nums, return true if the array was originally sorted in non-decreasing order,
* then rotated some number of positions (including zero). Otherwise, return false.
* There may be duplicates in the original array.
* Note: An array A rotated by x positions results in an array B of the same length such that B[i] == A[(i+x) % A.length] for every valid index i.
* */
public class IfSortedAndRotated {
    private void reverse(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    private int findNumberOfRotations(int[] nums){
        int index = nums.length - 1;
        int rotations = 0;
        while(index > 0){
            if(nums[index] < nums[index - 1]){
                return rotations + 1;
            }
            rotations++;
            index--;
        }
        return 0;
    }

    public boolean check(int[] nums) {
        int count = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i - 1] > nums[i]){
                count++;
            }
        }
        if(nums[nums.length - 1] > nums[0]){
            count++;
        }
        return count <= 1;

//        int numRotations = findNumberOfRotations(nums);
//        int[] original = Arrays.copyOf(nums, nums.length);
//        Arrays.sort(original);
//        System.out.println(Arrays.toString(original));
//        System.out.printf("Number of rotations: %d%n", numRotations);
//        for(int i = 0; i < numRotations; i++){
//            if(nums[i] != original[(i + numRotations) % nums.length]){
//                return false;
//            }
//        }
//        int[] original = Arrays.copyOf(nums, nums.length);
//        Arrays.sort(original);
//        boolean isRotated = Arrays.equals(nums, original);
//        if(isRotated){
//            return true;
//        }
//        for(int i = 1; i < nums.length; i++){
//            int[] temp = Arrays.copyOf(original, original.length);
//            reverse(temp, 0, i - 1);
//            reverse(temp, i, temp.length - 1);
//            reverse(temp, 0, temp.length - 1);
//            System.out.println(Arrays.toString(temp));
//            if(Arrays.equals(nums, temp)){
//                return true;
//            }
//        }

//        return true;
    }

    public static void main(String[] args) {
        IfSortedAndRotated isr = new IfSortedAndRotated();
        int[] nums = {2,7,4,1,2,6,2};
        boolean result = isr.check(nums);
        System.out.println("Is the array sorted and rotated? " + result);
    }
}
