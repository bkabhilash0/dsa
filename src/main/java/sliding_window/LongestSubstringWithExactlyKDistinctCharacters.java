package sliding_window;

import java.util.HashMap;
import java.util.Set;

public class LongestSubstringWithExactlyKDistinctCharacters {
    // Time Complex: O(n^2) where n is the length of the string, we have two nested loops to check all possible substrings
    // Space Complex: O(k) in the worst case when all characters are unique, we will have to store all characters in the set
    private int bruteForce(String s, int k) {
        int res = -1;
        Set<Character> set = new java.util.HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (set.size() == k && !set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                if (set.size() == k) {
                    res = Math.max(res, j - i + 1);
                }
            }
            set.clear();
        }
        return res;
    }

    // Time Complex: O(n) where n is the length of the string
    // Space Complex: O(k) in the worst case when all characters are unique, we will have to store all characters in the map
    private int better(String s, int k) {
        int res = -1;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }
                left++;
            }
            if (map.size() == k) {
                res = Math.max(res, right - left + 1);
            }
        }
        return res;
    }

    // Time Complex: O(n) where n is the length of the string. Removed the inner while loop and replaced it with an if condition, since we only need to shrink the window once when the number of distinct characters exceeds k
    // Space Complex: O(k) in the worst case when all characters are unique, we will have to store all characters in the map
    private int optimal(String s, int k) {
        int res = -1;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.size() > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }
                left++;
            }
            if (map.size() == k) {
                res = Math.max(res, right - left + 1);
            }
        }
        return res;
    }

    public int longestKSubstr(String s, int k) {
        // code here
        return optimal(s, k);
    }

    public static void main(String[] args) {
        LongestSubstringWithExactlyKDistinctCharacters longestSubstringWithAtMostKDistinctCharacters = new LongestSubstringWithExactlyKDistinctCharacters();
        String s = "aabacbebebe";
        int k = 3;
        System.out.println(longestSubstringWithAtMostKDistinctCharacters.longestKSubstr(s, k));

    }
}
