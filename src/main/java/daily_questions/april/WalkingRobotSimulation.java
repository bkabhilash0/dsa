package daily_questions.april;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: April 6, 2026,
 * <a href="https://leetcode.com/problems/walking-robot-simulation">Walking Robot Simulation</a>
 * A robot on an infinite XY-plane starts at point (0, 0) facing north.
 * The robot receives an array of integers commands, which represents a sequence of moves that it needs to execute.
 * are only three possible types of instructions the robot can receive:
 * -2: Turn left 90 degrees.
 * -1: Turn right 90 degrees.
 * 1 <= k <= 9: Move forward k units, one unit at a time.
 * Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi).
 * If the robot runs into an obstacle, it will stay in its current location (on the block adjacent to the obstacle) and move onto the next command.
 * Return the maximum squared Euclidean distance that the robot reaches at any point in its path (i.e. if the distance is 5, return 25).
 * Note:
 * There can be an obstacle at (0, 0). If this happens, the robot will ignore the obstacle until it has moved off the origin.
 * However, it will be unable to return to (0, 0) due to the obstacle.
 * North means +Y direction.
 * East means +X direction.
 * South means -Y direction.
 * West means -X direction.
 */
public class WalkingRobotSimulation {
    // The robot has to turn right
    // If the robot is currently facing north it has to face east
    // If the robot is currently facing east it has to face south
    // If the robot is currently facing south it has to face west
    // If the robot is currently facing west it has to face north
    private char rightTurn(char direction) {
        if (direction == 'N') {
            return 'E';
        } else if (direction == 'E') {
            return 'S';
        } else if (direction == 'S') {
            return 'W';
        } else {
            return 'N';
        }
    }

    // The robot has to turn left
    // If the robot is currently facing north it has to face west
    // If the robot is currently facing west it has to face south
    // If the robot is currently facing south it has to face east
    // If the robot is currently facing eat it has to face north
    private char leftTurn(char direction) {
        if (direction == 'N') {
            return 'W';
        } else if (direction == 'W') {
            return 'S';
        } else if (direction == 'S') {
            return 'E';
        } else {
            return 'N';
        }
    }

    private char turn(char direction, int command) {
        if (command == -1) {
            return rightTurn(direction);
        }
        return leftTurn(direction);
    }

    // Since we calculate the distance form origin (0,0) we do not need x1 or y1
    private long manhattanDistance(int x2, int y2) {
        return (int) (Math.pow(x2, 2) + Math.pow(y2, 2));
    }

    private int calculateForwardCoordinatesWithObstacles(int x, int y, boolean isXChanging, int increasingFactor, int distance, Set<String> obstacles) {
        // The increasing factor is either +1 or -1, moving either north or south or east or west
        // North: +1
        // South: -1
        // East: +1
        // West: -1
        String obstacleKey;
        for (int i = 1; i <= distance; i++) {
            if (isXChanging) {
                x += increasingFactor;
            } else {
                y += increasingFactor;
            }
            obstacleKey = String.format("%d#%d", x, y);
            if (obstacles.contains(obstacleKey)) {
                // We have reached an obstacle so we need to revert the previous operation and break out of the loop
                if (isXChanging) {
                    x -= increasingFactor;
                } else {
                    y -= increasingFactor;
                }
                break;
            }
        }
        return isXChanging ? x : y;
    }

    private int[] moveForward(char direction, int x, int y, int command, Set<String> obstacles) {
        int[] coords = new int[2];
        int dx = x;
        int dy = y;
        if (direction == 'N') {
            dy = calculateForwardCoordinatesWithObstacles(x, y, false, 1, command, obstacles);
        } else if (direction == 'E') {
            dx = calculateForwardCoordinatesWithObstacles(x, y, true, 1, command, obstacles);
        } else if (direction == 'S') {
            dy = calculateForwardCoordinatesWithObstacles(x, y, false, -1, command, obstacles);
        } else {
            dx = calculateForwardCoordinatesWithObstacles(x, y, true, -1, command, obstacles);
        }
        return new int[]{dx, dy};
    }

    private int intuition(int[] commands, int[][] obstacles) {
        char direction = 'N';
        int X = 0;
        int Y = 0;
        long maxDistance = 0;
        Set<String> obstaclesSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstaclesSet.add(obstacle[0] + "#" + obstacle[1]);
        }
        for (int command : commands) {
            if (command == -1 || command == -2) {
                direction = turn(direction, command);
            } else {
                // The robot needs to move forward on the direction the robot is facing
                int[] newCoords = moveForward(direction, X, Y, command, obstaclesSet);
                X = newCoords[0];
                Y = newCoords[1];
                // Check with distance from the origin
                System.out.println("Current position: (" + X + ", " + Y + ")");
                maxDistance = Math.max(maxDistance, manhattanDistance(X, Y));
                // Check if the new coordinates are an obstacle
            }
        }
        return (int) maxDistance;
    }

    // Time Complexity: O(M) + O(N) => M = obstacles and N = commands
    // Space Complexity: O(M)
    private int optimal(int[] commands, int[][] obstacles) {
        int result = 0;
        int x = 0;
        int y = 0;
        Set<String> obstaclesSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstaclesSet.add(obstacle[0] + "#" + obstacle[1]);
        }
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        for (int command : commands) {
            if (command == -1) {
                d = (d + 1) % 4;
            } else if (command == -2) {
                d = (d + 3) % 4;
            } else {
                for (int i = 0; i < command; i++) {
                    int newX = x + directions[d][0];
                    int newY = y + directions[d][1];
                    if (obstaclesSet.contains(newX + "#" + newY)) break;
                    x = newX;
                    y = newY;
                }
                result = Math.max(result, x * x + y * y);
            }
        }
        return result;
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        return optimal(commands, obstacles);
    }

    public static void main(String[] args) {
        WalkingRobotSimulation walkingRobotSimulation = new WalkingRobotSimulation();
//        int[] commands = {4, -1, 3};
//        int[][] obstacles = {};
        int[] commands = {4, -1, 4, -2, 4};
        int[][] obstacles = {{2, 4}};
        int result = walkingRobotSimulation.robotSim(commands, obstacles);
        System.out.println("Max distance from origin: " + result);
    }
}
