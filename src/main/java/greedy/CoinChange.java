package greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/coin-change/description/">Coin Change</a>
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {
    private int executeBruteForce(int[] coins, int amount, int numberOfCoins) {
        if (amount == 0) return numberOfCoins;
        if (amount < 0) return -1;

        int minCoins = -1;
        for (int coin : coins) {
            int result = executeBruteForce(coins, amount - coin, numberOfCoins + 1);
            if (result != -1 && (minCoins == -1 || result < minCoins)) {
                minCoins = result;
            }
        }
        return minCoins;
    }

    // Time Complex: O(n^m) where n is the number of coins and m is the amount, we are trying all the combinations of coins to make up the amount
    // Space Complex: O(m) where m is the amount, we are using the call
    private int bruteForce(int[] coins, int amount) {
        return executeBruteForce(coins, amount, 0);
    }

    private int executeDP(int[] coins, int amount, int numberOfCoins, int[][] dp) {
        if (amount == 0) {
            return numberOfCoins;
        }

        if (amount < 0) {
            return -1;
        }

        if (dp[amount][numberOfCoins] != -1) {
            return dp[amount][numberOfCoins];
        }

        int minCoins = -1;
        for (int coin : coins) {
            int result = executeDP(coins, amount - coin, numberOfCoins + 1, dp);
            if (result != -1 && (minCoins == -1 || result < minCoins)) {
                minCoins = result;
            }
        }
        System.out.println("Amount: " + amount + ", Number of Coins: " + numberOfCoins + ", Min Coins: " + minCoins);
        dp[amount][numberOfCoins] = minCoins;
        return minCoins;
    }

    //
    private int better(int[] coins, int amount) {
        int[][] dp = new int[amount + 1][amount + 1];
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = -1;
            }
        }
        return executeDP(coins, amount, 0, dp);
    }

    private int dpUsingOneState(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        if (dp[amount] != -2) {
            return dp[amount];
        }

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dpUsingOneState(coins, amount - coin, dp);
            if (res >= 0) {
                minCoins = Math.min(minCoins, res + 1);
            }
        }
        dp[amount] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return dp[amount];
    }

    // Time Complex: O(n*m) where n is the number of coins and m is the amount, we are trying all the combinations of coins to make up the amount but we are using memoization to avoid redundant calculations
    // Space Complex: (n) where n is the amount, can optimize it to O(m) by using a 1D array since we only need to store the results for the current amount and number of coins.
    private int better2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -2);
        return dpUsingOneState(coins, amount, dp);
    }

    public int coinChange(int[] coins, int amount) {
        return better2(coins, amount);
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
//        int[] coins = {1, 2, 5};
//        int amount = 11;
        int[] coins = {2};
        int amount = 3;
        System.out.println(coinChange.coinChange(coins, amount));
    }
}
