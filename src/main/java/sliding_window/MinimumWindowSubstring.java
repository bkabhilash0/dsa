package sliding_window;

/**
 * <a href="https://leetcode.com/problems/minimum-window-substring/description/">...</a>
 * Given two strings s and t of lengths m and n respectively,
 * return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 */
public class MinimumWindowSubstring {
    private int[] initializeMap(String t) {
        int[] map = new int[128];
        char[] chars = t.toCharArray();
        for (char aChar : chars) {
            map[aChar]++;
        }
        return map;
    }

    // Time Complex: O(n^2)
    // Space Complex: O(1) as the size of the map array is fixed at 128
    private String bruteForce(String s, String t) {
        int[] map;
        char[] chars = s.toCharArray();
        int count = 0;
        int requiredCount = t.length();
        int resultLength = Integer.MAX_VALUE;
        int startIndex = -1;

        for (int i = 0; i < chars.length; i++) {
            map = initializeMap(t);
            count = 0;
            for (int j = i; j < chars.length; j++) {
                // When adding new element we decrease the count
                // We kind of do a reverse lookup
                // When the count is greater than 0 it means that we have found a required character
                if (map[chars[j]] > 0) {
                    count++;
                }
                map[chars[j]]--;
                if (count == requiredCount) {
                    System.out.println("Found a valid substring: " + s.substring(i, j + 1));
                    int currentLength = j - i + 1;
                    if (currentLength < resultLength) {
                        resultLength = currentLength;
                        startIndex = i;
                    }
                    break;
                }
            }
        }
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + resultLength);
    }

    // Time Complex: O(n)
    // Space Complex: O(1) as the size of the map array is fixed at 128
    private String optimal(String s, String t) {
        int[] map = initializeMap(t);
        char[] chars = s.toCharArray();
        int count = 0;
        int requiredCount = t.length();
        int resultLength = Integer.MAX_VALUE;
        int startIndex = -1;
        int left = 0;

        for (int right = 0; right < chars.length; right++) {
            // When adding new element we decrease the count
            // We kind of do a reverse lookup
            // When the count is greater than 0 it means that we have found a required character
            if (map[chars[right]] > 0) {
                count++;
            }
            // When we add a character to the window we decrease the count in the map
            map[chars[right]]--;
            while (count == requiredCount) {
                int currentLength = right - left + 1;
                if (currentLength < resultLength) {
                    resultLength = currentLength;
                    startIndex = left;
                }
                // Remove the leftmost character from the window, by incrementing the left pointer and increasing the count in the map
                map[chars[left]]++;
                // When we remove a character from the window we increase the count in the map
                // When the count is greater than 0 it means that we have removed a required character
                // So we decrease the count of valid characters in the window or the distinct characters in the window
                if (map[chars[left]] > 0) {
                    count--;
                }
                left++;
            }
        }
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + resultLength);
    }

    public String minWindow(String s, String t) {
        return optimal(s, t);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minimumWindowSubstring.minWindow(s, t));
    }
}
