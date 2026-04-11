package graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/making-a-large-island/description/">Making a Large Island</a>
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 */
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int totalRows = grid.length;
        int totalCols = grid[0].length;
        int totalCells = totalCols * totalRows;
        DisjointSetUnion disjointSetUnion = new DisjointSetUnion(totalCells);
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // Create a DSU for the current setup
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalCols; col++) {
                if (grid[row][col] == 0) continue;
                if (grid[row][col] == 1) {
                    for (int[] direction : directions) {
                        int newRow = row + direction[0];
                        int newCol = col + direction[1];
                        if (newRow >= 0 && newRow < totalRows && newCol >= 0 && newCol < totalCols && grid[newRow][newCol] == 1) {
                            int nodeA = (row * totalCols) + col;
                            int nodeB = (newRow * totalCols) + newCol;
                            disjointSetUnion.unionBySize(nodeA, nodeB);
                        }
                    }
                }
            }
        }

        int maxIslandSize = Integer.MIN_VALUE;
        Set<Integer> uniqueIslands = new HashSet<>();
        // Now go through the grid again and try converting each 0's to 1's and find the max of all the island sizes
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalCols; col++) {
                uniqueIslands.clear();
                if (grid[row][col] == 1) continue;
                // Now check the 4 sides of the cell to check if it can be connected to any other island
                // If so add unique islands to the set and later sum the sizes
                for (int[] direction : directions) {
                    int newRow = direction[0] + row;
                    int newCol = direction[1] + col;
                    // Check if the cell has 1
                    if (newRow >= 0 && newRow < totalRows && newCol >= 0 && newCol < totalCols && grid[newRow][newCol] == 1) {
                        // Get the respective node number to search in DSU
                        int node = (newRow * totalCols) + newCol;
                        // Find the root Parent of this node and add it to the set
                        uniqueIslands.add(disjointSetUnion.find(node));
                    }
                }
                // Now for all the root parents in the set get their sizes and sum it
                // Default is 1 coz we consider the current converted node too
                int temp = 1;
                for (int island : uniqueIslands) {
                    int size = disjointSetUnion.getSizes()[island];
                    temp += size;
                }
                maxIslandSize = Math.max(maxIslandSize, temp);
            }
        }

        // Now to an edge case where there are 0 zeros to even check i.e the grid is full of 1's then return the total number of cells

        return maxIslandSize == Integer.MIN_VALUE ? totalCells : maxIslandSize;
    }

    public static void main(String[] args) {
//        int[][] grid = {{1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}, {0, 0, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 0, 1, 1, 1}};  // Output: 20
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {0, 1, 1}}; // Output: 4
        MakingALargeIsland makingALargeIsland = new MakingALargeIsland();
        System.out.println(makingALargeIsland.largestIsland(grid));
    }
}
