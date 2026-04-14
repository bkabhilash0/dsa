package daily_questions.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Date: April 14, 2026,
 * <a href="https://leetcode.com/problems/minimum-total-distance-traveled">Minimum Total Distance Travelled</a>
 * There are some robots and factories on the X-axis. You are given an integer array robot where robot[i]
 * is the position of the ith robot. You are also given a 2D integer array factory where factory[j] = [positionj, limitj]
 * indicates that positionj is the position of the jth factory and that the jth factory can repair at most limitj robots.
 * The positions of each robot are unique. The positions of each factory are also unique.
 * Note that a robot can be in the same position as a factory initially.
 * All the robots are initially broken; they keep moving in one direction.
 * The direction could be the negative or the positive direction of the X-axis.
 * When a robot reaches a factory that did not reach its limit, the factory repairs the robot, and it stops moving.
 * At any moment, you can set the initial direction of moving for some robot.
 * Your target is to minimize the total distance traveled by all the robots.
 * Return the minimum total distance traveled by all the robots.
 * The test cases are generated such that all the robots can be repaired.
 * Note that:
 * All robots move at the same speed.
 * If two robots move in the same direction, they will never collide.
 * If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
 * If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
 * If the robot moved from a position x to a position y, the distance it moved is |y - x|.
 */
public class MinimumTotalDistanceTravelled {
    private long solve(int i, int j, List<Integer> robot, int[][] factory, long[][] dp) {
        int n = robot.size();
        int m = factory.length;

        // all robots assigned
        if (i == n) return 0;

        // no factory left
        if (j == m) return (long) 1e15;

        if (dp[i][j] != -1) return dp[i][j];

        long res = solve(i, j + 1, robot, factory, dp); // skip factory

        long cost = 0;
        int pos = factory[j][0];
        int limit = factory[j][1];

        // assign k robots to this factory
        for (int k = 0; k < limit && i + k < n; k++) {
            cost += Math.abs(robot.get(i + k) - pos);
            res = Math.min(res, cost + solve(i + k + 1, j + 1, robot, factory, dp));
        }

        return dp[i][j] = res;
    }

    // Time Complexity: O(N * M * LIMIT)
    // Space Complexity: O(N * M) + O(N * M) for recursion stack
    private long optimal(List<Integer> robot, int[][] factory) {
        int n = robot.size();
        int m = factory.length;
        // We can sort the robots and the factories according to their positions
        Collections.sort(robot);
        // Sort the factories according to their positions
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        // dp[i][j] = minimum distance to repair robots from index i using factories from index j
        long[][] dp = new long[n][m];
        for (long[] row : dp) Arrays.fill(row, -1);
        // At each point we have 2 options, either we can skip the current factory and move to the next factory - dp[i][j+1]
        // or we can use the current factory to repair some robots by assigning robots to that factory at most the limit

        // Base cases:
        // If all robots are assigned → return 0
        // If no factories left → return large value
        return solve(0, 0, robot, factory, dp);
    }

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        return optimal(robot, factory);
    }

    public static void main(String[] args) {
        MinimumTotalDistanceTravelled minimumTotalDistanceTravelled = new MinimumTotalDistanceTravelled();
        List<Integer> robot = new ArrayList<>();
        robot.add(0);
        robot.add(4);
        robot.add(6);
        int[][] factory = {{2, 2}, {6, 2}};
        long result = minimumTotalDistanceTravelled.minimumTotalDistance(robot, factory);
        System.out.println("Minimum total distance: " + result);
    }
}
