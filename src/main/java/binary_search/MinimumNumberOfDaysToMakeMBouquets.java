package binary_search;

/**
 * <a href="https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/">...</a>
 * Given an integer array bloomDay, an integer m and an integer k.
 * We need to make m bouquets. To make a bouquet, we need to use k adjacent flowers from the garden.
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
 * Return the minimum number of days you need to wait to be able to make m bouquets from the garden.
 * If it is impossible to make m bouquets return -1.
 */
public class MinimumNumberOfDaysToMakeMBouquets {
    private int getMinimum(int[] bloomDay) {
        int min = Integer.MAX_VALUE;
        for (int day : bloomDay) {
            if (day < min) {
                min = day;
            }
        }
        return min;
    }

    private int getMaximum(int[] bloomDay) {
        int max = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            if (day > max) {
                max = day;
            }
        }
        return max;
    }

    private int[] getMinAndMax(int[] bloomDay) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            if (day < min) {
                min = day;
            }
            if (day > max) {
                max = day;
            }
        }
        return new int[]{min, max};
    }

    private boolean canBouquetsBeMade(int[] bloomDay, int days, int m, int k) {
        int bouquetsMade = 0;
        int flowers = 0;
        for (int day : bloomDay) {
            if (day <= days) {
                // The flower has bloomed
                flowers++;
            } else {
                // The flower has not bloomed, reset the flower count
                // Since its floor division, we can directly do integer division
                int bouquetsFromCurrentFlowers = flowers / k;
                bouquetsMade += bouquetsFromCurrentFlowers;
                if (bouquetsMade >= m) {
                    return true;
                }
                flowers = 0;
            }
        }
        int bouquetsFromCurrentFlowers = flowers / k;
        bouquetsMade += bouquetsFromCurrentFlowers;

        return bouquetsMade >= m;
    }

    // Brute Force Approach - Linear Search
    // Time Complexity: O(n * d) where n is the number of flowers and d is the range of days
    // Space Complexity: O(1)
    private int bruteForce(int[] bloomDay, int m, int k) {
        int start = getMinimum(bloomDay);
        int end = getMaximum(bloomDay);
        for (int day = start; day <= end; day++) {
            if (canBouquetsBeMade(bloomDay, day, m, k)) {
                return day;
            }
        }
        return -1;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(n * log d) where n is the number of flowers and d is the range of days
    // Space Complexity: O(1)
    private int optimal(int[] bloomDay, int m, int k) {
        if(m * k > bloomDay.length) {
            return -1;
        }
        int[] minMax = getMinAndMax(bloomDay);
        int start = minMax[0];
        int end = minMax[1];
        int result = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (canBouquetsBeMade(bloomDay, mid, m, k)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        return optimal(bloomDay, m, k);
    }

    public static void main(String[] args) {
        MinimumNumberOfDaysToMakeMBouquets mndtmmb = new MinimumNumberOfDaysToMakeMBouquets();
        int[] bloomDay = {7, 7, 7, 7, 12, 7, 7};
        int m = 2;
        int k = 3;
        System.out.println("Minimum number of days to make " + m + " bouquets is: " + mndtmmb.minDays(bloomDay, m, k));
    }
}
