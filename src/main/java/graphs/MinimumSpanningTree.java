package graphs;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <a href="https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1">Minimum Spanning Tree</a>
 * Given a weighted, undirected, and connected graph with V vertices and E edges,
 * your task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph.
 * The graph is provided as a list of edges, where each edge is represented as [u, v, w],
 * indicating an edge between vertex u and vertex v with edge weight w.
 */
public class MinimumSpanningTree {
    static class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    // Time Complexity: O(V) + O(E)
    // Space Complexity: O(V) + O(E)
    private List<List<Edge>> createList(int V, int[][] edges) {
        List<List<Edge>> list = new java.util.ArrayList<>();
        for (int i = 0; i < V; i++) {
            list.add(new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            list.get(edge[0]).add(new Edge(edge[1], edge[2]));
            list.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }
        return list;
    }

    // Time Complexity: O(ElogV)
    // Space Complexity: O(E+V) + O(V) + O(V)
    private int execute(int V, int[][] edges) {
        List<List<Edge>> adjList = createList(V, edges);
        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        int sum = 0;
        pq.offer(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (visited[current.node]) continue;
            visited[current.node] = true;
            sum += current.weight;
            for (Edge neighbor : adjList.get(current.node)) {
                if (!visited[neighbor.node]) pq.offer(neighbor);
            }
        }
        return sum;
    }

    public int spanningTree(int V, int[][] edges) {
        // code here
        return execute(V, edges);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 5}, {1, 2, 3}, {0, 2, 1}};
        int V = 5;
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree();
        int result = minimumSpanningTree.spanningTree(V, edges);
        System.out.println(result); // 7
    }
}
