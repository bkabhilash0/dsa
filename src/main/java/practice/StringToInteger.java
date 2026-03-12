package practice;

/**
 * <a href="https://leetcode.com/problems/string-to-integer-atoi/description/">String to Integer</a>
 * The algorithm for myAtoi(string s) is as follows:
 * Whitespace: Ignore any leading whitespace (" ").
 * Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
 * Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached.
 * If no digits were read, then the result is 0.
 * Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range.
 * Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
 * Return the integer as the final result.
 */
public class StringToInteger {
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public int myAtoi(String s) {
        int result = 0;
        int sign = 1;
        int i = 0;
        int n = s.length();
        // Find the first index without whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        // Check if the first non space is a sign
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            // Check for overflow and underflow before multiplying result by 10 and adding the digit
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }


    public static void main(String[] args) {
        StringToInteger sti = new StringToInteger();
        String s = "   -+0044";
        int res = sti.myAtoi(s);
        System.out.println(res);
    }
}
