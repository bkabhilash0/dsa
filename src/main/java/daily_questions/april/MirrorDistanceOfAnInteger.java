package daily_questions.april;

/**
 * Date: April 18, 2026,
 * <a href="https://leetcode.com/problems/mirror-distance-of-an-integer">Mirror Distance of An Integer</a>
 * You are given an integer n.
 * Define its mirror distance as: abs(n - reverse(n)) where reverse(n) is the integer formed by reversing the digits of n.
 * Return an integer denoting the mirror distance of n.
 * abs(x) denotes the absolute value of x.
 */
public class MirrorDistanceOfAnInteger {
    private int reverse(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }

    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public int mirrorDistance(int n) {
        return Math.abs(n - reverse(n));
    }

    public static void main(String[] args) {
        MirrorDistanceOfAnInteger solution = new MirrorDistanceOfAnInteger();
        System.out.println(solution.mirrorDistance(123)); // 198
        System.out.println(solution.mirrorDistance(1)); // 0
    }
}
