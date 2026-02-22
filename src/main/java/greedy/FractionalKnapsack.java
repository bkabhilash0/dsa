package greedy;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1">Fractional Knapsack</a>
 * Given two arrays, val[] and wt[] , representing the values and weights of items,
 * and an integer capacity representing the maximum weight a knapsack can hold,
 * determine the maximum total value that can be achieved by putting items in the knapsack.
 * You are allowed to break items into fractions if necessary.
 * Return the maximum value as a double, rounded to 6 decimal places.
 */
public class FractionalKnapsack {
    private static class Item {
        int value;
        int weight;
        double ratio;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
    }

    private Item[] createItems(int[] val, int[] wt) {
        Item[] items = new Item[val.length];
        for (int i = 0; i < val.length; i++) {
            items[i] = new Item(val[i], wt[i]);
        }
        return items;
    }

    // Time Complex: O(n log n) where n is the number of items, we are sorting the items based on their value to weight ratio
    // Space Complex: O(n) where n is the number of items, we are creating an array of items to store the value, weight and ratio of each item
    // Can also be done in O(1) space by sorting the val and wt arrays together based on the ratio, but that would make the code more complex and less readable.
    private double optimal(int[] val, int[] wt, int capacity) {
        Item[] items = createItems(val, wt);
        Arrays.sort(items, (item1, item2) -> Double.compare(item2.ratio, item1.ratio));
        double totalValue = 0;
        for (Item item : items) {
            if (capacity > item.weight) {
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // Since the capacity is less than the weight of the item, we can only take a fraction of the item.
                totalValue += (item.ratio * capacity);
                // The bag cannot accommodate any more items, so we break out of the loop.
                break;
            }
        }
        return totalValue;
    }

    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        // code here
        return optimal(val, wt, capacity);
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int capacity = 50;
        FractionalKnapsack fk = new FractionalKnapsack();
        System.out.println(fk.fractionalKnapsack(val, wt, capacity));
    }
}
