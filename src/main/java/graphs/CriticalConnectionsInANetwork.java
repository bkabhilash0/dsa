package graphs;

import java.util.Arrays;
import java.util.List;

/**
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server
 * connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi.
 * Any server can reach other servers directly or indirectly through the network.
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 * Return all critical connections in the network in any order.
 */
public class CriticalConnectionsInANetwork {
    int timer;

    CriticalConnectionsInANetwork() {
        timer = 1;
    }

    private List<List<Integer>> createList(int n, List<List<Integer>> connections) {
        List<List<Integer>> list = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new java.util.ArrayList<>());
        }
        for (List<Integer> connection : connections) {
            list.get(connection.get(0)).add(connection.get(1));
            list.get(connection.get(1)).add(connection.get(0));
        }
        return list;
    }

    private void dfs(int node, int parent, List<List<Integer>> adjList, boolean[] visited, int[] discoveryTime, int[] low, List<List<Integer>> res) {
        visited[node] = true;
        discoveryTime[node] = low[node] = timer++;
        for (int neighbor : adjList.get(node)) {
            if (neighbor == parent) continue;
            if (!visited[neighbor]) {
//                System.out.println("Visiting From " + node + " to " + neighbor);
                // If the neighbor node is not visited, visit that
                dfs(neighbor, node, adjList, visited, discoveryTime, low, res);
                // Once all the neighbors of the neighbor node are visited or we reach the deadline and back track, update the low
                low[node] = Math.min(low[node], low[neighbor]);
                // If the low of the neighbor node is > the discovery time of current Node
                // It means we can't reach any ancestor of `node` from any of its neighbors or descendants
                // So if we remove this edge then the other side won't even be reachable
                if (low[neighbor] > discoveryTime[node]) {
//                System.out.println(Arrays.toString(discoveryTime) + " " + Arrays.toString(low) + " " + node + " " + neighbor);
                    res.add(List.of(node, neighbor));
                }
            } else {
                // If the node was already visited and is not the parent then just update the low values and we realise its a cycle
                // The low value of the current Node would be the min of discovery Time of the neighbor and the low of current
//                System.out.println("Back Edge From " + node + " to " + neighbor);
                low[node] = Math.min(discoveryTime[neighbor], low[node]);
            }
        }
    }

    // Time Complexity: O(V + 2E)
    // Space Complexity: O(V + 2E) + O(3V)
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new java.util.ArrayList<>();
        List<List<Integer>> adjList = createList(n, connections); // O(V + 2E)
        boolean[] visited = new boolean[n]; // O(V)
        int[] discoveryTime = new int[n]; // O(V)
        int[] low = new int[n]; // O(V)
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, adjList, visited, discoveryTime, low, res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CriticalConnectionsInANetwork criticalConnectionsInANetwork = new CriticalConnectionsInANetwork();
        List<List<Integer>> res = criticalConnectionsInANetwork.criticalConnections(4, List.of(List.of(0, 1), List.of(1, 2), List.of(2, 0), List.of(1, 3)));
        System.out.println(res);
    }
}
