package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/merge-intervals/">...</a>
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */
public class MergeIntervals {
    // Time Complexity: O(n log n) + O(n^2) due to sorting
    // Space Complexity: O(n) for the output list
    private int[][] bruteForce(int[][] intervals) {
        // Implementation goes here
        List<int[]> mergedIntervals = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int i = 0;
        while (i < intervals.length) {
            // Handle the first interval or non-overlapping interval
            if (mergedIntervals.isEmpty() || intervals[i][0] > mergedIntervals.getLast()[1]) {
                mergedIntervals.add(new int[]{intervals[i][0], intervals[i][1]});
                i++;
                continue;
            }
            // Get the last interval in mergedIntervals
            int[] lastInterval = mergedIntervals.getLast();
            // Get the start of the merged interval - Start is not going to change as its a sorted list
            int start = lastInterval[0];
            int j = i;
            // Check for all the intervals which has the start less than or equal to end of last interval
            while (j < intervals.length && intervals[j][0] <= mergedIntervals.getLast()[1]) {
                // Update the end of the merged interval with the maximum end
                int end = Math.max(mergedIntervals.getLast()[1], intervals[j][1]);
                // Remove the last interval as we are going to merge it
                mergedIntervals.removeLast();
                // Add the merged interval back to the list
                mergedIntervals.add(new int[]{start, end});
                j++;
            }
            i = j;
        }
        return mergedIntervals.toArray(new int[0][]);
    }

    // Time Complexity: O(n log n) due to sorting + O(n) for single pass
    // Space Complexity: O(n) for the output list
    private int[][] optimal(int[][] intervals) {
        // Implementation goes here
        if (intervals.length <= 1) {
            return intervals;
        }
        List<int[]> mergedIntervals = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] currentInterval = intervals[0];
        mergedIntervals.add(currentInterval);
        int i = 1;
        while (i < intervals.length) {
            // Handle the non-overlapping interval
            if (intervals[i][0] > mergedIntervals.getLast()[1]) {
                currentInterval = intervals[i];
                mergedIntervals.add(currentInterval);
            } else {
//                // Get the last interval in mergedIntervals
//                int[] lastInterval = mergedIntervals.getLast();
//                // Get the start of the merged interval - Start is not going to change as its a sorted list
//                int start = lastInterval[0];
//                int end = Math.max(mergedIntervals.getLast()[1], intervals[i][1]);
//                // Remove the last interval as we are going to merge it
//                mergedIntervals.removeLast();
//                // Add the merged interval back to the list
//                mergedIntervals.add(new int[]{start, end});
                // Modify the reference of currentInterval to update the end, modifying the reference can eliminate the need to remove and add back to the list
                currentInterval[1] = Math.max(currentInterval[1], intervals[i][1]);
            }
            i++;
        }
        return mergedIntervals.toArray(new int[0][]);
    }

    public int[][] merge(int[][] intervals) {
        return optimal(intervals);
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        int[][] mergedIntervals = mi.merge(intervals);
        System.out.println("Merged Intervals: ");
        System.out.println(Arrays.deepToString(mergedIntervals));
    }
}
