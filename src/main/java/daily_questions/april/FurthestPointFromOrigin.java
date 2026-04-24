package daily_questions.april;

/**
 * Date: April 24, 2026,
 * <a href="https://leetcode.com/problems/furthest-point-from-origin">Furthest Point from Origin</a>
 * You are given a string moves of length n consisting only of characters 'L', 'R', and '_'.
 * The string represents your movement on a number line starting from the origin 0.
 * In the ith move, you can choose one of the following directions:
 * move to the left if moves[i] = 'L' or moves[i] = '_'
 * move to the right if moves[i] = 'R' or moves[i] = '_'
 * Return the distance from the origin of the furthest point you can get to after n moves.
 */
public class FurthestPointFromOrigin {
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    private int intuition(String moves) {
        int ans1 = 0;
        int ans2 = 0;
        for (char move : moves.toCharArray()) {
            if (move == 'L') {
                ans1--;
                ans2--;
            } else if (move == 'R') {
                ans1++;
                ans2++;
            } else {
                ans1++;
                ans2--;
            }
        }
        return Math.max(ans1, ans2);
    }

    // Time Complexity: O(N)
    // Space Complexity: O(1)
    private int copiedSolution(String moves){
        int left = 0, right = 0, blanks = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'L') left++;
            else if (c == 'R') right++;
            else blanks++;
        }

        return Math.abs(left - right) + blanks;
    }

    public int furthestDistanceFromOrigin(String moves) {
        return copiedSolution(moves);
    }

    public static void main(String[] args) {
        FurthestPointFromOrigin solution = new FurthestPointFromOrigin();
        System.out.println(solution.furthestDistanceFromOrigin("L_RL__R")); // 3;
        System.out.println(solution.furthestDistanceFromOrigin("_R__LL_")); // 5;
    }
}
