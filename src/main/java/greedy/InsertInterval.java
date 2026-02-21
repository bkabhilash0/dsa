package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/insert-interval/description/">Insert Interval</a>
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the
 * end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end]
 * that represents the start and end of another interval.
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and
 * intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * Return intervals after the insertion.
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 */
public class InsertInterval {
    // Time Complex: O(n log n) + O(n) where n is the number of intervals, we are sorting the intervals based on their start time after the insertion
    // Space Complex: O(n) where n is the number of intervals, we are creating a new array to store the merged intervals after the insertion
    private int[][] bruteForce(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int[][] res = new int[n + 1][2];
        int index = 0;
        for (int[] currentInterval : intervals) {
            // No overlap, newInterval is before the current interval
            // Draw a number line to see the overlap and non-overlap cases, it will be easier to understand
            // The new interval start has to be after the current interval end
            // OR the new interval end has to be before the current interval start
            if (newInterval[0] > currentInterval[1] || newInterval[1] < currentInterval[0]) {
                res[index++] = currentInterval;
            } else {
                newInterval[0] = Math.min(newInterval[0], currentInterval[0]);
                newInterval[1] = Math.max(newInterval[1], currentInterval[1]);
            }
        }
        res[index++] = newInterval;
        res = Arrays.copyOf(res, index);
        Arrays.sort(res, (a, b) -> a[0] - b[0]);
        return res;
    }

    // Time Complex: O(n) where n is the number of intervals, we are iterating through the intervals once
    // Space Complex: O(n) where n is the number of intervals, we are creating a new array to store the merged intervals after the insertion
    private int[][] optimal(int[][] intervals, int[] newInterval) {
//        if(intervals.length == 0) return new int[][]{{newInterval[0], newInterval[1]}};
        int n = intervals.length;
        int[][] res = new int[n + 1][2];
        int resIndex = 0;
        int index = 0;

        // No Overlapping left side intervals
        while (index < n && intervals[index][1] < newInterval[0]) {
            res[resIndex++] = intervals[index];
            index++;
        }
        // Overlapping intervals, merge them
        while (index < n && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            index++;
        }
        res[resIndex++] = newInterval;
        // Non overlapping right side intervals
        while (index < n) {
            res[resIndex++] = intervals[index];
            index++;
        }
        return Arrays.copyOf(res, resIndex);
    }

    // Time Complex: O(n) where n is the number of intervals, we are iterating through the intervals once
    // Space Complex: O(n) where n is the number of intervals, we are creating
    private int[][] moreOptimal(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // Step 1: Add all intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2: Merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Step 3: Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        return optimal(intervals, newInterval);
    }

    public static void main(String[] args) {
        InsertInterval ii = new InsertInterval();
//        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] intervals = {};
//        int[] newInterval = {4, 8};
        int[] newInterval = {5, 7};
        int[][] res = ii.insert(intervals, newInterval);
        for (int[] interval : res) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }
}
