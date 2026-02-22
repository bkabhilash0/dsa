package greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/valid-parenthesis-string/description/">Valid Parenthesis String</a>
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 * The following rules define a valid string:
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 */
public class ValidParenthesisString {
    private boolean executeBruteForce(String s, int index, int count) {
        if (index == s.length()) {
            return count == 0;
        }
        if (count == -1) {
            return false;
        }

        char c = s.charAt(index);
        if (c == '(') {
            return executeBruteForce(s, index + 1, count + 1);
        } else if (c == ')') {
            return executeBruteForce(s, index + 1, count - 1);
        } else {
            return executeBruteForce(s, index + 1, count + 1) || executeBruteForce(s, index + 1, count - 1) || executeBruteForce(s, index + 1, count);
        }
    }

    // Time Complex: O(3^n) where n is the length of the string, we are trying all the combinations of the string
    // Space Complex: O(n) where n is the length of the string, we are using the call stack to store the recursive calls
    private boolean bruteForce(String s) {
        return executeBruteForce(s, 0, 0);
    }

    private boolean executeDP(String s, int index, int count, int[][] dp) {
        // When count goes below 0, it means we have more close parentheses than open parentheses, which is invalid.
        // When count goes above the length of the string, it means we have more open parentheses than the total number of characters in the string, which is also invalid.
        if (count < 0 || count > s.length()) return false;

        if (dp[index][count] != -1) {
            return dp[index][count] == 1;
        }

        if (index == s.length()) {
            dp[index][count] = (count == 0) ? 1 : 0;
            return dp[index][count] == 1;
        }

        char c = s.charAt(index);
        boolean res;
        if (c == '(') {
            res = executeDP(s, index + 1, count + 1, dp);
        } else if (c == ')') {
            res = executeDP(s, index + 1, count - 1, dp);
        } else {
            res = (executeDP(s, index + 1, count + 1, dp) || executeDP(s, index + 1, count - 1, dp) || executeDP(s, index + 1, count, dp));
        }
        dp[index][count] = res ? 1 : 0;
        return res;
    }

    // DP approach to store the results of the recursive calls and avoid redundant calculations
    // Time Complex: O(n^2) where n is the length of the string, we are iterating through the string and for each character we are trying all the combinations of the string
    // Space Complex: O(n^2) + O(n) for recursion stack, where n is the length of the string, we are using a 2D array to store the results of the recursive calls
    private boolean better(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        boolean res = executeDP(s, 0, 0, dp);
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return res;
    }

    // Time Complex: O(n) where n is the length of the string, we are iterating through the string once
    // Space Complex: (1) since we are not using any extra space to store
    private boolean optimal(String s) {
        int minOpenBrackets = 0;
        int maxOpenBrackets = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                minOpenBrackets++;
                maxOpenBrackets++;
            } else if (ch == ')') {
                minOpenBrackets--;
                maxOpenBrackets--;
            } else {
                minOpenBrackets--;
                maxOpenBrackets++;
            }

            if (maxOpenBrackets < 0) {
                return false;
            }

            if (minOpenBrackets < 0) {
                minOpenBrackets = 0;
            }
        }
        return minOpenBrackets == 0;
    }

    public boolean checkValidString(String s) {
        return better(s);
    }

    public static void main(String[] args) {
        ValidParenthesisString vps = new ValidParenthesisString();
        String s = "(*((())))";
        boolean res = vps.checkValidString(s);
        System.out.println(res);
    }
}
