package daily_questions.april;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: April 7, 2026,
 * <a href="https://leetcode.com/problems/walking-robot-simulation-ii">Robot Simulation 2</a>
 * A width x height grid is on an XY-plane with the bottom-left cell at (0, 0) and the top-right cell at (width - 1, height - 1).
 * The grid is aligned with the four cardinal directions ("North", "East", "South", and "West").
 * A robot is initially at cell (0, 0) facing direction "East".
 * The robot can be instructed to move for a specific number of steps. For each step, it does the following.
 * Attempts to move forward one cell in the direction it is facing.
 * If the cell the robot is moving to is out of bounds, the robot instead turns 90 degrees counterclockwise and retries the step.
 * After the robot finishes moving the number of steps required, it stops and awaits the next instruction.
 * Implement the Robot class:
 * Robot(int width, int height) Initializes the width x height grid with the robot at (0, 0) facing "East".
 * void step(int num) Instructs the robot to move forward num steps.
 * int[] getPos() Returns the current cell the robot is at, as an array of length 2, [x, y].
 * String getDir() Returns the current direction of the robot, "North", "East", "South", or "West".
 */
public class WalkingRobotSimulationII {
    int X;
    int Y;
    Map<String, int[]> directionsMap;
    String currentDirection;
    int XLimit;
    int YLimit;

    public WalkingRobotSimulationII(int width, int height) {
        X = 0;
        Y = 0;
        directionsMap = new HashMap<>();
        directionsMap.put("North", new int[]{0, 1});
        directionsMap.put("East", new int[]{1, 0});
        directionsMap.put("South", new int[]{0, -1});
        directionsMap.put("West", new int[]{-1, 0});
        currentDirection = "East";
        XLimit = width;
        YLimit = height;
    }

//    public void step(int num) {
//        // For loops throws TLE So we need to find a better solution
////        for (int i = 0; i < num; i++) {
////            int[] d = directionsMap.get(currentDirection);
////            int nextX = X + d[0];
////            int nextY = Y + d[1];
////            // Check if we reached the boundary, if so, turn left and retry the step
////            if (nextX < 0 || nextX >= XLimit || nextY < 0 || nextY >= YLimit) {
////                if (currentDirection.equals("North")) {
////                    currentDirection = "West";
////                } else if (currentDirection.equals("West")) {
////                    currentDirection = "South";
////                } else if (currentDirection.equals("South")) {
////                    currentDirection = "East";
////                } else {
////                    currentDirection = "North";
////                }
////                nextX = X + directionsMap.get(currentDirection)[0];
////                nextY = Y + directionsMap.get(currentDirection)[1];
////            }
////            X = nextX;
////            Y = nextY;
////        }
//        int newX = X + directionsMap.get(currentDirection)[0] * num;
//        int newY = Y + directionsMap.get(currentDirection)[1] * num;
////        System.out.println("newX: " + newX + " newY: " + newY);
//
//        // Now check did we cross the boundary, if so how much distance did we cross it
//        // Until we make it valid we need to run this loop
//        while (newX < 0 || newX >= XLimit || newY < 0 || newY >= YLimit) {
////            System.out.println("newX: " + newX + " newY: " + newY);
//            // If X went less than 0, then we need to move it to 0 and then turn left and retry the step
//            // If at this step X exceeded 0 then we could say the robot is currently facing west
//            // Old X position - 0 would give the distance between the robot and the boundary, we need to move the robot to 0 and then turn left and retry the step
//            // no of steps - distance to boundary would give the remaining steps after we move the robot to 0, we need to move the robot to 0 and then turn left and retry the step
//            if (newX < 0) {
//                newX = 0;
//                // We know the robot is currently facing west thats why X went below 0
//                // So turning anti clockwise means now it faces South
//                currentDirection = "South";
//                // Num of steps - Distance to the boundary
//                int possibleSteps = X - 0;
//                int remainingSteps = num - possibleSteps;
//                newX = newX + directionsMap.get(currentDirection)[0] * remainingSteps;
//                newY = newY + directionsMap.get(currentDirection)[1] * remainingSteps;
//            } else if (newX >= XLimit) {
////                System.out.printf("Beyond X Limit : newX: %d newY: %d\n", newX, newY);
//                // The robot was facing west thats why it went beyond the width limit, so we need to move it to the width limit and then turn left and retry the step
//                newX = XLimit - 1;
//                currentDirection = "North";
//                int possibleSteps = (XLimit - 1) - X;
//                int remainingSteps = num - possibleSteps;
////                System.out.println("Remaining steps: " + remainingSteps);
//                newX = newX + directionsMap.get(currentDirection)[0] * remainingSteps;
//                newY = newY + directionsMap.get(currentDirection)[1] * remainingSteps;
////                System.out.println("Updated: newX: " + newX + " newY: " + newY);
//                break;
//            } else if (newY < 0) {
//                // The robot was facing South that's why it went below 0, so we need to move it to 0 and then turn left and retry the step
//                newY = 0;
//                currentDirection = "East";
//                int possibleSteps = Y - 0;
//                int remainingSteps = num - Y;
//                newX = newX + directionsMap.get(currentDirection)[0] * remainingSteps;
//                newY = newY + directionsMap.get(currentDirection)[1] * remainingSteps;
//            } else {
//                // The robot was facing north thats why it went beyond YLimit, so we need to move it to YLimit - 1 and turn left and retry the step
//                newY = YLimit - 1;
//                currentDirection = "West";
//                int possibleSteps = (YLimit - 1) - Y;
//                int remainingSteps = num - possibleSteps;
//                newX = newX + directionsMap.get(currentDirection)[0] * remainingSteps;
//                newY = newY + directionsMap.get(currentDirection)[1] * remainingSteps;
//            }
//        }
//        X = newX;
//        Y = newY;

    /// /        System.out.println("Final newX: " + newX + " newY: " + newY);
//    }
    // Time Complexity: O(1) -> The while loop runs max of 4 times as 4 edges needed direction change
    // Space Complexity: O(1)
    public void step(int num) {
        // The intuition is that, the robot walks through the perimeter or border of the rectangle
        // We shall consider this as a single line with circular characteristic
        // Finding the perimeter of the rectangle tells us how many points or steps it has
        // 2(l+b) - 4 => -4 is done is not consider the corners multiple times as corners are common component
        int totalNumberOfStepsPossible = (2 * (XLimit + YLimit)) - 4;   // Perimeter basically
        // Think it like a number of rotation - if num == total steps then we come back to the origin again
        // Using this we can simplify the num
        num = num % totalNumberOfStepsPossible;

        // An edge case where u reach origin, but this time the direction would be South as the robot finishes one rotation
        if (num == 0) {
            if (X == 0 && Y == 0) {
                currentDirection = "South";
            }
            return;
        }

        while (num > 0) {
            // Now keep moving until there are no number of steps to move
            if (currentDirection.equals("East")) {
                // You can move a max of XLimit - 1 steps in east direction, if num is less than that then move num steps and break the loop
                // This tells us if the distance between border and X is small or the new steps is small
                // Coz we are take only that many steps in this direction
                int steps = Math.min((XLimit - 1) - X, num);
                // We take that many steps
                X += steps;
                // Then decrease the num by that many steps
                num -= steps;
                // If there are extra steps then change direction
                if (num > 0) {
                    currentDirection = "North";
                }
            } else if (currentDirection.equals("North")) {
                int steps = Math.min((YLimit - 1) - Y, num);
                Y += steps;
                num -= steps;
                if (num > 0) {
                    currentDirection = "West";
                }
            } else if (currentDirection.equals("West")) {
                int steps = Math.min(X, num);
                X -= steps;
                num -= steps;
                if (num > 0) {
                    currentDirection = "South";
                }
            } else {
                int steps = Math.min(Y, num);
                Y -= steps;
                num -= steps;
                if (num > 0) {
                    currentDirection = "East";
                }
            }
        }
    }

    public int[] getPos() {
        return new int[]{X, Y};
    }

    public String getDir() {
        return currentDirection;
    }

    public static void main(String[] args) {
        WalkingRobotSimulationII robot = new WalkingRobotSimulationII(6, 3);
        robot.step(2);
        robot.step(2);
        System.out.println(robot.getPos()[0] + " " + robot.getPos()[1]);
        System.out.println(robot.getDir());
        robot.step(2);
        robot.step(1);
        robot.step(4);
        System.out.println(robot.getPos()[0] + " " + robot.getPos()[1]);
        System.out.println(robot.getDir());
    }
}
