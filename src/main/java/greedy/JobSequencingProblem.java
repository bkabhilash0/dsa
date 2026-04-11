package greedy;

import commons.Utils;
import graphs.DisjointSetUnion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1">Job Sequencing Problem</a>
 * You are given two arrays: deadline[], and profit[], which represent a set of jobs, where each job is associated with a deadline,
 * and a profit. Each job takes 1 unit of time to complete, and only one job can be scheduled at a time.
 * You will earn the profit associated with a job only if it is completed by its deadline.
 * Your task is to find:
 * The maximum number of jobs that can be completed within their deadlines.
 * The total maximum profit earned by completing those jobs.
 */
public class JobSequencingProblem {
    private static class Job {
        int deadline;
        int profit;

        public Job(int deadline, int profit) {
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    private int getMaxDeadline(int[] deadline) {
        int maxDeadline = 0;
        for (int d : deadline) {
            maxDeadline = Math.max(maxDeadline, d);
        }
        return maxDeadline;
    }

    private ArrayList<Job> getJobs(int[] deadline, int[] profit) {
        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < deadline.length; i++) {
            jobs.add(new Job(deadline[i], profit[i]));
        }
        // Sort the jobs in decreasing order of profit so that we can schedule the most profitable jobs first.
        jobs.sort((a, b) -> b.profit - a.profit);
        return jobs;
    }

    // Time Complexity: O(n log n) + O(n * m) where n is the number of jobs and m is the maximum deadline
    // Space Complexity: O(n) where n is the number of jobs, we are using an array to keep track of the scheduled jobs, the size of the array is equal to the maximum deadline.
    // This is not the optimal solution, we can solve this problem in O(n log n) time and O(n) space using a disjoint set data structure.
    private ArrayList<Integer> solve(int[] deadline, int[] profit) {
        // code here
        int lastDeadLine = getMaxDeadline(deadline);
        int n = deadline.length;
        int[] scheduledJobs = new int[lastDeadLine];
        Arrays.fill(scheduledJobs, -1);
        int countOfJobs = 0;
        int maxProfit = 0;
        ArrayList<Job> jobs = getJobs(deadline, profit);
        for (Job job : jobs) {
            int deadLine = job.deadline - 1;
            int profitOfJob = job.profit;
            // We will try to schedule the job at before deadline
            while (deadLine >= 0 && scheduledJobs[deadLine] != -1) {
                deadLine--;
            }

            if (deadLine >= 0) {
                scheduledJobs[deadLine] = profitOfJob;
                countOfJobs++;
                maxProfit += profitOfJob;
            }
        }
        return new ArrayList<>(Arrays.asList(countOfJobs, maxProfit));
    }

    private int findParent(int x, int[] parents) {
        if (parents[x] == x) return x;
        return parents[x] = findParent(parents[x], parents);
    }

    private void union(int x, int y, int[] parents) {
        int rootX = findParent(x, parents);
        int rootY = findParent(y, parents);
        parents[rootX] = rootY;
    }

    // Time Complexity: O(n log n) where n is the number of jobs, we are sorting the jobs based on profit and then we are using a disjoint set data structure to schedule the jobs in O(log n) time.
    // Space Complexity: O(n) where n is the number of jobs
    private ArrayList<Integer> optimal(int[] deadline, int[] profit) {
        int n = deadline.length;
        int lastDeadLine = getMaxDeadline(deadline);
        ArrayList<Job> jobs = getJobs(deadline, profit);
        // 1 based indexing DSU, there are nodes equal to the last deadline
        int[] parents = new int[lastDeadLine + 1];
        for (int i = 0; i <= lastDeadLine; i++) {
            parents[i] = i;
        }
        int countOfJobs = 0, maxProfit = 0;
        for (Job job : jobs) {
            int deadLine = job.deadline;
            int profitOfJob = job.profit;
            // Get the parent of the day
            // Here basically the root parent points to the last available day on or before the deadline
            int parent = findParent(deadLine, parents);
            // If parent is 0, it means no more days are available before the deadline, so we can't schedule this job
            // We start from day 1 so if day 0 then it means day 1 is already reserved for some other job, so we can't schedule this job
            if (parent <= 0) continue;
            countOfJobs++;
            maxProfit += profitOfJob;
            // Now union the current day and the previous day so that the parent becomes the last available day
            union(parent, parent - 1, parents);
        }
        return new ArrayList<>(Arrays.asList(countOfJobs, maxProfit));
    }

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        // code here
        return optimal(deadline, profit);
    }

    public static void main(String[] args) {
        int[] deadline = {4, 1, 1, 1};
        int[] profit = {20, 10, 40, 30};
        JobSequencingProblem jobSequencingProblem = new JobSequencingProblem();
        ArrayList<Integer> res = jobSequencingProblem.jobSequencing(deadline, profit);
        System.out.println("Maximum number of jobs that can be completed: " + res.get(0));
        System.out.println("Total maximum profit earned: " + res.get(1));
    }
}

