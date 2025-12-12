package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*
* Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
* The relative order of the elements should be kept the same.
* Consider the number of unique elements in nums to be k.
* After removing duplicates, return the number of unique elements k.
* The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index k - 1 can be ignored.
* */
public class RemoveDuplicates {
    private int removeUsingSetMethod(int[] nums){
        Set<Integer> set = new TreeSet<>();
        for(int num : nums){
            set.add(num);
        }
        for(int i = 0; i < set.size(); i++){
            nums[i] = (Integer) set.toArray()[i];
        }
        return set.size();
    }

    private int removeDuplicatesOptimised2(int[] nums){
        int index = 0;

        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[index]){
                index++;
                nums[index] = nums[i];
            }
        }

        return index + 1;
    }

    private int removeDuplicatesOptimised(int[] nums){
        if(nums.length == 0) return 0;
        int index = 0;
        int unique = 1;
        int temp = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(nums[i] != temp){
                temp = nums[i];
                unique++;
                index++;
                nums[index] = nums[i];
            }
        }

        return unique;
    }

    public int removeDuplicates(int[] nums) {
        return removeDuplicatesOptimised2(nums);
    }

    public static void main(String[] args) {
        RemoveDuplicates rd = new RemoveDuplicates();
        int[] nums = {-3,-1,0,0,0,3,3};
        int k = rd.removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println("Number of unique elements: " + k);
    }
}
