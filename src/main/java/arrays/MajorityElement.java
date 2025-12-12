package arrays;

/*
 * https://leetcode.com/problems/majority-element/
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * */
public class MajorityElement {
    public int majorityElementBruteForce(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int count = 0;
            for (int i : nums) {
                if (num == i) {
                    count++;
                }
            }
            if (count > n / 2) {
                return num;
            }
        }
        return -1;
    }

    public int majorityElementHashMap(int[] nums) {
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > n / 2) {
                return num;
            }
        }
        return -1;
    }

    // Moore's Voting Algorithm
    public int majorityElement(int[] nums) {
        int element = Integer.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                element = num;
            }
            if (num == element) {
                count++;
            } else {
                count--;
            }
        }
        return element;
    }

    public static void main(String[] args) {
        MajorityElement me = new MajorityElement();
        int[] nums = {2,2,1,1,1,2,2};
        int majority = me.majorityElement(nums);
        System.out.println("The majority element is: " + majority);
    }
}
