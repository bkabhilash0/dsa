package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://www.geeksforgeeks.org/problems/valid-parentheses/1">Valid Parenthesis</a>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 */
public class ValidParenthesis {
    // Time Complexity: O(n) where n is the length of the input string, we are iterating through the string once
    // Space Complexity: O(n) in the worst case when all characters in the string are opening brackets, we are storing all of them in the stack
    private boolean optimal(String s){
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char top = stack.pop();
                boolean isValid = false;
                switch (c) {
                    case ')':
                        isValid = top == '(';
                        break;
                    case '}':
                        isValid = top == '{';
                        break;
                    case ']':
                        isValid = top == '[';
                        break;
                    default:
                        break;
                }
                if (!isValid) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        return optimal(s);
    }

    public static void main(String[] args) {
        ValidParenthesis vp = new ValidParenthesis();
        String s = "([)]";
        System.out.println(vp.isValid(s));
    }
}
