package graphs;

import java.util.*;

/**
 * You are given an undirected graph with V vertices numbered from 0 to V-1 and E edges, represented as a 2D array edges[][],
 * where each element edges[i] = [u, v] represents an undirected edge between vertices u and v.
 * Your task is to find the shortest path distance from a given source vertex src to all other vertices in the graph.
 * If a vertex is not reachable from the source, return -1 for that vertex.
 * Note: All edges have unit weight (1).
 */
public class ShortestPathInUndirectedGraph {
    private ArrayList<ArrayList<Integer>> createAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        return adjList;
    }

    // Time Complexity: O(E) + O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V + E) for the adjacency list + O(V) for the queue and distances array
    public int[] shortestPath(int V, int[][] edges, int src) {
        // code here
        ArrayList<ArrayList<Integer>> adjList = createAdjList(V, edges);
        Queue<Integer> queue = new ArrayDeque<>();
        int[] distances = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;
        queue.offer(src);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentDistance = distances[node];
            if (currentDistance == Integer.MAX_VALUE) continue;
            for (int neighbor : adjList.get(node)) {
                // If this new path is < the previously known path to the neighbor, then update the distance to the neighbor
                if (currentDistance + 1 < distances[neighbor]) {
                    distances[neighbor] = currentDistance + 1;
                    queue.offer(neighbor);
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                distances[i] = -1;
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        ShortestPathInUndirectedGraph spu = new ShortestPathInUndirectedGraph();
        int V = 9;
        int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {3, 4}, {4, 5}, {2, 6}, {5, 6}, {6, 7}, {6, 8}, {7, 8}};
        int src = 0;
        int[] result = spu.shortestPath(V, edges, src);
        System.out.println(Arrays.toString(result));
    }
}
