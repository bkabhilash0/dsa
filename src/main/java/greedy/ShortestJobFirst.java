package greedy;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/shortest-job-first/0">Shortest Job First</a>
 * Geek is a software engineer. He is assigned with the task of calculating average waiting time of all the processes by following shortest job first policy.
 * The shortest job first (SJF) or shortest job next, is a scheduling policy that selects the waiting process with the smallest execution time to
 * execute next.
 * Given an array of integers bt of size n. Array bt denotes the burst time of each process.
 * Calculate the average waiting time of all the processes and return the nearest integer which is smaller or equal to the output.
 * Note: Consider all process are available at time 0.
 */
public class ShortestJobFirst {
    // Time Complex: O(nlogn) + O(n) where n is the number of processes, we are sorting the array of burst times
    // Space Complex: O(1) if we are sorting the array in place
    private static int optimal(int bt[]) {
        int waitingTime = 0;
        int totalWaitingTime = 0;
        int n = bt.length;
        Arrays.sort(bt);
        for (int i = 1; i < n; i++) {
            waitingTime += bt[i - 1];
            totalWaitingTime += waitingTime;
        }
        return totalWaitingTime / n;
    }

    static int solve(int bt[]) {
        // code here
        return optimal(bt);
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[]{4, 3, 7, 1, 2}));;
    }
}
