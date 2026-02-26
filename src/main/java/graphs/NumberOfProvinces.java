package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/number-of-provinces/description/">Number of Provinces</a>
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 */
public class NumberOfProvinces {
    // Time Complexity: O(V^2) where V is the number of vertices in the graph
    // Space Complexity: O(V^2) for the adjacency list, in the worst case we might have to store all the vertices and edges in the graph
    private ArrayList<ArrayList<Integer>> generateAdjList(int[][] adjMatrix) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                }
            }
        }
        return adjList;
    }

    // Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V) for the visited array and the queue, in the worst case we might have to visit all the vertices and add them to the queue
    private void bfs(int startNode, ArrayList<ArrayList<Integer>> adjList, int[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = 1;
            for (int neighbor : adjList.get(node)) {
                if (visited[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
    }

    // Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V) for the visited array and the stack, in the worst case we might have to visit all the vertices and add them to the stack
    // Faster than BFS
    private void dfs(int startNode, ArrayList<ArrayList<Integer>> adjList, int[] visited) {
        visited[startNode] = 1;
        for (int neighbor : adjList.get(startNode)) {
            if (visited[neighbor] == 0) {
                dfs(neighbor, adjList, visited);
            }
        }
    }

    private void bfsWithoutAdjList(int startNode, int[][] adjMatrix, int[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = 1;
            for (int i = 0; i < adjMatrix[node].length; i++) {
                boolean isConnected = adjMatrix[node][i] == 1;
                if (isConnected && visited[i] == 0) {
                    queue.add(i);
                }
            }
        }
    }

    // Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V) for the visited array and the stack, in the worst
    // The faster one since we are not using an adjacency list, we are directly using the adjacency matrix to check for the neighbors of each vertex
    private void dfsWithoutAdjList(int startNode, int[][] adjMatrix, int[] visited) {
        visited[startNode] = 1;
        for (int i = 0; i < adjMatrix[startNode].length; i++) {
            boolean isConnected = adjMatrix[startNode][i] == 1;
            if (isConnected && visited[i] == 0) {
                dfsWithoutAdjList(i, adjMatrix, visited);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int count = 0;
        int[] visited = new int[isConnected.length];
//        ArrayList<ArrayList<Integer>> adjList = generateAdjList(isConnected);
//        for (int i = 0; i < adjList.size(); i++) {
//            if (visited[i] == 0) {
//                dfs(i, adjList, visited);
//                count++;
//            }
//        }
        for (int i = 0; i < isConnected.length; i++) {
            if (visited[i] == 0) {
                count++;
                dfsWithoutAdjList(i, isConnected, visited);
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] isConnected = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        NumberOfProvinces numberOfProvinces = new NumberOfProvinces();
        System.out.println(numberOfProvinces.findCircleNum(isConnected));
    }
}
