package daily_questions.april;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: April 10 & 11, 2026,
 * <a href="https://leetcode.com/problems/minimum-distance-between-three-equal-elements-i/">Minimum Distance Between Three Equal Elements I</a>
 * You are given an integer array nums.
 * A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
 * The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
 * Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.
 */
public class MinDistanceBetweenThreeEqElementsI {
    // Time Complexity: O(N) + O(N * k)
    // Space Complexity: O(N)
    private int intuition(int[] nums) {
        if (nums.length < 3) {
            return -1;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new java.util.ArrayList<>()).add(i);
        }
        int i, j, k;
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            if (list.size() < 3) {
                continue;
            }
            i = 0;
            j = 1;
            k = 2;
            while (k < list.size()) {
                ans = Math.min(ans, Math.abs(list.get(i) - list.get(j)) + Math.abs(list.get(j) - list.get(k)) + Math.abs(list.get(k) - list.get(i)));
                i++;
                j++;
                k++;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // Time Complexity: O(n), where n is the length of the input array nums.
    // Space Complexity: O(n), in the worst case, if all elements in nums are same
    private int better(int[] nums) {
        if (nums.length < 3) return -1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                List<Integer> list = map.get(nums[i]);
                list.add(i);
                map.put(nums[i], list);
                if (list.size() > 3) {
                    list.removeFirst();
                }
                if (list.size() == 3) {
                    ans = Math.min(ans, Math.abs(list.get(0) - list.get(1)) + Math.abs(list.get(1) - list.get(2)) + Math.abs(list.get(2) - list.get(0)));
                }
            } else {
                List<Integer> list = new java.util.ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // When i < j < k the |i-j| + |j-k| + |k-i| =  (j - i) + (k - j) + (k - i) = 2 * (k - i)
    // Time Complexity: O(n), where n is the length of the input array nums.
    // Space Complexity: O(n), in the worst case, if all elements in nums are same
    private int optimal(int[] nums) {
        int n = nums.length;
        if (n < 3) return -1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new java.util.ArrayList<>()).add(i);
        }

        int ans = Integer.MAX_VALUE;
        for (List<Integer> list : map.values()) {
            if (list.size() < 3) continue;
            for (int i = 0; i < list.size() - 2; i++) {
                int distance = 2 * (list.get(i + 2) - list.get(i));
                ans = Math.min(ans, distance);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int minimumDistance(int[] nums) {
        return optimal(nums);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1, 3};
        MinDistanceBetweenThreeEqElementsI minDistanceBetweenThreeEqElementsI = new MinDistanceBetweenThreeEqElementsI();
        System.out.println(minDistanceBetweenThreeEqElementsI.minimumDistance(nums)); // Output: 6
    }
}
