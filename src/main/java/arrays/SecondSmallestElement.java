package arrays;

import java.util.Arrays;
import java.util.HashSet;

/*
 * Find the Second Smallest Element in an array without sorting.
 * Example:
 * Input: arr = [5, 3, 1, 4, 2]
 * Output: 2
 */
public class SecondSmallestElement {
    // Sort the array and return the second element
    // Wont work if there are duplicates of the smallest element
    // Put into set to remove duplicates
    // Then convert back to array, then sort and return second element
    // Putting into set can re-order the elements espacilly with unordered sets that's why we need to sort again
    // Time Complexity: O(nlog(n))
    // Space Complexity: O(1)
    private int bruteForce(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        Integer[] uniqueArr = set.toArray(new Integer[0]);
        Arrays.sort(arr);
        if (uniqueArr.length < 2) {
            return -1; // No second-smallest element
        } else {
            return uniqueArr[1];
        }
    }

    // Use 2 loops to find smallest and second smallest
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int bruteForceOptimised(int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int num : arr) {
            smallest = Math.min(num, smallest);
        }

        for (int num : arr) {
            if (num != smallest) {
                secondSmallest = Math.min(num, secondSmallest);
            }
        }

        return secondSmallest;
    }

    private int optimal(int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num > smallest && num < secondSmallest) {
                secondSmallest = num;
            }
        }

        return secondSmallest;
    }

    public int secondSmallest(int[] arr) {
        return optimal(arr);
    }

    public static void main(String[] args) {
        SecondSmallestElement sse = new SecondSmallestElement();
        int[] arr = {5, 3, 1, 4, 2, 2, 1};
        System.out.println("Input array: " + Arrays.toString(arr));
        int result = sse.secondSmallest(arr);
        System.out.println("Second smallest element: " + result);
    }
}
