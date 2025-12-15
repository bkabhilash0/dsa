package arrays;

import java.util.Arrays;

/*
https://www.geeksforgeeks.org/dsa/leaders-in-an-array/
* Given an array arr[] of size n, the task is to find all the Leaders in the array. An element is a Leader if it is greater than or equal to all the elements to its right side.
* Note: The rightmost element is always a leader.
* */
public class LeadersInAnArray {
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private int[] bruteForce(int[] arr) {
        int[] leaders = new int[arr.length];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) {
                leaders[index++] = arr[i];
            }
        }
        return Arrays.copyOf(leaders, index);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private int[] optimal(int[] arr) {
        int[] leaders = new int[arr.length];
        leaders[0] = arr[arr.length - 1];
        int index = 1;
        int currentMax = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] >= currentMax) {
                currentMax = arr[i];
                leaders[index++] = currentMax;
            }
        }
        // Reduce the size of the leaders array to the number of leaders found
        leaders = Arrays.copyOf(leaders, index);
        return leaders;
    }

    public int[] getLeaders(int[] arr) {
        return optimal(arr);
    }

    public static void main(String[] args) {
        LeadersInAnArray lia = new LeadersInAnArray();
        int[] arr = {16, 17, 4, 3, 5, 2};
        int[] leaders = lia.getLeaders(arr);
        System.out.print("Leaders in the array are: " + Arrays.toString(leaders));
    }
}
