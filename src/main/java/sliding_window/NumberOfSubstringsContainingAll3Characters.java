package sliding_window;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/">...</a>
 * "Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c."
 */
public class NumberOfSubstringsContainingAll3Characters {
    // Time Complex: O(n^2) where n is the length of the string, we are iterating through the string twice
    // Space Complex: O(1) since we are only storing the count of characters a, b and c in the array, the size of the array will never exceed 3
    private int bruteForce(String s) {
        int[] chars = new int[3];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            chars = new int[3];
            for (int j = i; j < s.length(); j++) {
                chars[s.charAt(j) - 'a']++;
                if (chars[0] > 0 && chars[1] > 0 && chars[2] > 0) {
                    res++;
                }
            }
        }
        return res;
    }

    // Time Complex: O(n) where n is the length of the string, we are iterating through the string once
    // Space Complex: O(1) since we are only storing the count of characters a, b and c in the array, the size of the array will never exceed 3
    private int optimal(String s) {
        int res = 0;
        int[] chars = new int[3];
        Arrays.fill(chars, -1);
        for (int right = 0; right < s.length(); right++) {
            chars[s.charAt(right) - 'a'] = right;
            if (chars[0] >= 0 && chars[1] >= 0 && chars[2] >= 0) {
                // Math.min is bit slower
//                int minWindowStart = Math.min(chars[0], Math.min(chars[1], chars[2]));
                int minWindowStart = Integer.MAX_VALUE;
                for(int num: chars){
                    minWindowStart = Math.min(num, minWindowStart);
                }
                res += minWindowStart + 1;
            }
        }
        return res;
    }

    // Time Complex: O(n) where n is the length of the string, we are iterating through the string once
    // Space Complex: O(1) since we are only storing the count of characters a, b and c in the map, the size of the map will never exceed 3
    private int intuition(String s) {
        int left = 0;
        int res = 0;
        Map<Character, Integer> set = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            set.put(s.charAt(right), set.getOrDefault(s.charAt(right), 0) + 1);
            if (set.size() == 3) {
                res += s.length() - right;
            }
            // Now shrink the window until we have less than 3 characters
            while (set.size() == 3) {
                set.put(s.charAt(left), set.get(s.charAt(left)) - 1);
                if (set.get(s.charAt(left)) == 0) {
                    set.remove(s.charAt(left));
                } else {
                    res += s.length() - right;
                }
                left++;
            }
        }
        return res;
    }

    public int numberOfSubstrings(String s) {
        return optimal(s);
    }

    public static void main(String[] args) {
        NumberOfSubstringsContainingAll3Characters numberOfSubstringsContainingAll3Characters = new NumberOfSubstringsContainingAll3Characters();
        String s = "abcabc";
        System.out.println(numberOfSubstringsContainingAll3Characters.numberOfSubstrings(s));
    }
}
