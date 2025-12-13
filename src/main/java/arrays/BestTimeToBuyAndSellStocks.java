package arrays;

/*
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock
* You are given an array prices where prices[i] is the price of a given stock on the ith day.
* You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
* Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
*/
public class BestTimeToBuyAndSellStocks {
    private int bruteForce(int[] prices){
        int profit = 0;
        for(int i = 0; i < prices.length - 1; i++){
            for(int j = i + 1; j < prices.length; j++){
                int gain = prices[j] - prices[i];
                profit = Math.max(profit, gain);
            }
        }
        return profit;
    }

    private int optimal(int[] prices){
        int profit = 0;
        int minPrice = prices[0];
        for(int i = 1; i < prices.length; i++){
            int gain =  prices[i] - minPrice;
            profit = Math.max(profit, gain);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return profit;
    }

    public int maxProfit(int[] prices) {
        return bruteForce(prices);
    }
    public static void main(String[] args) {
        BestTimeToBuyAndSellStocks btbss = new BestTimeToBuyAndSellStocks();
        int[] prices = {7,1,5,3,6,4};
        int maxProfit = btbss.maxProfit(prices);
        System.out.println("Maximum profit is: " + maxProfit);
    }
}
