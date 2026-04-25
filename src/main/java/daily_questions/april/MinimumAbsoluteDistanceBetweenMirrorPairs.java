package daily_questions.april;

import java.util.HashMap;

/**
 * Date: April 17, 2026,
 * <a href="https://leetcode.com/problems/minimum-absolute-distance-between-mirror-pairs">Minimum Absolute Distance Between Mirror Pairs</a>
 * You are given an integer array nums.
 * A mirror pair is a pair of indices (i, j) such that:
 * 0 <= i < j < nums.length, and
 * reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x.
 * Leading zeros are omitted after reversing, for example reverse(120) = 21.
 * Return the minimum absolute distance between the indices of any mirror pair.
 * The absolute distance between indices i and j is abs(i - j).
 * If no mirror pair exists, return -1.
 */
public class MinimumAbsoluteDistanceBetweenMirrorPairs {
    // Time Complexity: O(k) where k is the number of digits
    private int reverse(int num) {
        int reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }

    // Time Complexity: O(N^2 * k) where N is the length of the input array and k is the number of digits in the largest number
    // Space Complexity: O(1)
    private int bruteForce(int[] nums) {
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int reversed = reverse(nums[i]);
            for (int j = i + 1; j < nums.length; j++) {
                if (reversed == nums[j]) {
                    // We will break out coz anymore will increase the distance which we do not want
                    minDistance = Math.min(minDistance, Math.abs(i - j));
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    // Time Complexity: O(N) + O(N*k) where N is the size of nums array and k is the size of the laragest number
    // Space Complexity: O(N) for the hashmap
    // This fails when there are duplicates
    private int better(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        System.out.println("Map: " + map);
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int reversed = reverse(nums[i]);
            System.out.println("nums[i]: " + nums[i] + " reversed: " + reversed);
            if (map.containsKey(reversed) && map.get(reversed) > i && map.get(reversed) != i) {
                minDistance = Math.min(minDistance, Math.abs(i - map.get(reversed)));
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    // This is basically storing the reversed number as the key and the latest of its index in value
    // So basically at index 0 i say when i reversed this is get x
    // Index 1 when i reverse i get y
    // Now moving forward i check if the current number is equal to any of the previously reversed one if so update the distance
    // If there are duplicates the latest one is taken by over-writing the prev index
    // If there is 21 at index 0 and 2 then 12 would first be stored as {12:0} when 2nd index comes it gets updated to {12:2}
    // In this way the nearest index is taken and distance is minimized
    // Time Complexity: O(N*k) where N is the size of nums array and k is the size of the laragest number
    // Space Complexity: O(N)
    private int optimal(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                res = Math.min(res, Math.abs(map.get(nums[i]) - i));
            }
            map.put(reverse(nums[i]), i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int minMirrorPairDistance(int[] nums) {
        return optimal(nums);
    }

    public static void main(String[] args) {
        MinimumAbsoluteDistanceBetweenMirrorPairs solution = new MinimumAbsoluteDistanceBetweenMirrorPairs();
//        System.out.println(solution.minMirrorPairDistance(new int[]{12, 21, 45, 33, 54})); // 1
//        System.out.println(solution.minMirrorPairDistance(new int[]{120, 21})); // 1
//        System.out.println(solution.minMirrorPairDistance(new int[]{21, 120})); // -1
        System.out.println(solution.minMirrorPairDistance(new int[]{12, 2, 21, 4, 66, 78, 21})); // 2
    }
}
