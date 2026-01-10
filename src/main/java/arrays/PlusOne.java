package arrays;

/**
 * <a href="https://leetcode.com/problems/plus-one/description/">...</a>
 * You are given a large integer represented as an integer array digits,
 * where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant in left-to-right order.
 * The large integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 */
public class PlusOne {
    // Brute Force Approach: Convert array to number, add one, convert back to array
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private int[] bruteForce(int[] digits) {
        int number = 0;
        for(int digit : digits) {
            number = number * 10 + digit;
        }
        number += 1;
        String numberStr = Integer.toString(number);
        digits = new int[numberStr.length()];
        for(int i = 0; i < numberStr.length(); i++) {
            digits[i] = Character.getNumericValue(numberStr.charAt(i));
        }
        return digits;
    }

    // Optimal Approach: Handle carry directly in the array
    // Time Complexity: O(n)
    // Space Complexity: O(1) or O(n) if a new array is created
    private int[] optimal(int[] digits) {
        int n = digits.length;
        for(int i = digits.length - 1; i >= 0; i--){
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

    public int[] plusOne(int[] digits) {
        return optimal(digits);
    }
    public static void main(String[] args) {
        PlusOne po = new PlusOne();
        int[] digits = {8,9,9};
        int[] result = po.plusOne(digits);
        System.out.print("Result after adding one: ");
        System.out.println(java.util.Arrays.toString(result));
    }
}
