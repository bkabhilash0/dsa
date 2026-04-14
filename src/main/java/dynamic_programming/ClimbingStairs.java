package dynamic_programming;

/**
 * <a href="https://leetcode.com/problems/climbing-stairs/">Climbing Stairs</a>
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {
    // Time Complexity: O(2^N)
    // Space Complexity: O(N) // Stack Space
    private int bruteForce(int n) {
        if (n <= 1) {
            return 1;
        }

        int oneJump = bruteForce(n - 1);
        int twoJump = bruteForce(n - 2);
        return oneJump + twoJump;
    }

    // Top Down Approach: Recursion + Memoization
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    private int better(int n, int[] dp) {
        if (dp[n] != 0) {
            return dp[n];
        }
        // We know this is the base case so we shall set dp[0] and dp[1] in the dp array during the init
        // Even if we do not set its fine, coz its not starting a recursion its just a constant operation
        if (n <= 1) {
            dp[n] = 1;
            return 1;
        }
        int oneJump = better(n - 1, dp);
        int twoJump = better(n - 2, dp);
        dp[n] = oneJump + twoJump;
        return dp[n];
    }

    // Time Complexity: O(N)
    // Space Complexity: O(N)
    private int optimal(int n) {
        // We know dp[i] = dp[i - 1] + dp [i - 2]
        // So we just need to keep track of the last 2 values and we can get the current value
        // The base case is when you stand at n there is only 1 way to reach n
        // If we stand at n - 1 then there is only 1 way to reach n
        // Lets come from reverse
        if (n <= 1) {
            return 1;
        }
        // represents dp[n]
        int a = 1;
        // represents dp[n - 1]
        int b = 1;
        // We need to find for dp[n - 2]
        // Say we are in stair 3, we need the number of ways to reach stair 5 from 4 and from 5
        // Coming in reverse
        // We already are in stair n - 2, coz we know the values for n and n - 1
        // Taking the 1 based indexing
        // Only when we reach step 0, that is the group we will know the total number of ways
        for (int i = n - 2; i >= 0; i--) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    public int climbStairs(int n) {
//        long start = System.currentTimeMillis();
//        int result = better(n, new int[n + 1]);
        int result = optimal(n);
//        int result = bruteForce(n);
//        long end = System.currentTimeMillis();
//        System.out.println("Time taken by better: " + (end - start) + "ms");
        return result;
//        return better(n, new int[n + 1]);
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs(100));
    }
}
