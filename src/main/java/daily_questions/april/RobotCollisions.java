package daily_questions.april;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Date: April 1, 2026,
 * <a href="https://leetcode.com/problems/robot-collisions">Robot Collisions</a>
 * There are n 1-indexed robots, each having a position on a line, health, and movement direction.
 * You are given 0-indexed integer arrays positions, healths, and a string directions
 * (directions[i] is either 'L' for left or 'R' for right). All integers in positions are unique.
 * All robots start moving on the line simultaneously at the same speed in their given directions.
 * If two robots ever share the same position while moving, they will collide.
 * If two robots collide, the robot with lower health is removed from the line,
 * and the health of the other robot decreases by one.
 * The surviving robot continues in the same direction it was going.
 * If both robots have the same health, they are both removed from the line.
 * Your task is to determine the health of the robots that survive the collisions,
 * in the same order that the robots were given, i.e. final health of robot 1 (if survived),
 * final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.
 * Return an array containing the health of the remaining robots (in the order they were given in the input),
 * after no further collisions can occur.
 * Note: The positions may be unsorted.
 */
public class RobotCollisions {
    private static class Robot {
        int position;
        int health;
        Character direction;
        int index;

        Robot(int position, int health, Character direction, int index) {
            this.position = position;
            this.health = health;
            this.direction = direction;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Robot: {position: " + position + ", health: " + health + ", direction: " + direction + "}";
        }
    }

    // Time Complexity: O(N) + O(NlogN) + O(N) + O(N) = O(NlogN)
    // Space Complexity: O(N) + O(N) = O(N)
    private List<Integer> optimal(int[] positions, int[] healths, String directions) {
        List<Integer> result = new java.util.ArrayList<>();
        List<Robot> robots = new java.util.ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            robots.add(new Robot(positions[i], healths[i], directions.charAt(i), i));
        }
        // Now Sort the robots based on their positions so that we can easily find the collisions
        robots.sort(Comparator.comparingInt(a -> a.position));
        Stack<Robot> stack = new Stack<>();
        for (Robot currentRobot : robots) {
            // If the currentRobot is moving towards the right push it into the stack
            if (currentRobot.direction == 'R') {
                stack.push(currentRobot);
            } else {
                // We will have to now handle the collisions
                // Since every robots in the stack are moving right and this is moving left a collision will occur
                while (!stack.isEmpty() && stack.peek().direction == 'R') {
                    Robot top = stack.peek();
                    // Now collide
                    if (top.health > currentRobot.health) {
                        // Then current Robot will explode and top robots health will decrease by 1
                        top.health--;
                        currentRobot.health = 0;
                        break;
                    } else if (top.health < currentRobot.health) {
                        // Then top Robot will explode and current robots health will decrease by 1
                        currentRobot.health--;
                        stack.pop();
                    } else {
                        // When health of both robots are same both will explode
                        stack.pop();
                        currentRobot.health = 0;
                        break;
                    }
                }
                if (currentRobot.health > 0) {
                    stack.push(currentRobot);
                }
            }
        }
        // Now the stack will have all the survived robots
        int[] temp = new int[positions.length];
        Arrays.fill(temp, -1);
        while (!stack.isEmpty()) {
            temp[stack.peek().index] = stack.pop().health;
        }
        for (int num : temp) {
            if (num != -1)
                result.add(num);
        }
        return result;
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        return optimal(positions, healths, directions);
    }

    public static void main(String[] args) {
        int[] positions = {3, 5, 2, 6};
        int[] healths = {10, 10, 15, 12};
        String directions = "RLRL";
        RobotCollisions robotCollisions = new RobotCollisions();
        List<Integer> result = robotCollisions.survivedRobotsHealths(positions, healths, directions);
        System.out.println(result); // [2, 3]
    }
}