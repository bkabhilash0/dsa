package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <a href="https://www.geeksforgeeks.org/problems/topological-sort/1">Topological Sort</a>
 * Given a Directed Acyclic Graph (DAG) of V (0 to V-1) vertices and E edges represented as a 2D list of edges[][],
 * where each entry edges[i] = [u, v] denotes a directed edge u -> v. Return the topological sort for the given graph.
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u -> v,
 * vertex u comes before v in the ordering.
 * Note: As there are multiple Topological orders possible, you may return any of them.
 * If your returned Topological sort is correct then the output will be true else false.
 */
public class TopologicalSort {
    private ArrayList<ArrayList<Integer>> createList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            list.get(node1).add(node2);
        }
        return list;
    }

    private boolean executeTopoSortUsingDFS(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, boolean[] path, Stack<Integer> topoSort) {
        visited[node] = true;
        path[node] = true;

        for (int neighbour : adjList.get(node)) {
            if (!visited[neighbour]) {
                // Check if we find a cycle in the graph, then we cannot perform topological sort and we can return an empty list
                if (executeTopoSortUsingDFS(neighbour, adjList, visited, path, topoSort)) {
                    return true;
                    // Check if we are visiting a node that is already in the current path,
                    // then we have found a cycle in the graph and we cannot perform topological sort and we can return an empty list
                }
            } else if (path[neighbour]) {
                return true;
            }
        }

        path[node] = false;
        topoSort.push(node);
        return false;
    }

    // Time Complexity: O(V) + O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V + E) for adjList + O(V) for the stack, visited and path arrays and the recursion stack in the worst case when the graph forms a linear chain
    private ArrayList<Integer> topoSortUsingDFS(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = createList(V, edges);
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        boolean[] path = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (visited[i]) continue;
            if (executeTopoSortUsingDFS(i, adjList, visited, path, stack)) {
                // If we find a cycle in the graph, then we cannot perform topological sort and we can return an empty list
                return new ArrayList<>();
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    // Time Complexity: O(V) + O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V + E) for adjList + O(V) for the queue and the indegree array in the worst case when the graph is a complete graph
    private ArrayList<Integer> topoSortUsingKahn(int V, int[][] edges) {
        Queue<Integer> queue = new LinkedList<>();
        int[] inDeg = new int[V];
        ArrayList<ArrayList<Integer>> adjList = createList(V, edges);
        ArrayList<Integer> res = new ArrayList<>();

        // Find the indegree of each node
        for (int i = 0; i < V; i++) {
            for (int neighbour : adjList.get(i)) {
                inDeg[neighbour]++;
            }
        }

        // Find the nodes with indegree 0 and add them to the queue
        for (int i = 0; i < V; i++) {
            if (inDeg[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            res.add(node);
            ArrayList<Integer> neighbors = adjList.get(node);
            for (int neighbor : neighbors) {
                inDeg[neighbor]--;
                // We do not need the visited array as the indegree logic will take care of that
                if (inDeg[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return res;
    }

    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        return topoSortUsingKahn(V, edges);
    }

    public static void main(String[] args) {
        TopologicalSort ts = new TopologicalSort();
        int V = 6;
        int[][] edges = new int[][]{
                {1, 3}, {2, 3}, {4, 1}, {4, 0}, {5, 0}, {5, 2}
        };
        ArrayList<Integer> res = ts.topoSort(V, edges);
        System.out.println(res);
    }
}
