package graphs;

import java.util.ArrayList;
import java.util.Stack;

/**
 * <a href="https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1">DFS in Graph</a>
 * Given a connected undirected graph containing V vertices, represented by a 2-d adjacency list adj[][], where each adj[i] represents the
 * list of vertices connected to vertex i. Perform a Depth First Search (DFS) traversal starting from vertex 0,
 * visiting vertices from left to right according to the given adjacency list, and return a list containing the DFS traversal of the graph.
 * Note: Do traverse in the same order as they are in the given adjacency list.
 */
public class DFS {
    // Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V) for the visited array and the recursion stack, in the worst case we might have to visit all the vertices and
    // the maximum depth of the recursion will be equal to the number of vertices in the graph
    private void recursiveDFS(ArrayList<ArrayList<Integer>> adj, int node, boolean[] visited, ArrayList<Integer> res) {
        visited[node] = true;
        res.add(node);
        ArrayList<Integer> neighbors = adj.get(node);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                recursiveDFS(adj, neighbor, visited, res);
            }
        }
    }

    // Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V) for the visited array and the stack, in the worst case we might have to visit all the vertices and add them to the stack
    private ArrayList<Integer> iterativeDFS(ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[adj.size()];
        ArrayList<Integer> res = new ArrayList<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if(visited[node] == 1) continue;
            visited[node] = 1;
            res.add(node);
            ArrayList<Integer> neighbors = adj.get(node);
            // We do it from the reverse to maintain the order of the neighbors as they are in the given adjacency list, since stack is LIFO
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (visited[neighbor] == 0) {
                    stack.push(neighbor);
                }
            }
        }
        return res;
    }

    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
//        ArrayList<Integer> res = new ArrayList<>();
//        recursiveDFS(adj, 0, new boolean[adj.size()], res);
//        return res;
        return iterativeDFS(adj);
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>());
        adjList.get(0).add(2);
        adjList.get(0).add(3);
        adjList.get(0).add(1);
        adjList.get(1).add(0);
        adjList.get(2).add(0);
        adjList.get(2).add(4);
        adjList.get(3).add(0);
        adjList.get(4).add(2);
        System.out.println(dfs.dfs(adjList));
    }
}
