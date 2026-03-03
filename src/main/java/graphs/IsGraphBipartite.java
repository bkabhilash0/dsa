package graphs;

import java.util.Arrays;
import java.util.List;

/**
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B
 * such that every edge in the graph connects a node in set A and a node in set B.
 * Return true if and only if it is bipartite.
 */
public class IsGraphBipartite {
    private boolean dfs(int node, int color, int[] colors, int[][] graph) {
        colors[node] = color;
        int[] neighbors = graph[node];
        for (int neighbor : neighbors) {
            if (colors[neighbor] == color) return false;
            if (colors[neighbor] == -1) {
                if (!dfs(neighbor, 1 - color, colors, graph)) return false;
            }
        }
        return true;
    }

    // Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V) for the colors array and the recursion stack in the worst case when the graph is a complete graph
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        for (int i = 0; i < graph.length; i++) {
            if(colors[i] != -1) continue;
            boolean res = dfs(i, 1, colors, graph);
            if (!res) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        IsGraphBipartite isGraphBipartite = new IsGraphBipartite();
        int[][] graph = new int[][]{
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };
        System.out.println(isGraphBipartite.isBipartite(graph));
    }
}
