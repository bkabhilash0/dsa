package binary_search;

/**
 * <a href="https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/">...</a>
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 */
public class CapacityToShipPackagesWithinDDays {
    private int[] getMinAndMaxLimit(int[] weights) {
        int min = Integer.MIN_VALUE;
        int max = 0;
        for (int weight : weights) {
            if (weight > min) {
                min = weight;
            }
            max += weight;
        }
        return new int[]{min, max};
    }

    private boolean canShipWithinDDays(int[] weights, int days, int limit) {
        int daysNeeded = 1;
        int currentLoad = 0;
        for (int weight : weights) {
            if ((currentLoad + weight) > limit) {
                daysNeeded++;
                currentLoad = 0;
            }
            currentLoad += weight;
        }

        return daysNeeded <= days;
    }

    // Brute Force Approach - Linear Search
    // Time Complexity: O(n * m) where n is the number of weights and m is the sum of all weights
    // Space Complexity: O(1)
    private int bruteForce(int[] weights, int days) {
        // We start from min weight to sum of all weights, we start from min weight because
        // the ship capacity cannot be less than the minimum weight package
        int[] limits = getMinAndMaxLimit(weights);
        int minLimit = limits[0];
        int maxLimit = limits[1];
        for (int i = minLimit; i <= maxLimit; i++) {
            if (canShipWithinDDays(weights, days, i)) {
                return i;
            }
        }
        return -1;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(n * log m) where n is the number of weights and m is the sum of all weights
    // Space Complexity: O(1)
    private int optimal(int[] weights, int days) {
        int[] minMax = getMinAndMaxLimit(weights);
        int start = minMax[0], end = minMax[1];

        while(start <= end){
            int mid = (start + end) / 2;
            if(canShipWithinDDays(weights, days, mid)){
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public int shipWithinDays(int[] weights, int days) {
        return optimal(weights, days);
    }

    public static void main(String[] args) {
        CapacityToShipPackagesWithinDDays csp = new CapacityToShipPackagesWithinDDays();
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;
        System.out.println("Weights: " + java.util.Arrays.toString(weights));
        System.out.println("\nD: " + D);
        int result = csp.shipWithinDays(weights, D);
        System.out.println("Result: " + result);
    }
}
