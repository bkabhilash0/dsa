package graphs;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/number-of-provinces/description/">Number of Provinces</a>
 * There are n cities. Some of them are connected, while some are not.
 * If city a is connected directly with city b, and city b
 * is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1
 * if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 */
public class NumberOfProvincesUsingDSU {
    private int findParent(int[] parents, int node) {
        if (parents[node] == node) {
            return node;
        }
        return parents[node] = findParent(parents, parents[node]);
    }

    // Return true when we group two components
    private boolean union(int[] parents, int[] rank, int node1, int node2) {
        int parent1 = findParent(parents, node1);
        int parent2 = findParent(parents, node2);
        if (parent1 == parent2) {
            return false;
        }
        if (rank[parent1] < rank[parent2]) {
            parents[parent1] = parent2;
        } else if (rank[parent2] < rank[parent1]) {
            parents[parent2] = parent1;
        } else {
            parents[parent2] = parent1;
            rank[parent1]++;
        }
        return true;
    }

    // Time Complexity: O(N) + O(N * M * alpha)
    // Space Complexity: O(2N) for the parents and rank arrays
    public int findCircleNum(int[][] isConnected) {
        int[] parents = new int[isConnected.length];
        int[] ranks = new int[isConnected.length];
        // Initially since there are no edges connected in DSU we could say there are n connected components
        int count = isConnected.length;

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            ranks[i] = 0;
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                // The union returns true if 2 connected go connected and have become a single one
                // If it returns true we have to decrement the count, by this one loop of counting the unique parents can be avoided
                if (isConnected[i][j] == 1 && i != j && union(parents, ranks, i, j)) {
                    count--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfProvincesUsingDSU numberOfProvincesUsingDSU = new NumberOfProvincesUsingDSU();
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int result = numberOfProvincesUsingDSU.findCircleNum(isConnected);
        System.out.println("Number of Provinces: " + result);
    }
}
