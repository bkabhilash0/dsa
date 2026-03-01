package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1">Undirected Graph Cycle</a>
 * Given an undirected graph with V vertices and E edges, represented as a 2D vector edges[][],
 * where each entry edges[i] = [u, v] denotes an edge between vertices u and v,
 * determine whether the graph contains a cycle or not.
 * Note: The graph can have multiple component.
 */
public class UndirectedGraphCycle {
    private boolean bfs(int node, List<List<Integer>> adjList, boolean[] visited) {
        // Structure of the array is {currentNode, parentNode}
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{node, -1}); // We are adding the parent node in the queue to keep track of the parent node while traversing the graph
        visited[node] = true;
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            List<Integer> neighbors = adjList.get(element[0]);
            int parentNode = element[1];
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new int[]{neighbor, element[0]});
                } else if (neighbor != parentNode) {
                    // If the neighbor is visited and it is not the parent node, then we have found a cycle in the graph
                    return true;
                }
            }
        }
        return false;
    }

    private List<List<Integer>> createList(int V, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            list.get(node1).add(node2);
            list.get(node2).add(node1);
        }
        return list;
    }

    // Time Complexity: O(E) + O(V + 2E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V) for the visited array and the queue in the worst case when the graph is a complete graph
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        // Since there can be connected components, we have to loop through all the vertices and check for cycle in each component
        boolean[] visited = new boolean[V];
        List<List<Integer>> adjList = createList(V, edges);
        for (int i = 0; i < V; i++) {
            if (visited[i]) continue;
            if (bfs(i, adjList, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        UndirectedGraphCycle ugc = new UndirectedGraphCycle();
        int V = 5;
        int[][] edges = new int[][]{
                {0, 1},
                {1, 2},
                {2, 0},
                {1, 3},
                {3, 4}
        };
        boolean res = ugc.isCycle(V, edges);
        System.out.println(res);
    }
}
