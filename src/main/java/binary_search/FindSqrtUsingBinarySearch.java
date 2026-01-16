package binary_search;

/**
 * <a href="https://takeuforward.org/binary-search/finding-sqrt-of-a-number-using-binary-search">...</a>
 * You are given a positive integer n. Your task is to find and return its square root.
 * If ‘n’ is not a perfect square, then return the floor value of sqrt(n).
 */
public class FindSqrtUsingBinarySearch {
    // Brute Force Approach - Linear Search
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int bruteForce(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (i * i <= n) {
                result = i;
            } else {
                break;
            }
        }
        return result;
    }

    private int optimal(int n) {
        int left = 1;
        int right = n;
//        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midSquare = mid * mid;
            if (midSquare <= n) {
//                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // If we observe carefully, at the end of the loop, right will be pointing to the floor value of sqrt(n)
        return right;
    }

    public int sqrt(int n) {
        return optimal(n);
    }

    public static void main(String[] args) {
        FindSqrtUsingBinarySearch fsu = new FindSqrtUsingBinarySearch();
        int n = 67;
        System.out.println("n: " + n);
        int result = fsu.sqrt(n);
        System.out.println("Result: " + result);
    }
}
