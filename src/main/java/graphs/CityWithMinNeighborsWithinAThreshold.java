package graphs;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance">Find the City With the Smallest Number of Neighbors at a Threshold Distance</a>
 * There are n cities numbered from 0 to n-1.
 * Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and
 * weighted edge between cities fromi and toi, and given the integer distanceThreshold.
 * Return the city with the smallest number of cities that are reachable through some path
 * and whose distance is at most distanceThreshold, If there are multiple such cities,
 * return the city with the greatest number.
 * Notice that the distance of a path connecting cities i and j
 * is equal to the sum of the edges' weights along that path.
 */
public class CityWithMinNeighborsWithinAThreshold {
    int INF = 1000000000;
    private int[][] doFloydWarshall(int n, int[][] edges) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Build the matrix with the edges first
        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }

        for (int via = 0; via < n; via++) {
            for (int from = 0; from < n; from++) {
                if (from == via) continue;
                for (int to = 0; to < n; to++) {
                    if (dist[from][via] == INF || dist[via][to] == INF) continue;
                    dist[from][to] = Math.min(dist[from][to], dist[from][via] + dist[via][to]);
                }
            }
        }
        return dist;
    }

    private int findTheCity(int n, int threshold, int[][] dist) {
        // Now find the city which has the min neighbors within the threshold
        int city = 0;
        int numOfNeighbors = INF;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (dist[i][j] <= threshold) {
                    count++;
                }
            }
            // If no neighbors are within threshold ignore that, that's why count > 0 is checked
            if (count <= numOfNeighbors) {
                city = i;
                numOfNeighbors = count;
            }
        }
        return city;
    }

    // Time Complexity: O(n^3) for Floyd Warshall and O(n^2) for finding the city, overall O(n^3)
    // Space Complexity: O(n^2) for the distance matrix
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = doFloydWarshall(n, edges);
        return findTheCity(n, distanceThreshold, dist);
    }

    public static void main(String[] args) {
        CityWithMinNeighborsWithinAThreshold city = new CityWithMinNeighborsWithinAThreshold();
//        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
//        System.out.println(city.findTheCity(4, edges, 4));
//        int[][] edges = {{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}};
//        System.out.println(city.findTheCity(5, edges, 2));
        int[][] edges = {
                {0, 3, 7},
                {2, 4, 1},
                {0, 1, 5},
                {2, 3, 10},
                {1, 3, 6},
                {1, 2, 1}
        };
        System.out.println(city.findTheCity(6, edges, 417));
    }
}
