package arrays;

import java.util.HashSet;

/**
 * Intersection of two arrays
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and you may return the result in any order.
 */
public class Intersection {
    private int[] intersectionUsingMap(int[] nums1, int[] nums2) {
        int[] seen = new int[1001];
        int resultSize = nums1.length;
        if(nums2.length < nums1.length) {
            resultSize = nums2.length;
        }
        int index = 0;
        int[] intersection = new int[resultSize];
        for (int num : nums1) {
            seen[num] = 1;
        }

        for(int num : nums2) {
            if (seen[num] == 1) {
                seen[num] = 0; // To ensure uniqueness
                intersection[index++] = num;
            }
        }

        return intersection.length == index ? intersection : java.util.Arrays.copyOf(intersection, index);
    }

    private int[] intersectionUsingSet(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        HashSet<Integer> intersection = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                intersection.add(num);
            }
        }
        int[] result = new int[intersection.size()];
        int index = 0;
        for (int num : intersection) {
            result[index++] = num;
        }
        return result;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        return intersectionUsingMap(nums1, nums2);
    }

    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] result = intersection.intersection(nums1, nums2);
        System.out.println(java.util.Arrays.toString(result)); // Output: [2]
    }
}
