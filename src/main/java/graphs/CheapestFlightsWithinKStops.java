package graphs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/cheapest-flights-within-k-stops/">Cheapest Flights with K Stops</a>
 * There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei]
 * indicates that there is a flight from city fromi to city toi with cost pricei.
 * You are also given three integers src, dst, and k,
 * return the cheapest price from src to dst with at most k stops.
 * If there is no such route, return -1.
 */
public class CheapestFlightsWithinKStops {
    static class City {
        int city;
        int cost;

        public City(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("[city=%d, cost=%d]", city, cost);
        }
    }

    static class QueueNode implements Comparable<QueueNode> {
        int city;
        int stops;
        int cost;

        public QueueNode(int city, int stops, int cost) {
            this.city = city;
            this.stops = stops;
            this.cost = cost;
        }

        @Override
        public int compareTo(QueueNode o) {
            return this.cost - o.cost;
        }
    }

    private List<List<City>> createAdjList(int n, int[][] flights) {
        List<List<City>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            adjList.get(from).add(new City(to, cost));
        }
        return adjList;
    }

    // Time Complexity: O(N)
    // Space Complexity: O(N + E) where N is the number of cities and E is the number of flights.
    private int executeFindCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<City>> adjList = createAdjList(n, flights);
        // We can replace the PQ using a normal queue since the stops increase +1
        Queue<QueueNode> pq = new ArrayDeque<>();
        int[] costs = new int[n];

        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        pq.offer(new QueueNode(src, 0, 0)); // city, stops, cost

        while (!pq.isEmpty()) {
            QueueNode current = pq.poll();
            int city = current.city;
            int stops = current.stops;
            int cost = current.cost;

            if (city == dst) continue;
            if (stops > k) continue;

            for (City neighbor : adjList.get(city)) {
                int newCost = cost + neighbor.cost;
                if (newCost < costs[neighbor.city]) {
                    costs[neighbor.city] = newCost;
                    pq.offer(new QueueNode(neighbor.city, stops + 1, newCost));
                }
            }
        }
        System.out.println(Arrays.toString(costs));
        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        return executeFindCheapestPrice(n, flights, src, dst, k);
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();
//        int n = 3;
//        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
//        int src = 0;
//        int dst = 2;
//        int k = 1;
        // ---
//        int n = 5;
//        int[][] flights = {{4, 1, 1}, {1, 2, 3}, {0, 3, 2}, {0, 4, 10}, {3, 1, 1}, {1, 4, 3}};
//        int src = 2;
//        int dst = 1;
//        int k = 1;
        int n = 5;
        int[][] flights = {
                {0, 1, 5},
                {1, 2, 5},
                {0, 3, 2},
                {3, 1, 2},
                {1, 4, 1},
                {4, 2, 1}
        };
        int src = 0;
        int dst = 2;
        int k = 2;

        System.out.println(cheapestFlightsWithinKStops.findCheapestPrice(n, flights, src, dst, k)); // return 200
    }
}
