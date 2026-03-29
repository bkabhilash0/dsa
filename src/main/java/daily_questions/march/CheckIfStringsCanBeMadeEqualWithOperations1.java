package daily_questions.march;

import com.sun.security.jgss.GSSUtil;

/**
 * Date: March 29, 2026,
 * <a href="https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-i">Check if Strings Can be Made Equal With Operations I</a>
 * You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.
 * You can apply the following operation on any of the two strings any number of times:
 * Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.
 * Return true if you can make the strings s1 and s2 equal, and false otherwise.
 */
public class CheckIfStringsCanBeMadeEqualWithOperations1 {
    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    // Since the strings are of size 4, we shall bruteForce
    // Time Complexity: O(2^d * N) → O(1) here
    // Space Complexity: O(d) → O(1)
    private boolean execute(String s1, String s2, int index) {
        if (index > 1) {
            return s1.equals(s2);
        }
        return execute(s1, s2, index + 1) || execute(swap(s1, index, index + 2), s2, index + 1);
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    private boolean iterativeApproach(String s1, String s2) {
        return ((s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0))) &&
                ((s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) ||
                        (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1)));
    }

    public boolean canBeEqual(String s1, String s2) {
        return iterativeApproach(s1, s2);
    }

    public static void main(String[] args) {
        CheckIfStringsCanBeMadeEqualWithOperations1 check = new CheckIfStringsCanBeMadeEqualWithOperations1();
        System.out.println(check.canBeEqual("abcd", "cdab"));
    }
}
