package sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/">...</a>
 * "Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c."
 */
public class NumberOfSubstringsContainingAll3Characters {
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
        return intuition(s);
    }

    public static void main(String[] args) {
        NumberOfSubstringsContainingAll3Characters numberOfSubstringsContainingAll3Characters = new NumberOfSubstringsContainingAll3Characters();
        String s = "abcabc";
        System.out.println(numberOfSubstringsContainingAll3Characters.numberOfSubstrings(s));
    }
}
