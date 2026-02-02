package binary_search;

import commons.Utils;

import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/minimize-max-distance-to-gas-station/">...</a>
 * <a href="https://www.geeksforgeeks.org/problems/minimize-max-distance-to-gas-station/1">...</a>
 * You are given an array stations that represents the positions of gas stations along a horizontal line, and an integer k.
 * You should add k new gas stations to the line.
 * Find the smallest possible value of the maximum distance between adjacent gas stations after adding the k new gas stations.
 * Return the smallest possible value of the maximum distance between adjacent gas stations. Answers within 10^-6 of the actual answer will be accepted.
 * Example 1:
 * Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
 * Output: 0.500000
 * Explanation: We can add a gas station in the middle of each interval, and the maximum distance between adjacent gas stations will be 0.5.
 */
public class MinimizeMaxDistanceToGasStation {
    private static class StationIndex {
        int index;
        double distance;

        StationIndex(double distance, int index) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Index: " + index + ", Distance: " + distance;
        }
    }

    // Brute Force Approach
    // Time Complexity: O(K * N) - where N is the number of stations and K is the number of new stations to be added
    // Space Complexity: O(N)
    private double bruteForce(int[] stations, int K) {
        int[] stationsInBetween = new int[stations.length - 1];
        for (int newStation = 1; newStation <= K; newStation++) {
            // Loop through the stationsInBetween array to find the maximum distance
            int maxIndex = 0;
            double maxDistance = Integer.MIN_VALUE;
            for (int i = 0; i < stationsInBetween.length; i++) {
                int defaultDistance = stations[i + 1] - stations[i];
                double newDistance = (double) defaultDistance / (stationsInBetween[i] + 1);
                if (newDistance > maxDistance) {
                    maxDistance = newDistance;
                    maxIndex = i;
                }
            }
            stationsInBetween[maxIndex]++;
        }

        // Now Loop through the stationsInBetween array to find the maximum distance
        double result = Double.MIN_VALUE;
        for (int i = 0; i < stationsInBetween.length; i++) {
            int defaultDistance = stations[i + 1] - stations[i];
            double distance = (double) defaultDistance / (stationsInBetween[i] + 1);
            result = Math.max(result, distance);
        }
        // code here
        return result;
    }

    // Better Approach using MaxHeap
    // Time Complexity: O(N log N + K log N) - where N is the number of stations and K is the number of new stations to be added
    // Space Complexity: O(N)
    private double better(int[] stations, int K) {
        int[] stationsInBetween = new int[stations.length - 1];
        // By default the PriorityQueue in Java is a MinHeap. Use the comparator to convert it to a MaxHeap.
        PriorityQueue<StationIndex> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.distance, a.distance));

        // Initialize the maxHeap with the initial distances. It takes O(N * log N) time.
        // Heap insertion takes O(log N) time and we are doing it for N stations.
        for (int i = 1; i < stations.length; i++) {
            maxHeap.add(new StationIndex((stations[i] - stations[i - 1]), i - 1));
        }

        for (int newStation = 1; newStation <= K; newStation++) {
            // Replacing the inner loop with a maxHeap to get the maximum distance in O(1) time but the new insertion will take O(log N) time.
            StationIndex longestStation = maxHeap.poll();
            if (longestStation == null) break;
            // It gives the index of the stationsInBetween array which has the longest distance. This can avoid the inner loop and the distance calculation again.
            int longestStationIndex = longestStation.index;
            // Add the new station in between the longest distance stations.
            stationsInBetween[longestStationIndex]++;
            // Calculate the new distance after adding the new station and add it back to the maxHeap.
            int defaultDistance = stations[longestStationIndex + 1] - stations[longestStationIndex];
            double newDistance = (double) defaultDistance / (stationsInBetween[longestStationIndex] + 1);
            maxHeap.add(new StationIndex(newDistance, longestStationIndex));
        }
        // Just take the top element from the maxHeap which will have the maximum distance.
        return maxHeap.peek() != null ? maxHeap.peek().distance : -1.0;
    }

    private int getStationsCount(int[] stations, double possibleDistance) {
        int stationsCount = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            int distance = stations[i + 1] - stations[i];
            stationsCount += (int) Math.ceil(distance / possibleDistance) - 1;
        }
        return stationsCount;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(N) + O(N log M) - where N is the number of stations and M is the range of distances
    // Space Complexity: O(1)
    private double optimal(int[] stations, int K) {
        double start = 0;
        double end = Utils.getMax(stations);

        // Find the maximum distance between the stations to set the 'end' of our binary search range.
//        for (int i = 0; i < stations.length - 1; i++) {
//            end = Math.max(end, stations[i + 1] - stations[i]);
//        }

        double limit = 1e-6;
        while ((end - start) > limit) {
            double mid = (start + end) / 2;
            if (getStationsCount(stations, mid) <= K) {
                end = mid;
            } else {
                start = mid;
            }
        }

        // We can return either low or high as their difference is beyond 10^(-6). They both can be the possible answer. Here, we have returned the ‘start/low’.
        return start;
    }

    public double minMaxDist(int[] stations, int K) {
        // code here
        if (stations == null || stations.length <= 1 || K <= 0) return 0.0;
        return optimal(stations, K);
    }

    public static void main(String[] args) {
        MinimizeMaxDistanceToGasStation mmmdtg = new MinimizeMaxDistanceToGasStation();
        int[] stations = {3, 6, 12, 19, 33};
        int K = 3;
//        int[] stations = {3, 6, 12, 19, 33};
//        int K = 3;
        System.out.println("Minimized Maximum Distance to Gas Station: " + mmmdtg.minMaxDist(stations, K));
    }
}
