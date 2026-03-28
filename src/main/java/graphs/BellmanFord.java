package graphs;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1">Bellman-Ford</a>
 * Given an weighted graph with V vertices numbered from 0 to V-1 and E edges,
 * represented by a 2d array edges[][], where edges[i] = [u, v, w] represents a
 * direct edge from node u to v having w edge weight.
 * You are also given a source vertex src.
 * Your task is to compute the shortest distances from the source to all other vertices.
 * If a vertex is unreachable from the source, its distance should be marked as 108.
 * Additionally, if the graph contains a negative weight cycle, return [-1]
 * to indicate that shortest paths cannot be reliably computed.
 */
public class BellmanFord {
    // Time Complexity: O((V - 1)*V) = O(V^2)
    // Space Complexity: O(V)
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // code here
        int[] distances = new int[V];
        Arrays.fill(distances, (int) 1e8);
        distances[src] = 0;
        boolean isChanged = true;
        // Ideally this has to run V-1 times but we can break early if no changes are made in an iteration
        // If the distance is getting updated in the Vth loop then there exists a negative cycle so return [-1]
        for (int i = 1; i <= V; i++) {
            isChanged = false;
            for (int[] edge : edges) {
                int start = edge[0];
                int end = edge[1];
                int weight = edge[2];
                // Can be clubbed to single if condition but keeping it seperate for Readability
                if (distances[start] == (int) 1e8) continue;
                int newDistance = distances[start] + weight;
                if (newDistance < distances[end]) {
                    if (i == V) {
                        // Then a cycle exists
                        return new int[]{-1};
                    }
                    isChanged = true;
                    distances[end] = newDistance;
                }
            }
            // If the iteration didn't find any new shorter distance then we can early stop the iterations
            if (!isChanged) break;
        }
        return distances;
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 3, 2}, {4, 3, -1}, {2, 4, 1}, {1, 2, 1}, {0, 1, 5}};
        int V = 5;
        int src = 0;
        BellmanFord bellmanFord = new BellmanFord();
        int[] result = bellmanFord.bellmanFord(V, edges, src);
        System.out.println(Arrays.toString(result)); // [0, 5, 6, 6, 7]
    }
}
