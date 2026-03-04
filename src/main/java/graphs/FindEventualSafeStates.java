package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.com/problems/find-eventual-safe-states/">Find Eventual Safe States</a>
 * There is a directed graph of n nodes with each node labeled from 0 to n - 1.
 * The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i,
 * meaning there is an edge from node i to each node in graph[i].
 * A node is a terminal node if there are no outgoing edges.
 * A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 */
public class FindEventualSafeStates {
    private boolean dfs(int node, int[] visited, int[] path, int[] safeNodes, int[][] graph) {
        visited[node] = 1;
        path[node] = 1;

        int[] neighbors = graph[node];
        for (int neighbor : neighbors) {
            if (visited[neighbor] != 1) {
                if (dfs(neighbor, visited, path, safeNodes, graph)) {
                    return true;
                }
            } else if (path[neighbor] == 1) {
                return true;
            }
        }
        safeNodes[node] = 1;
        path[node] = 0;
        return false;
    }

    // Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V + E) for the graph + O(V) for the visited, path and safeNodes arrays and the recursion stack in the worst case when the graph forms a linear chain
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int[] safeNodes = new int[graph.length];
        int[] visited = new int[graph.length];
        int[] path = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 1) continue;
            dfs(i, visited, path, safeNodes, graph);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < safeNodes.length; i++) {
            if (safeNodes[i] == 1) res.add(i);
        }
        return res;
        // This is slower creating streams and boxing the integers, we can just loop through the array and add the safe nodes to the result list
//        return new ArrayList<>(IntStream.range(0, safeNodes.length).filter(i -> safeNodes[i] == 1).boxed().toList());
    }

    public static void main(String[] args) {
        FindEventualSafeStates findEventualSafeStates = new FindEventualSafeStates();
        int[][] graph = new int[][]{
                {1, 2},
                {2, 3},
                {5},
                {0},
                {5},
                {},
                {}
        };
        System.out.println(findEventualSafeStates.eventualSafeNodes(graph));
    }
}

