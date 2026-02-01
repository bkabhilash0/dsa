package binary_search;

/**
 * <a href="https://www.codechef.com/practice/course/binary-search/INTBINS01/problems/BSEX03">...</a>
 * You have n boards of different lengths and m painters. You have to paint all the boards
 * such that the maximum time taken by a painter to paint a board is minimized.
 * A painter can only paint contiguous boards.
 */
public class PaintersPartition {
    public static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static int getSummation(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    private static int paintersRequired(int[] boards, int maxLengthAllowed) {
        int paintersRequired = 1;
        int currentLength = 0;
        for (int length : boards) {
            if (length + currentLength > maxLengthAllowed) {
                currentLength = 0;
                paintersRequired++;
            }
            currentLength += length;
        }
        return paintersRequired;
    }

    // Brute Force Approach - Linear Search
    // Time Complexity: O(n * m) where n is the number of boards and m is the range between max board length and sum of all board lengths
    // Space Complexity: O(1)
    private static int bruteForce(int[] boards, int numberOfPainters) {
        int start = getMax(boards);
        int end = getSummation(boards);
        for (int i = start; i <= end; i++) {
            int paintersNeeded = paintersRequired(boards, i);
            if (paintersNeeded <= numberOfPainters) {
                return i;
            }
        }
        return -1;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(n * log m) where n is the number of boards and m is the range between max board length and sum of all board lengths
    // Space Complexity: O(1)
    private static int optimal(int[] boards, int numberOfPainters) {
        int start = getMax(boards);
        int end = getSummation(boards);
        while (start <= end) {
            int mid = (start + end) / 2;
            int paintersNeeded = paintersRequired(boards, mid);
            if (paintersNeeded <= numberOfPainters) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static int painterPartition(int[] boards, int numberOfPainters) {
        if (numberOfPainters > boards.length) return -1;
        return bruteForce(boards, numberOfPainters);
    }

    public static void main(String[] args) {
        PaintersPartition pp = new PaintersPartition();
        int[] boards = {10, 20, 30, 40};
        int m = 2;
        System.out.println("boards: " + java.util.Arrays.toString(boards));
        System.out.println("\nm: " + m);
        int result = pp.bruteForce(boards, m);
        System.out.println("Result: " + result);
    }
}
