package sliding_window;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/longest-repeating-character-replacement/description/">...</a>
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 */
public class LongestRepeatingCharacterReplacement {
    // Time Complex: O(n^2) where n is the length of the string, we are iterating through the string twice
    // Space Complex: O(1) since we are only storing the count of characters in the array, the size of the array will never exceed 26
    private int bruteForce(String s, int k) {
        int res = 0;
        int[] chars = new int[26];
        int maxFreq = 0;
        for (int i = 0; i < s.length(); i++) {
            chars = new int[26];
            maxFreq = 0;
            for (int j = i; j < s.length(); j++) {
                chars[s.charAt(j) - 'A'] += 1;
                int currentCharCount = chars[s.charAt(j) - 'A'];
                maxFreq = Math.max(maxFreq, currentCharCount);
                int windowSize = j - i + 1;
                int changesPossible = windowSize - maxFreq;
                if (changesPossible > k) {
                    break;
                }
                res = Math.max(res, windowSize);
            }
        }
        return res;
    }

    // Same as that of optimal but instead of if condition we use a while loop to shrink the window until we have changesPossible <= k,
    // this is a bit slower than the optimal solution since we are iterating through the string more than once in some cases
    private int better(String s, int k) {
        return 0;
    }

    // Time Complex: O(n) where n is the length of the string, we are iterating through the string once
    // Space Complex: O(1) since we are only storing the count of characters in the array, the size of the array will never exceed 26
    private int optimal(String s, int k) {
        int left = 0;
        int res = 0;
        int[] chars = new int[26];
        int maxFreq = 0;
        for (int right = 0; right < s.length(); right++) {
            chars[s.charAt(right) - 'A'] += 1;
            maxFreq = Math.max(maxFreq, chars[s.charAt(right) - 'A']);
            int windowSize = right - left + 1;
            int changesPossible = windowSize - maxFreq;
            if (changesPossible > k) {
                chars[s.charAt(left) - 'A'] -= 1;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int characterReplacement(String s, int k) {
        return optimal(s, k);
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement longestRepeatingCharacterReplacement = new LongestRepeatingCharacterReplacement();
        String s = "AABABBA";
        int k = 1;
        System.out.println(longestRepeatingCharacterReplacement.characterReplacement(s, k));
    }
}
