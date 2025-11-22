package recursion;

import java.util.ArrayList;

/*
 * Consider a rat placed at position (0, 0) in an n x n square matrix maze[][]. The rat's goal is to reach the destination at position (n-1, n-1).
 * The rat can move in four possible directions: 'U'(up), 'D'(down), 'L' (left), 'R' (right).
 * The matrix contains only two possible values:
 * 0: A blocked cell through which the rat cannot travel.
 * 1: A free cell that the rat can pass through.
 * Your task is to find all possible paths the rat can take to reach the destination, starting from (0, 0) and ending at (n-1, n-1),
 * under the condition that the rat cannot revisit any cell along the same path. Furthermore, the rat can only move to adjacent cells that are within the bounds of the matrix and not blocked.
 * If no path exists, return an empty list.
 * Note: Return the final result vector in lexicographically smallest order.
 * */
public class RatInAMaze {
    private void execute(int[][] maze, int row, int col, String path, ArrayList<String> result, int n) {
        if (row == n - 1 && col == n - 1) {
            result.add(path);
            return;
        }

//        String directions = "DLRU";

//        for(int i = 0; i < directions.length(); i++){
        // Check if I can move down
        if (row < n - 1 && maze[row + 1][col] == 1) {
            maze[row][col] = 0;
            path = path + 'D';
            execute(maze, row + 1, col, path, result, n);
            path = path.substring(0, path.length() - 1);
            maze[row][col] = 1;
        }
        // Check if I can move left
        if (col > 0 && maze[row][col - 1] == 1) {
            maze[row][col] = 0;
            path = path + 'L';
            execute(maze, row, col - 1, path, result, n);
            path = path.substring(0, path.length() - 1);
            maze[row][col] = 1;
        }

        // Check for right
        if (col < n - 1 && maze[row][col + 1] == 1) {
            maze[row][col] = 0;
            path = path + 'R';
            execute(maze, row, col + 1, path, result, n);
            path = path.substring(0, path.length() - 1);
            maze[row][col] = 1;
        }

        // Check for up
        if (row > 0 && maze[row - 1][col] == 1) {
            maze[row][col] = 0;
            path = path + 'U';
            execute(maze, row - 1, col, path, result, n);
            path = path.substring(0, path.length() - 1);
            maze[row][col] = 1;
        }
//        }
    }

    private void executeOptimal(int[][] maze, int row, int col, StringBuilder path, ArrayList<String> result, int n) {
        if (row == n - 1 && col == n - 1) {
            result.add(path.toString());
            return;
        }

        String directions = "DLRU";
        int[] rowDirections = {1, 0, 0, -1};
        int[] colDirections = {0, -1, 1, 0};

        for (int i = 0; i < directions.length(); i++) {
            int newRow = row + rowDirections[i];
            int newCol = col + colDirections[i];
            if (newCol >= 0 && newCol < n && newRow >= 0 && newRow < n && maze[newRow][newCol] == 1) {
                path.append(directions.charAt(i));
                maze[row][col] = 0;
                executeOptimal(maze, newRow, newCol, path, result, n);
                path.deleteCharAt(path.length() - 1);
                maze[row][col] = 1;
            }
        }
    }

    public ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        ArrayList<String> result = new ArrayList<>();
        executeOptimal(maze, 0, 0, new StringBuilder(), result, maze.length);
        return result;
    }


    public static void main(String[] args) {
        RatInAMaze obj = new RatInAMaze();
        int[][] maze = {{1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}};
        ArrayList<String> result = obj.ratInMaze(maze);
        System.out.println(result);
    }
}
