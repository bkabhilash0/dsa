package sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right
 * until you cannot pick more fruit.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 * Example 1:
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 * Example 2:
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2]. If we had started at the first tree, we would only pick from trees [0,1].
 * Example 3:
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2]. If we had started at the first tree, we would only pick from trees [1,2].
 */
public class FruitsInBasket {
    // Time Complex: O(n^2)
    // Space Complex: O(n) in the worst case when all fruits are unique, we will have to store all fruits in the set
    private int bruteForce(int[] fruits) {
        int maxFruits = 0;
        Set<Integer> map = new HashSet<>();
        for (int i = 0; i < fruits.length; i++) {
            map.add(fruits[i]);
            for (int j = i + 1; j < fruits.length; j++) {
                // Check if we have 2 unique fruits in the map and the current fruit is not in the map, then we cannot pick more fruits
                if (map.size() == 2 && !map.contains(fruits[j])) {
                    break;
                }
                // If the map has less than 2 unique fruits or the current fruit is not in the map, we can pick the fruit and add it to the map if it's not already there
                if (map.size() < 2 || !map.contains(fruits[j])) {
                    map.add(fruits[j]);
                }
                maxFruits = Math.max(maxFruits, j - i + 1);
            }
            map.clear();
        }
        return maxFruits;
    }

    // Time Complex: O(n)
    // Space Complex: O(n) in the worst case when all fruits are unique, we will have to store all fruits in the map
    private int better(int[] fruits) {
        int l = 0, r = 0;
        int maxFruits = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (r < fruits.length) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
            maxFruits = Math.max(maxFruits, r - l + 1);
            r++;
        }
        return maxFruits;
    }

    // Time Complex: O(n)
    // Space Complex: O(n) in the worst case when all fruits are unique, we will have to store all fruits in the map
    private int optimal(int[] fruits) {
        int l = 0, r = 0;
        int maxFruits = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (r < fruits.length) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            if (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
            maxFruits = Math.max(maxFruits, r - l + 1);
            r++;
        }
        return maxFruits;
    }

    // Time Complex: O(n)
    // Space Complex: O(1) as we are only storing 2 fruits and their counts
    private int moreOptimal(int[] fruits) {
        int l = 0, r = 0;
        int maxFruits = 0;
        int fruit1 = -1, fruit2 = -1;
        int fruit1Count = 0;
        int count = 0;
        for (int fruit : fruits) {
            if (fruit == fruit1 || fruit == fruit2) {
                count++;
            } else {
                count = fruit1Count + 1;
            }

            if (fruit == fruit1) {
                fruit1Count++;
            } else {
                fruit1Count = 1;
                fruit2 = fruit1;
                fruit1 = fruit;
            }
            maxFruits = Math.max(maxFruits, count);
        }
        return maxFruits;
    }

    public int totalFruit(int[] fruits) {
        return moreOptimal(fruits);
    }

    public static void main(String[] args) {
        FruitsInBasket fruitsInBasket = new FruitsInBasket();
        int[] fruits = {1, 2, 3, 2, 2};
        System.out.println(fruitsInBasket.totalFruit(fruits));
    }
}
