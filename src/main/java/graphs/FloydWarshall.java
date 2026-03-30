package graphs;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1">Floyd Warshall</a>
 * You are given a weighted directed graph, represented by an adjacency matrix, dist[][] of size n x n,
 * where dist[i][j] represents the weight of the edge from node i to node j.
 * If there is no direct edge, dist[i][j] is set to a large value (i.e., (int) 10e8) to represent infinity.
 * The graph may contain negative edge weights, but it does not contain any negative weight cycles.
 * Your task is to find the shortest distance between every pair of nodes i and j in the graph.
 * Note: Modify the distances for every pair in place.
 */
public class FloydWarshall {
    // Time Complexity: O(V * V * V)
    // Space Complexity: O(V)
    public void floydWarshall(int[][] dist) {
        // Code here
        int n = dist.length;
        for (int via = 0; via < n; via++) {
            for (int from = 0; from < n; from++) {
                if (from == via) continue;
                for (int to = 0; to < n; to++) {
                    if (dist[from][via] == 100000000 || dist[via][to] == 100000000) continue;
                    dist[from][to] = Math.min(dist[from][to], dist[from][via] + dist[via][to]);
                }
            }
        }
    }

    public static void main(String[] args) {
        FloydWarshall floydWarshall = new FloydWarshall();
//        int[][] dist = {{0, 4, (int) 10e8, 5, (int) 10e8}, {(int) 10e8, 0, 1, (int) 10e8, 6}, {2, (int) 10e8, 0, 3, (int) 10e8}, {(int) 10e8, (int) 10e8, 1, 0, 2}, {1, (int) 10e8, (int) 10e8, 4, 0}};
//        int[][] dist = {{0, -1, 2}, {1, 0, (int) 10e8}, {3, 1, 0}};
        int[][] dist = {{0, 8, 7, -3}, {1, 0, -1, 6}, {9, 5, 0, 5}, {100000000, 100000000, 100000000, 0}};
        floydWarshall.floydWarshall(dist);
        System.out.println(Arrays.deepToString(dist));
    }
}
