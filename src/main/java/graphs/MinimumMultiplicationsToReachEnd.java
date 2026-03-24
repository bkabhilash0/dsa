package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1">Minimum Multiplications to Reach End</a>
 * Given start, end and an array arr of n numbers.
 * At each step, start is multiplied with any number in the array and
 * then mod operation with 100000 is done to get the new start.
 * Your task is to find the minimum steps in which end can be achieved starting from start.
 * If it is not possible to reach end, then return -1.
 */
public class MinimumMultiplicationsToReachEnd {
    // Time Complexity: O(100000*n) Where n is the size of the array. Max possible nodes are 100000
    // Space Complexity: O(2*100000) Both for queue and visited
    private int executeMinimumMultiplications(int[] arr, int start, int end) {
        boolean[] visited = new boolean[1_00_000];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int steps = -1;

        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == end) return steps;
                for (int num : arr) {
                    int next = current * num;
                    next %= 1_00_000;
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    int minimumMultiplications(int[] arr, int start, int end) {
        // Your code here
        return executeMinimumMultiplications(arr, start, end);
    }

    public static void main(String[] args) {
        MinimumMultiplicationsToReachEnd solution = new MinimumMultiplicationsToReachEnd();
        int[] arr = {2, 5, 7};
        int start = 3;
        int end = 30;
        System.out.println(solution.minimumMultiplications(arr, start, end)); // Output: 2
    }
}
