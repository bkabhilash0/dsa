package sliding_window;

/**
 * There are several cards arranged in a row, and each card has an associated number of points.
 * The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 */
public class MaximumPointsYouCanObtainFromCards {
    // A Constructive method of sliding window as the ranges are shrunk or expanded
    // Time Complexity: O(2K)
    // Space Complexity: O(1)
    private int optimal(int[] cardPoints, int k) {
        int sum1 = 0, sum2 = 0;
        int totalSum = 0, maxSum = Integer.MIN_VALUE;
        int lastIndex = cardPoints.length - 1;

        // Find the sum of first k nums
        for (int i = 0; i < k; i++) {
            sum1 += cardPoints[i];
        }
        maxSum = sum1;

        // Now gradually start decreasing the sum of first k and increasing the last k
        for (int i = k - 1; i >= 0; i--) {
            sum1 = sum1 - cardPoints[i];
            int lastKNumbersIndex = lastIndex - (k - (i + 1));
            sum2 = sum2 + cardPoints[lastKNumbersIndex];
            totalSum = sum1 + sum2;
            maxSum = Math.max(maxSum, totalSum);
        }
        return maxSum;
    }

    // Another method: Remove smallest middle window
    // Time Complexity: O(n)
    // Space Complexity: (1)
    private int better(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;
        for (int num : cardPoints) {
            totalSum += num;
        }
        if (k == n) return totalSum;

        // Now find the subarray of minimum sum with the size (n-k)
        int minSubarraySum = 0;
        int windowSum = 0;
        for (int i = 0; i < n - k; i++) {
            windowSum += cardPoints[i];
        }
        minSubarraySum = windowSum;


        for(int i = (n - k); i < n; i++){
            windowSum += cardPoints[i];
            windowSum -= cardPoints[i - (n - k)];
            minSubarraySum = Math.min(minSubarraySum, windowSum);
        }

//        int left = 0;
//        int right = left + (n - k);
//        while (right < n) {
//            windowSum += cardPoints[right];
//            windowSum -= cardPoints[left - (n - k)];
//            minSubarraySum = Math.min(minSubarraySum, windowSum);
//            left++;
//            right++;
//        }
        return totalSum - minSubarraySum;
    }

    public int maxScore(int[] cardPoints, int k) {
        return better(cardPoints, k);
    }

    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        System.out.println(new MaximumPointsYouCanObtainFromCards().maxScore(cardPoints, k));
    }
}
