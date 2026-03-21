package graphs;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given a weighted undirected graph with n vertices numbered from 1 to n and m edges along with their weights.
 * Find the shortest path between vertex 1 and vertex n. Each edge is given as {a, b, w}, denoting an edge between vertices a and b with weight w.
 * If a path exists, return a list of integers where the first element is the total weight of the shortest path,
 * and the remaining elements are the nodes along that path (from 1 to n). If no path exists, return a list containing only {-1}.
 * Note: The driver code will internally verify your returned list.
 * If both the path and its total weight are valid, only the total weight will be displayed as output.
 * If your list contains only -1, the output will be -1.
 * If the returned list is invalid, the output will be -2.
 */
public class PrintShortestPath {
    static class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("[node=%d, weight=%d]", node, weight);
        }
    }

    private List<List<Edge>> createAdjList(int numberOfVertices, int numberOfEdges, int[][] edges) {
        List<List<Edge>> adjMatrix = new java.util.ArrayList<>();
        for (int i = 0; i <= numberOfVertices; i++) {
            adjMatrix.add(new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            adjMatrix.get(edge[0]).add(new Edge(edge[1], edge[2]));
            adjMatrix.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }
        return adjMatrix;
    }

    // Time Complexity: O(E*logV) + O(E+V) + O(V) -> Dijkstra + Create Adj List + Create Path Trace
    // Space Complexity: O(3V) -> pathTrace + distance array + priority queue
    private List<Integer> executeShortestPath(int numberOfVertices, List<List<Edge>> adjList) {
        // The graph is not 0 indexed, its 1 indexed thats why +1
        int[] pathTrace = new int[numberOfVertices + 1];
        int[] shortestDistance = new int[numberOfVertices + 1];
        Arrays.fill(shortestDistance, Integer.MAX_VALUE);
        Arrays.fill(pathTrace, -1);
        // Store [dist, node] in the pq
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 1});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[1];
            int dist = current[0];

            if (shortestDistance[node] < dist) {
                continue;
            }

            List<Edge> neighbors = adjList.get(node);
            for (Edge neighbor : neighbors) {
                int newDistance = dist + neighbor.weight;
                if (newDistance < shortestDistance[neighbor.node]) {
                    shortestDistance[neighbor.node] = newDistance;
                    // If this node is reached in a shorest manner update the parent from which it was reached
                    pathTrace[neighbor.node] = node;
                    pq.offer(new int[]{newDistance, neighbor.node});
                }
            }
        }

        // If there was no path found to the destination return -1
        if (pathTrace[numberOfVertices] == -1) {
            return List.of(-1);
        }
        List<Integer> path = new java.util.LinkedList<>();
        int startNode = numberOfVertices;
        while (startNode != 1) {
            path.addFirst(startNode);
            startNode = pathTrace[startNode];
        }
        path.addFirst(1);
        path.addFirst(shortestDistance[numberOfVertices]);

        return path;
    }

    public List<Integer> shortestPath(int n, int m, int[][] edges) {
        //  Code Here.
        List<List<Edge>> adjList = createAdjList(n, m, edges);
        return executeShortestPath(n, adjList);
    }

    public static void main(String[] args) {
        PrintShortestPath printShortestPath = new PrintShortestPath();
        int n = 5;
        int m = 6;
        int[][] edges = {{1, 2, 2}, {2, 5, 5}, {2, 3, 4}, {1, 4, 1}, {4, 3, 3}, {3, 5, 1}};
        List<Integer> res = printShortestPath.shortestPath(n, m, edges);
        System.out.println(res);
    }
}
