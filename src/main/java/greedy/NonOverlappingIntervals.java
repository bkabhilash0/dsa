package greedy;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/non-overlapping-intervals/1">Non-overlapping Intervals</a>
 * Given an array of intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
 */
public class NonOverlappingIntervals {
    // Time Complexity: O(n log n) + O(n) where n is the number of intervals, we are sorting the intervals based on their end time
    // Space Complexity: O(1) since we are not using any extra space to store
    private int optimal(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int count = 0;
        int[] currentInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // There is no overlap
            // Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
            // So we can use >= here instead of >
            if (intervals[i][0] >= currentInterval[1]) {
                currentInterval = intervals[i];
                continue;
            }
            // Find the number of overlapping intervals
            count++;
        }
        return count;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        return optimal(intervals);
    }

    public static void main(String[] args) {
        NonOverlappingIntervals noi = new NonOverlappingIntervals();
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int res = noi.eraseOverlapIntervals(intervals);
        System.out.println(res);
    }
}
