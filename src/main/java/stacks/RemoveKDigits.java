package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/remove-k-digits/description/">Remove K Digits</a>
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 */
public class RemoveKDigits {
    // Time Complexity: O(N) + (N) => Increasing numbers + k = length of array
    // Space Complexity: O(N) for stack
    public String removeKdigits(String num, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            int val = num.charAt(i) - '0';
            while (!stack.isEmpty() && k > 0 && val < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(val);
        }
        StringBuilder sb = new StringBuilder();
        // For edge cases where all are increasing numbers, remove the top k from stack
        while (k > 0) {
            stack.pop();
            k--;
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }
        String result = sb.substring(i);
        return result.isEmpty() ? "0" : sb.substring(i);
    }

    public static void main(String[] args) {
        RemoveKDigits rkd = new RemoveKDigits();
        String num = "1432219";
        int k = 3;
        System.out.println(rkd.removeKdigits(num, k));
    }
}
