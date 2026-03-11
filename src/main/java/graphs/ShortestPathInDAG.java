package graphs;

import java.util.*;

/**
 * <a href="https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1">Shortest Path in DAG</a>
 * Given a Directed Acyclic Graph of V vertices from 0 to n-1 and a 2D Integer array(or vector) edges[ ][ ] of length E,
 * where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.
 * Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.
 */
public class ShortestPathInDAG {
    static class Pair {
        int node;
        int distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "node=" + node +
                    ", distance=" + distance +
                    '}';
        }
    }

    private List<ArrayList<Pair>> createList(int V, int E, int[][] edges) {
        List<ArrayList<Pair>> list = new java.util.ArrayList<>();
        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int startNode = edge[0];
            int endNode = edge[1];
            int distance = edge[2];
            list.get(startNode).add(new Pair(endNode, distance));
        }
        return list;
    }

    // We can omit the return true or false as the question itself specifies its a DAG
    private void topoSortUsingDFS(int node, List<ArrayList<Pair>> adjList, boolean[] visited, boolean[] path, Deque<Integer> stack) {
        visited[node] = true;
        path[node] = true;
        for (Pair neighbour : adjList.get(node)) {
            if (!visited[neighbour.node]) {
                topoSortUsingDFS(neighbour.node, adjList, visited, path, stack);
            }
        }
        path[node] = false;
        stack.push(node);
    }

    // Time Complexity: O(V + E) + O(V) => O(V + E) is for toposort and O(V) is for iterating through the stack to find the shortest path
    // Space Complexity: O(V) for the visited and path arrays and O(V) for the stack and O(V + E) for the adjacency list
    private int[] findShortestPath(int V, int E, int[][] edges) {
        // Code here
        List<ArrayList<Pair>> adjList = createList(V, E, edges);
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[V];
        boolean[] path = new boolean[V];
        // Since the graph is a DAG, we can perform topological sort and then find the shortest path using the topological order
        for (int i = 0; i < V; i++) {
            if (visited[i]) continue;
            // We are not checking the cycle as the question itself specifies its a DAG
            topoSortUsingDFS(i, adjList, visited, path, stack);
        }
        int[] distance = new int[V];
        // Set all distances to infinity except for the source vertex which is set to 0
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;

        while (!stack.isEmpty()) {
            // Get the current node and the distance to that node from the src node.
            int currentNode = stack.pop();
            int currentDistance = distance[currentNode];
            // If the distance to the current node is infinity then we cannot reach that node from the src node so we can skip it
            if (currentDistance == Integer.MAX_VALUE) continue;
            for (Pair neighbor : adjList.get(currentNode)) {
                // Get the distance to the neighbor node from src node
                int neighborDistance = distance[neighbor.node];
                // From the current node, if we can reach the neighbor node with a shorter distance than the already visited distance then we update the distance to the neighbor node
                if (currentDistance + neighbor.distance < neighborDistance) {
                    // Set the new distance to the nieghbor node from the src node
                    distance[neighbor.node] = currentDistance + neighbor.distance;
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                distance[i] = -1;
            }
        }
        return distance;
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        return findShortestPath(V, E, edges);
    }

    public static void main(String[] args) {
        ShortestPathInDAG spid = new ShortestPathInDAG();
        int V = 6;
        int E = 7;
        int[][] edges = {{0, 1, 2}, {0, 4, 1}, {1, 2, 3}, {4, 2, 2}, {4, 5, 4}, {2, 3, 6}, {5, 3, 1}};
        int[] result = spid.shortestPath(V, E, edges);
        System.out.print("Shortest path from source vertex 0 to all vertices: ");
        System.out.println(Arrays.toString(result));
    }
}
