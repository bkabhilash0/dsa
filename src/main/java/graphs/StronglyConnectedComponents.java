package graphs;

import java.util.Deque;
import java.util.List;

/**
 * <h2> Kosaraju's Algorithm </h2>
 * <a href="https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1">Strongly Connected Components</a>
 * Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges.
 * The graph is represented as a 2D vector edges[][],
 * where each entry edges[i] = [u, v] denotes a direct edge from vertex u to v.
 * Find the number of strongly connected components in the graph.
 */
public class StronglyConnectedComponents {
    // Util Function to create an Adjacency List from the given edges
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

    // Util function to create an Adjacency List with reversed edges
    private List<List<Integer>> reverseEdges(int V, int[][] edges) {
        List<List<Integer>> list = new java.util.ArrayList<>();
        for (int i = 0; i < V; i++) {
            list.add(new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            list.get(edge[1]).add(edge[0]);
        }
        return list;
    }

    // DFS with a flag of if the node must be added to stack. The stack is filled bottom up
    private void dfs(int node, boolean[] visited, List<List<Integer>> adjList, Deque<Integer> stack, boolean addToStack) {
        visited[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, adjList, stack, addToStack);
            }
        }
        if (addToStack) stack.push(node);
    }

    // Function to find number of strongly connected components in the graph
    // Time Complexity: O(V + E)
    // Space Complexity: O(E + V) + O(E + V) + O(V) + O(V)
    public int kosaraju(int V, int[][] edges) {
        // code here
        List<List<Integer>> adjList = createList(V, edges);
        List<List<Integer>> reversedEdges = reverseEdges(V, edges);
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new java.util.ArrayDeque<>();
        // Do a DFS and push the nodes in the stack according to their finishing times
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, adjList, stack, true);
            }
        }

        // Now start popping out the stack and run dfs in the stack popping order on the reversed graph, each time we run a dfs, we will get a strongly connected component
        int countOfStronglyConnectedComponents = 0;
        visited = new boolean[V];
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                // Pass addToStack as false else the stack starts growing
                dfs(node, visited, reversedEdges, stack, false);
                countOfStronglyConnectedComponents++;
            }
        }
        return countOfStronglyConnectedComponents;
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {{0, 2}, {0, 3}, {1, 0}, {2, 1}, {3, 4}};
        StronglyConnectedComponents stronglyConnectedComponents = new StronglyConnectedComponents();
        System.out.println(stronglyConnectedComponents.kosaraju(V, edges)); // Output: 3
    }
}
