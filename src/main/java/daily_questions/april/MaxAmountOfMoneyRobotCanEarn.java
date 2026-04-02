package daily_questions.april;

/**
 * Date: April 2, 2026,
 * You are given an m x n grid. A robot starts at the top-left corner of the grid (0, 0)
 * and wants to reach the bottom-right corner (m - 1, n - 1). The robot can move either right or down at any point in time.
 * The grid contains a value coins[i][j] in each cell:
 * If coins[i][j] >= 0, the robot gains that many coins.
 * If coins[i][j] < 0, the robot encounters a robber, and the robber steals the absolute value of coins[i][j] coins.
 * The robot has a special ability to neutralize robbers in at most 2 cells on its path, preventing them from stealing coins in those cells.
 * Note: The robot's total coins can be negative.
 * Return the maximum profit the robot can gain on the route.
 */
public class MaxAmountOfMoneyRobotCanEarn {
    int dfs(int[][] coins, int m, int n, int i, int j, Integer[][][] dp, int k) {
        if (i >= m || j >= n) return Integer.MIN_VALUE;
        if (i == m - 1 && j == n - 1) {
            if (coins[i][j] < 0 && k > 0) return 0;
            return coins[i][j];
        }
        if (dp[i][j][k] != null) return dp[i][j][k];

        int down = dfs(coins, m, n, i + 1, j, dp, k);
        int right = dfs(coins, m, n, i, j + 1, dp, k);

        int res;

        if (coins[i][j] >= 0) {
            res = Math.max(down, right) + coins[i][j];
        } else {
            int takeDamage = coins[i][j] + Math.max(down, right);
            int neutralised = Integer.MIN_VALUE;
            if (k > 0) {
                int down1 = dfs(coins, m, n, i + 1, j, dp, k - 1);
                int right1 = dfs(coins, m, n, i, j + 1, dp, k - 1);
                neutralised = Math.max(down1, right1);
            }
            res = Math.max(takeDamage, neutralised);
        }
        dp[i][j][k] = res;
        return dp[i][j][k];
    }

    // Time Complexity: O(m * n * 3)
    // Space Complexity: O(m * n * 3) + O(m + n)
    // O(m + n) is for max depth
    private int optimal(int[][] coins) {
        Integer[][][] dp = new Integer[coins.length][coins[0].length][3];
        return dfs(coins, coins.length, coins[0].length, 0, 0, dp, 2);
    }

    public int maximumAmount(int[][] coins) {
        return optimal(coins);
    }

    public static void main(String[] args) {
        int[][] coins = {{0, 1, -1}, {1, -2, 3}, {2, -3, 4}};
        MaxAmountOfMoneyRobotCanEarn maxAmountOfMoneyRobotCanEarn = new MaxAmountOfMoneyRobotCanEarn();
        int result = maxAmountOfMoneyRobotCanEarn.maximumAmount(coins);
        System.out.println(result); // 8
    }
}
