package graphs;

/**
 * <a href="https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/">Number of Operations to Make a Network Connected</a>
 * There are n computers numbered from 0 to n - 1 connected
 * by ethernet cables connections forming a network where
 * connections[i] = [ai, bi] represents a connection between computers ai and bi.
 * Any computer can reach any other computer directly or indirectly through the network.
 * You are given an initial computer network connections.
 * You can extract certain cables between two directly connected computers,
 * and place them between any pair of disconnected computers to make them directly connected.
 * Return the minimum number of times you need to do this
 * in order to make all the computers connected. If it is not possible, return -1.
 */
public class NumberOfOpsToMakeNetworkConnected {
    // Time Complexity: O(Eα)
    // Space Complexity: O(2V) for parent and rank arrays in DSU, V is the number of nodes
    public int solveUsingDSU(int n, int[][] connections) {
        // Min number of edges required to connect n nodes/components is n-1
        // Here we have n nodes we require n-1 edges to connect them, if we have less than n-1 edges then we cannot connect all the nodes
        // We need to find out how many extra edges are available
        // Here we want to make the whole network as a single connected component. So currently find how much components are there
        // Then those many components - 1 would be the required edges
        // If extra edges >= components - 1 then return components - 1 else return -1

        // If the number of edges present is less than the min required edges to connect n nodes then we cannot connect all the nodes, return -1
        if (connections.length < n - 1) return -1;
        // Initialize Disjoint Set Union to find number of connected components
        DisjointSetUnion disjointSetUnion = new DisjointSetUnion(n);

        // Start adding edges so that we can create the connected components
        for (int[] connection : connections) {
            disjointSetUnion.union(connection[0], connection[1]);
        }
        // Now we have the number of connected components in the network
        // Its either the number of unique parents or we can directly get it from the disjoint set union class
        int numberOfConnectedComponents = disjointSetUnion.getNumOfConnectedComponents();
        // The extra edges are derived when we try to connect 2 nodes and figure out that they are already connected in the same component
        // The DSU class returns that number of extra edges
        int numberOfExtraEdges = disjointSetUnion.getNumberOfExtraEdges();

        if (numberOfExtraEdges >= numberOfConnectedComponents - 1) {
            return numberOfConnectedComponents - 1;
        }
        return -1;
    }

    public int makeConnected(int n, int[][] connections) {
        return solveUsingDSU(n, connections);
    }

    public static void main(String[] args) {
        NumberOfOpsToMakeNetworkConnected numberOfOpsToMakeNetworkConnected = new NumberOfOpsToMakeNetworkConnected();
//        int n = 4;
//        int[][] connections = {{0, 1}, {0, 2}, {1, 2}};
        int n = 6;
        int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        int result = numberOfOpsToMakeNetworkConnected.makeConnected(n, connections);
        System.out.println("Number of operations to make network connected: " + result);
    }
}
