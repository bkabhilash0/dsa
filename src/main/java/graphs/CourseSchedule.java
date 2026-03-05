package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/course-schedule/description/">Course Schedule</a>
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */
public class CourseSchedule {
    private ArrayList<ArrayList<Integer>> createAdjList(int numOfCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numOfCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preReq = prerequisite[1];
            adjList.get(preReq).add(course);
        }
        return adjList;
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
    private boolean topoSortUsingDFS(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = createAdjList(V, edges);
        System.out.println(adjList);
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        boolean[] path = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (visited[i]) continue;
            if (executeTopoSortUsingDFS(i, adjList, visited, path, stack)) {
                // If we find a cycle in the graph, then we cannot perform topological sort and we can return an empty list
                return false;
            }
        }

        return stack.size() == V;
    }

    // Time Complexity: O(V) + O(V + E) where V is the number of courses and E is the number of prerequisites, we are visiting each course and prerequisite once
    // Space Complexity: O(V + E) for the adjacency list + O(V) for the indegree array and the queue in the worst case when we have to add all the courses to the queue
    private boolean isTopoSortPossible(int numOfCourse, int[][] prerequisites) {
        // We shall use Kahn's algorithm
        Queue<Integer> queue = new java.util.LinkedList<>();
        int[] inDegree = new int[numOfCourse];
        ArrayList<ArrayList<Integer>> adjList = createAdjList(numOfCourse, prerequisites);
        int count = 0;
        // Create the indegree data
        for (int i = 0; i < numOfCourse; i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Get all the nodes with indegree 0 and add them to the queue
        for (int i = 0; i < numOfCourse; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            List<Integer> neighbors = adjList.get(node);
            for (int neighbor : neighbors) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return count == numOfCourse;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return topoSortUsingDFS(numCourses, prerequisites);
    }

    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int numCourses = 2;
        int[][] prerequisites = new int[][]{
                {1, 0},
                {0,1}
        };
        System.out.println(courseSchedule.canFinish(numCourses, prerequisites));
    }
}
