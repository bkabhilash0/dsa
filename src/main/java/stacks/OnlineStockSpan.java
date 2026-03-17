package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
 * The span of the stock's price in one day is the maximum number of consecutive days
 * (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.
 * For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2,
 * then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
 * Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8,
 * then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
 * Implement the StockSpanner class:
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 */
public class OnlineStockSpan {
    Deque<int[]> stack;
    int span;

    public OnlineStockSpan() {
        stack = new ArrayDeque<>();
        span = 1;
    }

    // Time Complexity: O(N) where N is the number of calls to the next method, in the worst case when the prices are in increasing order,
    // we are pushing all the elements in the stack and popping them in the next call
    // Space Complexity: O(N) in the worst case when the prices are in increasing order, we are storing all the elements in the stack
    public int next(int price) {
        span = 1;
        while (!stack.isEmpty() && price >= stack.peek()[0]) {
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return span;
    }

    private void printStack() {
        System.out.println("Stack: ");
        for (int[] arr : stack) {
            System.out.print(arr[0] + "-> " + arr[1] + " : ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        OnlineStockSpan oss = new OnlineStockSpan();
        System.out.println(oss.next(100)); // return 1
        System.out.println(oss.next(80));  // return 1
        System.out.println(oss.next(60));  // return 1
        System.out.println(oss.next(70));  // return 2
        System.out.println(oss.next(60));  // return 1
        System.out.println(oss.next(75));  // return 4
        System.out.println(oss.next(85));  // return 6
    }
}
