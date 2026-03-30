package daily_questions.march;

import java.util.Arrays;

/**
 * Date: March 30, 2026,
 * <a href="https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii">Check if Strings Can be Made Equal With Operations II</a>
 * You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.
 * You can apply the following operation on any of the two strings any number of times:
 * Choose any two indices i and j such that i < j and the difference j - i is even,
 * then swap the two characters at those indices in the string.
 * Return true if you can make the strings s1 and s2 equal, and false otherwise.
 */
public class CheckIfStringsCanBeMadeEqualWithOperations2 {
    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    // Time Complexity: O(2^N)
    // Space Complexity: O(2^N)
    private boolean execute(String s1, String s2, int index) {
        // Base case: processed all indices
        if (index >= s1.length()) {
            return s1.equals(s2);
        }
        // Early check (optional optimization)
        if (s1.equals(s2)) {
            return true;
        }
        // Try WITHOUT swapping at this index
        if (execute(s1, s2, index + 1)) {
            return true;
        }
        // Try all valid swaps (same parity only)
        for (int i = index + 1; i < s1.length(); i++) {
            // only same parity allowed
            if ((index % 2) != (i % 2)) {
                continue;
            }
            s1 = swap(s1, index, i);
            if (execute(s1, s2, index + 1)) {
                return true;
            }
            s1 = swap(s1, index, i);
        }

        return false;
    }

    // Time Complexity: O(N) + O(26)
    // Space Complexity: O(2*26) => O(1)
    private boolean better(String s1, String s2) {
        // Chars in odd indexes can only be swapped with odd indices
        // Chars in even indexes can only be swapped with even indices
        // So we can check if the chars in odd and even indices are same in both strings
        int[] oddCount1 = new int[26];
        int[] evenCount1 = new int[26];
//        int[] oddCount2 = new int[26];
//        int[] evenCount2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            if (i % 2 == 0) {
                evenCount1[s1.charAt(i) - 'a']++;
                evenCount1[s2.charAt(i) - 'a']--;
            } else {
                oddCount1[s1.charAt(i) - 'a']++;
                oddCount1[s2.charAt(i) - 'a']--;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (oddCount1[i] != 0 || evenCount1[i] != 0) {
                return false;
            }
        }
        return true;
        // Check if odd and even of both the string are equal, then it means that we can somehow swap and create the other string
//        return Arrays.equals(oddCount1, oddCount2) && Arrays.equals(evenCount1, evenCount2);
    }

    // Time Complexity: O(N) + O(26 * K) where k is the number of groups (j - i)
    // Space Complexity: O(26 * K) => O(1)
    private boolean optimal(String s1, String s2) {
        // Lets maintain a single matrix and generalise
        // Since here the j - i = 2 we have 2 groups either the odd or even
        // When its 3, we would have 3 groups, the numbers can only be swapped within their groups
        int k = 2; // j - i = 2
        int m = s1.length();
        // Each rows are each groups and each column is the count of each char in that group
        int[][] matrix = new int[k][26];
        for (int i = 0; i < m; i++) {
            int group = i % k;
            matrix[group][s1.charAt(i) - 'a']++;
            matrix[group][s2.charAt(i) - 'a']--;
        }

        // Go through all the groups and check if there is any unbalanced group
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 26; j++) {
                if (matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkStrings(String s1, String s2) {
        return optimal(s1, s2);
    }

    public static void main(String[] args) {
        CheckIfStringsCanBeMadeEqualWithOperations2 check = new CheckIfStringsCanBeMadeEqualWithOperations2();
//        System.out.println(check.checkStrings("abcdba", "cabdab"));
        System.out.println(check.checkStrings("ublnlasppynwgx", "ganplbuylnswpx"));
    }
}
