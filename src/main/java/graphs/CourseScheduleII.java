package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 */
public class CourseScheduleII {
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

    private boolean executeDFS(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, boolean[] path, Stack<Integer> topoSort) {
        visited[node] = true;
        path[node] = true;

        ArrayList<Integer> neighbors = adjList.get(node);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                if (executeDFS(neighbor, adjList, visited, path, topoSort)) {
                    return true;
                }
            } else if (path[neighbor]) {
                return true;
            }
        }
        topoSort.push(node);
        path[node] = false;
        return false;
    }

    // Time Complexity: O(V) + O(V + E) where V is the number of vertices and E is the number of edges in the graph, we are visiting each vertex and edge once
    // Space Complexity: O(V + E) for adjList + O(V) for the stack, visited and path arrays and the recursion stack in the worst case when the graph forms a linear chain
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjList = createAdjList(numCourses, prerequisites);
        Stack<Integer> topoSort = new Stack<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i]) continue;
            if (executeDFS(i, adjList, visited, path, topoSort)) {
                // If we find a cycle in the graph, then we cannot perform topological sort and we can return an empty list
                return new int[0];
            }
        }
        if (topoSort.size() != numCourses) {
            return new int[0];
        }

        int[] result = new int[numCourses];
        int index = 0;
        while (!topoSort.isEmpty()) {
            result[index++] = topoSort.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        CourseScheduleII courseScheduleII = new CourseScheduleII();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] result = courseScheduleII.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
    }
}
