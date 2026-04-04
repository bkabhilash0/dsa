package graphs;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/minimum-spanning-tree-kruskals-algorithm/1">Minimum Spanning Tree - Kruskal's Algorithm</a>
 * Given a weighted, undirected, and connected graph with V vertices and E edges,
 * the task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST)
 * of the graph using Kruskal's Algorithm.
 * The graph is represented as an edge list edges[][],
 * where edges[i] = [u, v, w] denotes an undirected edge between u and v with weight w.
 */
public class KruskalAlgorithm {
    private static int findParent(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = findParent(parent, parent[i]);
    }

    private static void union(int[] parent, int[] rank, int i, int j) {
        int rootI = findParent(parent, i);
        int rootJ = findParent(parent, j);
        if (rootI == rootJ) return;
        if (rank[rootI] < rank[rootJ]) parent[rootI] = rootJ;
        else if (rank[rootJ] < rank[rootI]) parent[rootJ] = rootI;
        else {
            parent[rootI] = rootJ;
            rank[rootJ]++;
        }
    }

    // Time Complexity: O(ElogE) O(V) + O(E * α(V))
    // Space Complexity: O(V) + O(V)
    static int kruskalsMST(int V, int[][] edges) {
        // Sort the Edges based on their weights
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        // Create Parent and rank arrays
        int[] parent = new int[V];
        int[] rank = new int[V];
        // Initialize the parent array
        for (int i = 0; i < V; i++) parent[i] = i;
        int totalWeight = 0;
        // Now Start Connecting
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // If the Edge is already present in the same connected component then ignore
            // It means the edge is already present between these nodes in a cheap weight as we are
            // performing the connection in increasing order of the weights
            if (findParent(parent, from) == findParent(parent, to)) continue;
            // Now Connect the edge as the edge is not already present and add to result
            union(parent, rank, from, to);
            totalWeight += weight;
        }
        return totalWeight;
    }

    public static void main(String[] args) {
        System.out.println(kruskalsMST(3, new int[][]{{0, 1, 5}, {1, 2, 3}, {0, 2, 1}}));
    }
}
