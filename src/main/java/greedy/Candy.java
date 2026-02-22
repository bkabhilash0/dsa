package greedy;

/**
 * <a href="https://leetcode.com/problems/candy/description/">Candy</a>
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 */
public class Candy {
    // Time Complex: O(3n) where n is the length of the ratings array, we are doing two passes through the array and then a final pass to calculate the total candies
    // Space Complex: O(2n) where n is the length of the ratings array, we are using two additional arrays to store the candies for the left and right passes
    private int bruteForce(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        int candies = 0;
        // Initialize the left and right arrays with 1, since each child must have at least one candy.
        left[0] = 1;
        right[ratings.length - 1] = 1;

        // Do the left to right pass, considering only the left neighbour
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Do a right to left pass, considering only the right neighbour
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // Take the max of each child from the left and right arrays, since we need to satisfy both the left and right neighbour conditions.
        for (int i = 0; i < ratings.length; i++) {
            candies += Math.max(left[i], right[i]);
        }
        return candies;
    }

    // Time complexity : O(3n) where n is the length of the ratings array, we are doing 2 pass through the array and then a final pass to calculate the total candies
    // Space complexity : O(n) where n is the length of the ratings array
    private int better(int[] ratings) {
        int[] candies = new int[ratings.length];
        int totalCandies = 0;
        candies[0] = 1;

        // Do the left to right pass, considering only the left neighbour
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            // Do the max comparison on the fly when we update the right comparison, since we need to satisfy both the left and right neighbour conditions.
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i + 1] + 1, candies[i]);
            } else {
                candies[i] = Math.max(1, candies[i]);
            }
        }

        // Take the max of each child from the left and right arrays, since we need to satisfy both the left and right neighbour conditions.
        for (int candy : candies) {
            totalCandies += candy;
        }
        return totalCandies;
    }

    // Time complexity : O(n) where n is the length of the ratings array, we are doing one pass through the ratings
    // Space complexity : O(1) since we are not using any additional space to store the candies, we are calculating the candies on the fly.
    private int optimal(int[] ratings) {
        int n = ratings.length;
        int peak = 0;
        int valley = 0;
        // Initialize the candies to n, since each child must have at least one candy.
        int candies = n;

        int i = 1;
        while (i < n) {
            // If the current child has same rating as the previous then we shall stick with 1 and the 1 is already initialized
            if (ratings[i] == ratings[i - 1]) {
                i++;
                continue;
            }
            peak = 0;
            // Climbing peak
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak++;
                i++;
                candies += peak;
            }

            valley = 0;
            while (i < n && ratings[i] < ratings[i - 1]) {
                valley++;
                i++;
                candies += valley;
            }

            // Now reduces the overlap of the peak and valley; since the peak and valley are both counting the same child,
            // we need to reduce the overlap by the minimum of the peak and valley, since we need to satisfy both the left and right neighbor conditions.
            candies -= Math.min(peak, valley);
        }

        return candies;
    }

    public int candy(int[] ratings) {
        return better(ratings);
    }

    public static void main(String[] args) {
        Candy candy = new Candy();
        System.out.println(candy.candy(new int[]{1, 0, 2})); // 5
        System.out.println(candy.candy(new int[]{1, 2, 2})); // 4
    }
}

