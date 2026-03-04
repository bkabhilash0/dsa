package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1">Directed Cyclic Graph</a>
 * Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.
 * The graph is represented as a 2D vector edges[][], where each entry edges[i] = [u, v] denotes an edge from vertex u to v.
 */
public class DirectedCyclicGraph {
    private List<List<Integer>> createList(int V, int[][] edges) {
        List<List<Integer>> list = new java.util.ArrayList<>();
        for (int i = 0; i < V; i++) {
            list.add(new java.util.ArrayList<>());
        }

        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
        }
        return list;
    }

    private boolean isCyclicUsingDFS(int node, List<List<Integer>> adj, boolean[] visited, boolean[] path) {
        visited[node] = true;
        path[node] = true;
        List<Integer> neighbors = adj.get(node);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                if (isCyclicUsingDFS(neighbor, adj, visited, path)) return true;
            } else if (path[neighbor]) {
                return true;
            }
        }

        // Remove from the path since we are backtracking and we are not in the current path anymore
        path[node] = false;
        return false;
    }

    // Time Complexity: O(V) + O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V + E) for adj + O(V) for the visited and path arrays and the recursion stack in the worst case when the graph forms a linear chain
    public boolean isCyclic(int V, int[][] edges) {
        // code here
        List<List<Integer>> adj = createList(V, edges);
        boolean[] visited = new boolean[V];
        boolean[] path = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (visited[i]) continue;
            if (isCyclicUsingDFS(i, adj, visited, path)) return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
