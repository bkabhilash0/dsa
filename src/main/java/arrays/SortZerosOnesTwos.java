package arrays;

/*
* https://leetcode.com/problems/sort-colors/submissions/
* Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
* with the colors in the order red, white, and blue.
* We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
* You must solve this problem without using the library's sort function.
* */
public class SortZerosOnesTwos {
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortArrayBetter(int[] nums) {
        int n = nums.length;
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                count0++;
            }else if(nums[i] == 1){
                count1++;
            }else{
                count2++;
            }
        }
        int index = 0;
        for(int i = 0; i < count0; i++){
            nums[index++] = 0;
        }
        for(int i = 0; i < count1; i++){
            nums[index++] = 1;
        }
        for(int i = 0; i < count2; i++){
            nums[index++] = 2;
        }
    }

    // Dutch National Flag Algorithm
    public void sortArrayOptimal(int[] nums) {
        int n = nums.length;
        int low = 0;
        int mid = 0;
        int high = n - 1;
        while(mid <= high){
            if(nums[mid] == 0){
                swap(nums, low++, mid++);
            }else if(nums[mid] == 2){
                swap(nums, mid, high--);
            }else{
                mid++;
            }
        }
    }

    public void sortColors(int[] nums) {
        sortArrayOptimal(nums);
    }
    public static void main(String[] args) {
        SortZerosOnesTwos szot = new SortZerosOnesTwos();
        int[] nums = {2,0,1};
        System.out.println(java.util.Arrays.toString(nums)); // Output: [2,0,2,1,1,0]
        szot.sortColors(nums);
        System.out.println(java.util.Arrays.toString(nums)); // Output: [0,0,1,1,2,2]
    }
}
