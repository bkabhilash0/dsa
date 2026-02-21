package greedy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/merge-intervals/description/">Merge Intervals</a>
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */
public class MergeIntervals {
    // Time Complex: O(n log n) + O(n) where n is the number of intervals, we are sorting the intervals based on their start range
    // Space Complex: O(n) where n is the number of intervals, we are creating a new array to store the merged intervals
    private int[][] optimal(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = intervals.length;
        ArrayList<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        int[] current = intervals[0];
        for (int i = 1; i < n; i++) {
            // No overlap, we can add the current interval to the result
            if (intervals[i][0] > res.getLast()[1]) {
                current = intervals[i];
                res.add(current);
            } else {
                // Modify the reference
                current[1] = Math.max(current[1], intervals[i][1]);
            }
        }
        return res.toArray(new int[0][]);
    }

    public int[][] merge(int[][] intervals) {
        return optimal(intervals);
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res = mi.merge(intervals);
        for (int[] interval : res) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }
}
