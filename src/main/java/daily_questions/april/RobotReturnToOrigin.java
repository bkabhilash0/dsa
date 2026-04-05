package daily_questions.april;

/**
 * Date: April 5, 2026,
 * <a href="https://leetcode.com/problems/robot-return-to-origin">Robot Return to Origin</a>
 * There is a robot starting at the position (0, 0), the origin, on a 2D plane.
 * Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.
 * You are given a string moves that represents the move sequence of the robot where moves[i]
 * represents its ith move. Valid moves are 'R' (right), 'L' (left), 'U' (up), and 'D' (down).
 * Return true if the robot returns to the origin after it finishes all of its moves, or false otherwise.
 * Note: The way that the robot is "facing" is irrelevant.
 * 'R' will always make the robot move to the right once,
 * 'L' will always make it move left, etc.
 * Also, assume that the magnitude of the robot's movement is the same for each move.
 */
public class RobotReturnToOrigin {
    // Time Complexity: O(N) where N is total number of moves
    // Space Complexity: O(1)
    public boolean judgeCircle(String moves) {
        int startX = 0;
        int startY = 0;
        for (final char move : moves.toCharArray()) {
            switch (move) {
                case 'U':
                    startY++;
                    break;
                case 'D':
                    startY--;
                    break;
                case 'L':
                    startX--;
                    break;
                case 'R':
                    startX++;
                    break;
            }
        }
        return startX == 0 && startY == 0;
    }

    public static void main(String[] args) {
        RobotReturnToOrigin robotReturnToOrigin = new RobotReturnToOrigin();
        String moves = "UD";
        boolean result = robotReturnToOrigin.judgeCircle(moves);
        System.out.println("Robot return to origin: " + result);
    }
}
