package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * <a href="https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1">BFS in Graph</a>
 * Given a connected undirected graph containing V vertices, represented by a 2-d adjacency list adj[][],
 * where each adj[i] represents the list of vertices connected to vertex i. Perform a Breadth First Search (BFS) traversal starting from vertex 0,
 * visiting vertices from left to right according to the given adjacency list, and return a list containing the BFS traversal of the graph.
 * Note: Do traverse in the same order as they are in the given adjacency list.
 */
public class BFS {
    // Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V) for the visited array and the queue, in the worst case we might have to visit all the vertices and add them to the queue
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        ArrayList<Integer> bfsTraversal = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        visited[0] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfsTraversal.add(node);
            ArrayList<Integer> neighbors = adj.get(node);
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        return bfsTraversal;
    }

    public static void main(String[] args) {
        BFS bfs = new BFS();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.get(0).add(2);
        adj.get(0).add(3);
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(2).add(0);
        adj.get(2).add(4);
        adj.get(3).add(0);
        adj.get(4).add(2);
        System.out.println(bfs.bfs(adj));
    }
}
