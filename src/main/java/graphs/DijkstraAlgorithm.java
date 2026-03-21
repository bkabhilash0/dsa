package graphs;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <a href="https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1">Dijkstra Algorithm</a>
 * Given an undirected, weighted graph with V vertices numbered from 0 to V-1 and E edges,
 * represented by 2d array edges[][], where edges[i]=[u, v, w] represents the edge between
 * the nodes u and v having w edge weight.
 * You have to find the shortest distance of all the vertices from the source vertex src,
 * and return an array of integers where the ith element denotes the shortest distance between ith node and source vertex src.
 * Note: The Graph is connected and doesn't contain any negative weight edge.
 * It is guaranteed that all the shortest distance will fit in a 32-bit integer.
 */
public class DijkstraAlgorithm {
    static class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("[node=%d, weight=%d]", node, weight);
        }
    }

    private List<List<Edge>> createAdjList(int V, int[][] edges) {
        List<List<Edge>> adjMatrix = new java.util.ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjMatrix.add(new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            adjMatrix.get(edge[0]).add(new Edge(edge[1], edge[2]));
            adjMatrix.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }
        return adjMatrix;
    }

    // Time Complexity: O(E*logV) + O(E+V)
    // Space Complexity: O(V+E) + O(V) + O(V)
    private int[] executeDijkstra(int V, List<List<Edge>> adjMatrix, int src) {
        int[] shortestDistance = new int[V];
        Arrays.fill(shortestDistance, Integer.MAX_VALUE);
        shortestDistance[src] = 0;

        // Min Heap - (dist, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[1];
            int dist = current[0];

            // We would have already found a shorter distance to reach this node
            // Some stale entries in the queue would trigger this node to re-execute this
            // We shall avoid this execution by checking if we are travelling through this node in a longer path
            if(dist > shortestDistance[node]) continue;

            List<Edge> neighbors = adjMatrix.get(node);
            for (Edge neighbor : neighbors) {
                int newDistance = dist + neighbor.weight;
                if (newDistance < shortestDistance[neighbor.node]) {
                    shortestDistance[neighbor.node] = newDistance;
                    pq.offer(new int[]{newDistance, neighbor.node});
                }
            }
        }

        return shortestDistance;
    }

    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        List<List<Edge>> adjMatrix = createAdjList(V, edges);
        return executeDijkstra(V, adjMatrix, src);
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1, 4},
                {0, 2, 8},
                {1, 4, 6},
                {2, 3, 2},
                {3, 4, 10},
        };
        int v = 5;
        int src = 0;
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        int[] shortestDistance = dijkstraAlgorithm.dijkstra(v, edges, src);
        System.out.println(Arrays.toString(shortestDistance));
    }
}
