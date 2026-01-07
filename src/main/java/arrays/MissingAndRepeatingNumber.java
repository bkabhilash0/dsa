package arrays;

/**
 * <a href="https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers">...</a>
 * Given an array of size n containing numbers from 1 to n.
 * One number is missing, and one number is repeating.
 * Find the missing and repeating numbers.
 * Example:
 * Input: arr = [3, 1, 3, 5, 4]
 * Output: Missing number = 2, Repeating number = 3
 */
public class MissingAndRepeatingNumber {
    // Brute Force Approach: For each number from 1 to n, count its occurrences in the array.
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private int[] bruteForce(int[] arr, int n) {
        int count = 0;
        int missing = -1, repeating = -1;
        for (int i = 1; i < n; i++) {
            count = 0;
            for (int j = 0; j < n; j++) {
                if (i == arr[j]) {
                    count++;
                }
            }
            if (count == 0) {
                missing = i;
            } else if (count == 2) {
                repeating = i;
            }
        }
        return new int[]{missing, repeating};
    }

    // Better Approach: Use a frequency array to count occurrences of each number.
    // Time Complexity: O(2n)
    // Space Complexity: O(n)
    private int[] better(int[] arr, int n) {
        int[] map = new int[n + 1];
        int missing = -1, repeating = -1;
        for (int num : arr) {
            map[num]++;
        }
        for (int i = 1; i <= n; i++) {
            if (map[i] == 0) {
                missing = i;
            } else if (map[i] == 2) {
                repeating = i;
            }
        }
        return new int[]{missing, repeating};
    }

    // Optimal Approach Using Math:
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int[] optimalUsingMath(int[] arr, int n) {
        int s1 = n * (n + 1) / 2;
        int s2 = n * (n + 1) * (2 * n + 1) / 6;
        int actualSum = 0;
        int actualSumSq = 0;
        for (int num : arr) {
            actualSum += num;
            actualSumSq += num * num;
        }

        int XMinusY = s1 - actualSum; // missing - repeating
        int X2MinusY2 = s2 - actualSumSq; // missing^2 - repeating^2
        int XPlusY = X2MinusY2 / XMinusY; // missing + repeating
        int TwoX = XMinusY + XPlusY; // 2 * missing
        int missing = TwoX / 2;
        int repeating = missing - XMinusY;
        return new int[]{missing, repeating};
    }

    // Optimal Approach Using XOR:
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int[] optimalUsingXOR(int[] arr, int n) {
        int xor = 0;
        for (int num : arr) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }

        int rightmostSetBit = xor & -xor;
        int x = 0, y = 0;

        for (int num : arr) {
            if ((num & rightmostSetBit) != 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & rightmostSetBit) != 0) {
                x ^= i;
            } else {
                y ^= i;
            }
        }

        int countX = 0;
        for (int num : arr) {
            if (num == x) {
                countX++;
            }
        }

        if (countX == 2) {
            return new int[]{y, x}; // missing, repeating
        } else {
            return new int[]{x, y}; // missing, repeating
        }
    }

    public int[] findMissingAndRepeating(int[] arr, int n) {
        return optimalUsingXOR(arr, n);
    }

    public static void main(String[] args) {
        MissingAndRepeatingNumber manr = new MissingAndRepeatingNumber();
        int[] arr = {3, 1, 3, 5, 4};
        int n = arr.length;
        int[] result = manr.findMissingAndRepeating(arr, n);
        System.out.println("Missing number = " + result[0] + ", Repeating number = " + result[1]);
    }
}
