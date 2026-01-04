package arrays;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/majority-element-ii">...</a>
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 */
public class MajorityElement2 {
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private void bruteForce(int[] nums, List<Integer> result) {
        int n = nums.length;
        int threshold = (n / 3) + 1;
        // Implementation goes here
        for (int j : nums) {
            if (result.contains(j)) {
                continue;
            }
            int count = 0;
            for (int num : nums) {
                if (j == num) {
                    count++;
                }
            }
            if (count > threshold) {
                result.add(j);
            }
            if (result.size() == 2) {
                break;
            }
        }
    }

    // Reduce one loop using a hashmap to store the counts
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private void better(int[] nums, List<Integer> result) {
        int n = nums.length;
        int threshold = (n / 3) + 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        // Implementation goes here
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            // Adding equals check instead of greater than will avoid duplicates in result
            if (map.get(num) == threshold) {
                result.add(num);
            }
            if (result.size() == 2) {
                break;
            }
        }
    }

    // Modified version of Moore's Voting Algorithm
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private void optimal(int[] nums, List<Integer> result) {
        int n = nums.length;
        int element1 = Integer.MIN_VALUE;
        int element2 = Integer.MIN_VALUE;
        int count1 = 0;
        int count2 = 0;
        // Implementation goes here
        for (int num : nums) {
            if (count1 == 0 && num != element2) {
                count1 = 1;
                element1 = num;
            } else if (count2 == 0 && num != element1) {
                count2 = 1;
                element2 = num;
            } else if (num == element1) {
                count1++;
            } else if (num == element2) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        // Now Manually check if element1 and element2 are actually majority elements
        count1 = 0;
        count2 = 0;
        int threshold = (n / 3) + 1;
        for (int num : nums) {
            if (num == element1) {
                count1++;
                if (count1 == threshold) {
                    result.add(element1);
                }
            } else if (num == element2) {
                count2++;
                if (count2 == threshold) {
                    result.add(element2);
                }
            }
            if (result.size() == 2) {
                break;
            }
        }
    }

    public List<Integer> majorityElement(int[] nums) {
        return new AbstractList<Integer>() {
            List<Integer> result;
            private void init(){
                result = new ArrayList<>();
                optimal(nums, result);
            }

            @Override
            public Integer get(int index) {
                return result.get(index);
            }

            @Override
            public int size() {
                if(result == null){
                    init();
                }
                return result.size();
            }
        };
    }

    public static void main(String[] args) {
        MajorityElement2 m = new MajorityElement2();
        int[] nums = {3,2,3};
        List<Integer> majorityElements = m.majorityElement(nums);
        System.out.println("The majority elements are: " + majorityElements);
    }
}
