package sliding_window;

import java.util.Arrays;

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 */
public class LengthOfLongestSubstringWithoutRepeatingCharacters {
    // Time Complex: O(n^2)
    // Space Complex: O(1)
    private int bruteForce(String s) {
        int[] chars = new int[128];
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            chars = new int[128];
            for (int j = i; j < s.length(); j++) {
                if (chars[s.charAt(j)] == 1) {
                    break;
                }
                chars[s.charAt(i)] = 1;
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }

    // Time Complex: O(n)
    // Space Complex: O(1) as the size of the chars array is fixed at 128
    private int optimal(String s) {
        int l = 0;
        int r = 0;
        int maxLength = 0;
        int[] chars = new int[128];
        Arrays.fill(chars, -1);
        while (r < s.length()) {
            if (chars[s.charAt(r)] != -1) {
                l = Math.max(chars[s.charAt(r)] + 1, l);
            }
            chars[s.charAt(r)] = r;
            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring(String s) {
        return optimal(s);
    }

    public static void main(String[] args) {
        LengthOfLongestSubstringWithoutRepeatingCharacters lengthOfLongestSubarray = new LengthOfLongestSubstringWithoutRepeatingCharacters();
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubarray.lengthOfLongestSubstring(s));
    }
}
