package daily_questions.march;

/**
 * Date: March 11, 2026,
 * <a href="https://leetcode.com/problems/complement-of-base-10-integer">Complement Of Base10 Integer</a>
 * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
 * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
 * Given an integer n, return its complement.
 */
public class ComplementOfBase10Integer {
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        // Highest 1 bit means flip all the bit to 0 and have only the first bit as 1
        // Eg: 5 -> 101 -> highest 1 bit is 100
        // Now shift left, this add one 0 at the end. So 100 << 1 -> 1000
        // Now sub 1 from 1000, it gives 111
        // This is like getting the number sequence, to find which mask i.e. the nears number with all ones of the same size
        // Then XOR
        int temp = (Integer.highestOneBit(n) << 1) - 1;
        return n ^ temp;
    }

    public static void main(String[] args) {
        ComplementOfBase10Integer cbi = new ComplementOfBase10Integer();
        System.out.println(cbi.bitwiseComplement(5));
    }
}
