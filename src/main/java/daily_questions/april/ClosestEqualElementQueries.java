package daily_questions.april;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Date: April 16, 2026,
 * <a href="https://leetcode.com/problems/closest-equal-element-queries">Closest Equal Element Queries</a>
 * You are given a circular array nums and an array queries.
 * For each query i, you have to find the following:
 * The minimum distance between the element at index queries[i] and
 * any other index j in the circular array, where nums[j] == nums[queries[i]].
 * If no such index exists, the answer for that query should be -1.
 * Return an array answer of the same size as queries, where answer[i] represents the result for query i.
 */
public class ClosestEqualElementQueries {
    // Time Complexity: O(N) + O(q * N) where N is the size of nums and q is the size of queries
    // Space Complexity: O(N) for the hashmap
    private List<Integer> intuition(int[] nums, int[] queries) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        // Create a hashmap with the keys as the element and values are list of indices it is present
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new java.util.ArrayList<>()).add(i);
        }
        // Sort all the List inside the map - but ideally the list would be sorted as we are looping in order from left to right

        List<Integer> result = new java.util.ArrayList<>();
        // Iterate through each query
        for (int query : queries) {
            // Get the actual number from the index the query is referring to
            int queryElement = nums[query];
            // If the nums array has only one instance of that element then its -1
            if (map.get(queryElement).size() == 1) {
                result.add(-1);
                continue;
            }
            // Set the start or reference index as the query index as we need to find the distance from this index both clockwise and ACW
            int startIndex = query;
            // Get all the indices where this number is present
            List<Integer> indices = map.get(queryElement);
            int minDistance = getMinDistance(indices, startIndex, n);
            result.add(minDistance);
        }
        return result;
    }

    // This will run for O(N) Overall coz the overall indices will be the size of the num
    private static int getMinDistance(List<Integer> indices, int startIndex, int n) {
        int minDistance = Integer.MAX_VALUE;
        for (Integer index : indices) {
            // If the index is same as startIndex or reference index skip it
            if (index == startIndex) continue;
            // Get the clock wise distance - Left -> Right
            int clockWiseDistance = Math.abs(index - startIndex);
            // Get the anti clock wise distance - Right -> Left
            int counterClockWiseDistance = n - clockWiseDistance;
            // Find the min of both
            int min = Math.min(clockWiseDistance, counterClockWiseDistance);
            // Update the min Distance
            minDistance = Math.min(minDistance, min);
            // For all the indices where the element is present we need to find the CW and ACW distance before updating the result
        }
        return minDistance;
    }

    // Time Complexity: O(N) + O(q log k) where N is the size of nums, q is the size of queries and k is the max number of indices for any element in nums
    // Space Complexity: O(N) for the hashmap
    private List<Integer> optimal(int[] nums, int[] queries) {
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new java.util.ArrayList<>()).add(i);
        }
        List<Integer> result = new java.util.ArrayList<>();
        // Iterate through each query
        for (int query : queries) {
            // Get the actual number from the index the query is referring to
            int queryElement = nums[query];
            // If the nums array has only one instance of that element then its -1
            if (map.get(queryElement).size() == 1) {
                result.add(-1);
                continue;
            }
            // Set the start or reference index as the query index as we need to find the distance from this index both clockwise and ACW
            int startIndex = query;
            // Get all the indices where this number is present
            List<Integer> indices = map.get(queryElement);
            int minDistance = getMinDistanceOptimal(indices, startIndex, n);
            result.add(minDistance);
        }
        return result;
    }

    // This will run for O(log k)
    private static int getMinDistanceOptimal(List<Integer> indices, int startIndex, int n) {
        // Binary search to find the query Index or start Index
        int positionOfStartIndex = Collections.binarySearch(indices, startIndex);
        int res = Integer.MAX_VALUE;
        // left neighbor of the start Index - the nearest from the start Index
        int left = indices.get((positionOfStartIndex - 1 + indices.size()) % indices.size());
        // get the distance, the clock wise distance
        int d1 = Math.abs(startIndex - left);
        // Get the min of res and clockwise and anticlockwise min
        res = Math.min(res, Math.min(d1, n - d1));

        // right neighbor
        int right = indices.get((positionOfStartIndex + 1) % indices.size());
        int d2 = Math.abs(startIndex - right);
        return Math.min(res, Math.min(d2, n - d2));
    }

    public List<Integer> solveQueries(int[] nums, int[] queries) {
        return optimal(nums, queries);
    }

    public static void main(String[] args) {
        ClosestEqualElementQueries solution = new ClosestEqualElementQueries();
        System.out.println(solution.solveQueries(new int[]{1, 3, 1, 4, 1, 3, 2}, new int[]{0, 3, 5})); // [2, -1, 3]
        System.out.println(solution.solveQueries(new int[]{1, 2, 3, 4}, new int[]{0, 1, 2, 3})); // [-1,-1,-1,-1]
    }
}
