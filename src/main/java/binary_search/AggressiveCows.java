package binary_search;

import java.util.Arrays;

/*
 * <a href="https://www.geeksforgeeks.org/problems/aggressive-cows/1">...</a>
 * You are given an array with unique elements of stalls[], which denote the positions of stalls.
 * You are also given an integer k which denotes the number of aggressive cows.
 * The task is to assign stalls to k cows such that the minimum distance between any two of them is the maximum possible.
 */
public class AggressiveCows {
    private boolean canPlaceCows(int[] stalls, int k, int distance) {
        int cowsPlaced = 1; // Place the first cow in the first stall
        int lastPosition = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            int currentDistance = stalls[i] - lastPosition;
            if (currentDistance >= distance) {
                System.out.println("Placing cow " + (cowsPlaced + 1) + " at stall position " + stalls[i] + " with distance " + currentDistance);
                lastPosition = stalls[i];
                cowsPlaced++;
            }

            if (cowsPlaced == k) {
                return true;
            }
        }
        return false;
    }

    // Brute Force Approach - Linear Search
    // Time Complexity: O(n * m) where n is the number of stalls and m is the maximum possible distance
    // Space Complexity: O(1)
    private int bruteForce(int[] stalls, int k) {
        // code here
        int maxPossibleDistance = stalls[stalls.length - 1] - stalls[0];
        int n = stalls.length;
        int distance = 1;
        for (distance = 1; distance <= maxPossibleDistance; distance++) {
            if (!canPlaceCows(stalls, k, distance)) {
                return distance - 1;
            }
        }
        return distance - 1;
    }

    private int optimal(int[] stalls, int k) {
        // code here
        int left = 1;
        int right = stalls[stalls.length - 1] - stalls[0];
        while(left <= right){
            int mid = (left + right) / 2;
            if(canPlaceCows(stalls, k, mid)){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public int aggressiveCows(int[] stalls, int k) {
        // code here
        if (stalls.length < k) {
            return -1;
        }
        Arrays.sort(stalls);
        return optimal(stalls, k);
    }

    public static void main(String[] args) {
        AggressiveCows ac = new AggressiveCows();
        int[] stalls = {1, 5, 17};
        int k = 2;
        System.out.println("stalls: " + java.util.Arrays.toString(stalls));
        System.out.println("\nk: " + k);
        int result = ac.aggressiveCows(stalls, k);
        System.out.println("Result: " + result);
    }
}
