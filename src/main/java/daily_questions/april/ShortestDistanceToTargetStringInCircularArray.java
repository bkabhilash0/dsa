package daily_questions.april;

/**
 * Date: April 15, 2026,
 * <a href="https://leetcode.com/problems/shortest-distance-to-target-string-in-a-circular-array">Shortest Distance to Target String in a Circular Array</a>
 * You are given a 0-indexed circular string array words and a string target.
 * A circular array means that the array's end connects to the array's beginning.
 * Formally, the next element of words[i] is words[(i + 1) % n] and
 * the previous element of words[i] is words[(i - 1 + n) % n], where n is the length of words.
 * Starting from startIndex, you can move to either the next word or the previous word with 1 step at a time.
 * Return the shortest distance needed to reach the string target. If the string target does not exist in words, return -1.
 */
public class ShortestDistanceToTargetStringInCircularArray {
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    // This will break the circular behaviour
    private int intuition(String[] words, String target, int startIndex) {
        // Check both the half of the start Index
        int leftHalf = Integer.MAX_VALUE;
        int rightHalf = Integer.MAX_VALUE;
        for (int i = 0; i < startIndex; i++) {
            if (words[i].equals(target)) {
                leftHalf = Math.min(leftHalf, Math.abs(i - startIndex));
            }
        }

        for (int i = startIndex + 1; i < words.length; i++) {
            if (words[i].equals(target)) {
                rightHalf = Math.min(rightHalf, Math.abs(i - startIndex));
            }
        }
        if (leftHalf == Integer.MAX_VALUE && rightHalf == Integer.MAX_VALUE) {
            return -1;
        }
        return Math.min(leftHalf, rightHalf);
    }

    // Time Complexity: O(N)
    // Space Complexity: O(1)

    /**
     *
     * @param words
     * @param target
     * @param startIndex
     * @return int
     * This it like a clock or cake, the startIndex is 3 and we found the value at 1. Now slice the gap from 1-3 so we remove 1.1 to 2.9 so 2 part gone
     * Now the clock will have 2 parts less so the distance would be 12-2 when we try to now reach 1 from the 3
     */
    private int optimal(String[] words, String target, int startIndex) {
        int n = words.length;
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            if(words[i].equals(target)) {
                int clockWiseDistance = Math.abs(i - startIndex);
                int antiClockWiseDistance = n - clockWiseDistance;
                int minDistance = Math.min(clockWiseDistance, antiClockWiseDistance);
                result = Math.min(result, minDistance);
            }
        }
        return Integer.MAX_VALUE == result ? -1 : result;
    }

    public int closestTarget(String[] words, String target, int startIndex) {
        return optimal(words, target, startIndex);
    }

    public static void main(String[] args) {
        ShortestDistanceToTargetStringInCircularArray solution = new ShortestDistanceToTargetStringInCircularArray();
        System.out.println(solution.closestTarget(new String[]{"hello", "i", "am", "leetcode"}, "hello", 1)); // 1
        System.out.println(solution.closestTarget(new String[]{"a", "b", "leetcode"}, "leetcode", 0)); // 1
        System.out.println(solution.closestTarget(new String[]{"a", "b", "c"}, "d", 0)); // -1
    }
}
