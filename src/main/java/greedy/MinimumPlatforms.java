package greedy;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1">Minimum Platforms</a>
 * Given arrival arr[] and departure dep[] times of trains on the same day, find the minimum number of platforms needed so that no train waits.
 * A platform cannot serve two trains at the same time; if a train arrives before another departs, an extra platform is needed.
 * Note: Time intervals are in the 24-hour format (HHMM) , where the first two characters represent hour (between 00 to 23 )
 * and the last two characters represent minutes (this will be <= 59 and >= 0). Leading zeros for hours less than 10 are optional (e.g., 0900 is the same as 900).
 */
public class MinimumPlatforms {
    private static class Train {
        int arrivalTime;
        int departureTime;

        public Train(int arrivalTime, int departureTime) {
            this.arrivalTime = arrivalTime;
            this.departureTime = departureTime;
        }
    }

    private Train[] createTrains(int[] arr, int[] dep) {
        Train[] trains = new Train[arr.length];
        for (int i = 0; i < arr.length; i++) {
            trains[i] = new Train(arr[i], dep[i]);
        }
        return trains;
    }

    private int findFirstVacantPlatform(int[] platforms, int arrivalTime) {
        for (int i = 0; i < platforms.length; i++) {
            if (platforms[i] < arrivalTime) {
                return i;
            }
        }
        return -1;
    }

    // Time Complexity: O(NlogN) + O(N^2) where n is the number of trains, one pass for iterating through trains and another to find the first vacant platform for each train
    // Space Complexity: O(n) where n is the number of trains, we are using an array to keep track of the platforms
    private int bruteForce(int[] arr, int[] dep) {
        int n = arr.length;
        int[] platforms = new int[n];
        Arrays.fill(platforms, -1);
        Train[] trains = createTrains(arr, dep);
        // We need the sort the trains by arrival time so that we can process the trains in the order of their arrival time,
        // this way we can keep track of the platforms that are occupied and the platforms that are vacant at any given time.
        Arrays.sort(trains, (a, b) -> a.arrivalTime - b.arrivalTime);
        int maxPlatform = 0;
        for (Train train : trains) {
            int arrivalTime = train.arrivalTime;
            int departureTime = train.departureTime;
            int platFormIndex = findFirstVacantPlatform(platforms, arrivalTime);
            // If so there is no vacant platform for this train
            // Since the platforms array is equal to number of trains, of everything in the array is filled then it means there is no vacant platforms left
            // This case will only happen when the number of platforms needed is equal to the number of trains, so we can just return n in this case,
            // since we cannot have more than n platforms for n trains
            if (platFormIndex == -1) {
                return n;
            }
            // If the vacant platform is taken for the first time then we need to increase the maxPlatform count,
            // otherwise we are just replacing the departure time of the train that is leaving with the departure time of the train that is coming
            if (platforms[platFormIndex] < 0) {
                maxPlatform++;
            }
            platforms[platFormIndex] = departureTime;
            System.out.println("Arrival Time: " + arrivalTime + " Platforms: " + Arrays.toString(platforms));
        }
        return maxPlatform;
    }

    // Time Complexity: O(n log n) + O(n) where n is the number of trains, we are sorting the arrival and departure times
    // Space Complexity: O(1) since we are not using any extra space to store
    private int optimal(int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int platformCount = 0;
        int maxPlatform = 0;
        int arrivalIndex = 0;
        int departureIndex = 0;
        while (arrivalIndex < arr.length && departureIndex < dep.length) {
            if (arr[arrivalIndex] <= dep[departureIndex]) {
                platformCount++;
                arrivalIndex++;
            } else {
                platformCount--;
                departureIndex++;
            }
            maxPlatform = Math.max(maxPlatform, platformCount);
        }
        return maxPlatform;
    }

    public int minPlatform(int[] arr, int[] dep) {
        return bruteForce(arr, dep);
    }

    public static void main(String[] args) {
        MinimumPlatforms mp = new MinimumPlatforms();
        int[] arr = {2148, 2334, 338, 25, 2121, 2353, 1125, 2358, 1023};
        int[] dep = {2238, 2349, 1518, 939, 2147, 2355, 2233, 2359, 2200};
        int res = mp.minPlatform(arr, dep);
        System.out.println(res);
    }
}
