package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://www.geeksforgeeks.org/problems/articulation-point-1/1">Articulation Point I</a>
 * Given an undirected connected graph with V vertices and adjacency list adj.
 * You are required to find all the vertices removing which (and edges through it) disconnects the graph
 * into 2 or more components and return it in sorted manner.
 * Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might be loops present in the graph.
 */
public class ArticulationPointI {
    int timer;

    ArticulationPointI() {
        timer = 1;
    }

    private void dfs(int node, int parent, boolean[] visited, int[] discoveryTime, int[] low, ArrayList<ArrayList<Integer>> adj, Set<Integer> res) {
        visited[node] = true;
        discoveryTime[node] = low[node] = timer++;

        // We use this to track the nodes that the dfs visits from this node
        int treeChildren = 0;
        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue;
            if (!visited[neighbor]) {
                treeChildren++;
                dfs(neighbor, node, visited, discoveryTime, low, adj, res);
                low[node] = Math.min(low[node], low[neighbor]);
                // Here to get the critical node we check >=
                // Make sure we are not checking the root node here
                if (parent != -1 && low[neighbor] >= discoveryTime[node]) {
                    res.add(node);
                }
            } else {
                low[node] = Math.min(low[node], discoveryTime[neighbor]);
            }
        }
        // For a root node the logic is bit different
        // If a root node has 2 or more tree nodes, I mean those 2 or more nodes can only be visited from the root node
        // and not from any other node, then the root node is also a critical node
        if (parent == -1 && treeChildren >= 2) {
            res.add(node);
        }
    }

    // Function to return Breadth First Traversal of given graph.
    // Time Complexity: O(E + 2V) + O(klog k) where k is the number of Articulation Nodes
    // Space Complexity: O(E + 2V) + O(3V) + O(K) + O(V) for recursion stack
    public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        Set<Integer> res = new HashSet<>();
        boolean[] visited = new boolean[V]; // O(V)
        int[] discoveryTime = new int[V];   // O(V)
        int[] low = new int[V]; // O(V)
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, -1, visited, discoveryTime, low, adj, res);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>(res);  // O(k)
        ans.sort(Integer::compareTo);
        if (ans.isEmpty()) ans.add(-1);
        return ans;
    }

    public static void main(String[] args) {
        ArticulationPointI ap = new ArticulationPointI();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(List.of(1)));
        adj.add(new ArrayList<>(List.of(0, 4)));
        adj.add(new ArrayList<>(List.of(3, 4)));
        adj.add(new ArrayList<>(List.of(2, 4)));
        adj.add(new ArrayList<>(List.of(1, 2, 3)));
        System.out.println(ap.articulationPoints(5, adj)); // Expected output: [1, 4]

    }
}
