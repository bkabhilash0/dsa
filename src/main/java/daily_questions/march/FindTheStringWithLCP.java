package daily_questions.march;

import java.util.Arrays;

/**
 * Date: March 28, 2026,
 * <a href="https://leetcode.com/problems/find-the-string-with-lcp">Find the String with LCP</a>
 * We define the lcp matrix of any 0-indexed string word of n lowercase English letters as an n x n grid such that:
 * lcp[i][j] is equal to the length of the longest common prefix between the substrings word[i,n-1] and word[j,n-1].
 * Given an n x n matrix lcp, return the alphabetically smallest string word that corresponds to lcp.
 * If there is no such string, return an empty string.
 * A string a is lexicographically smaller than a string b (of the same length)
 * if in the first position where a and b differ, string a has a letter that
 * appears earlier in the alphabet than the corresponding letter in b.
 * For example, "aabd" is lexicographically smaller than "aaca"
 * because the first position they differ is at the third letter, and 'b' comes before 'c'.
 */
public class FindTheStringWithLCP {
    // Time Complexity: O(3 * n^2)
    // Space Complexity: O(N) + O(N^2)
    private String execute(int[][] lcp) {
        // lcp[0][2] states the lcp of word from 0 and word from 2
        // abab -> lcp[0][2] means the lcp of strings abab and ab
        // Here we note that if there is a lcp then we can surely say the first letter is always same
        // lcp[0][2] > 0 then s.charAt(0) and s.charAt(2) are same
        // By using this intuition lets recreate the word or say lets group it based on this

        int n = lcp.length;
        // The word will be the size of the lcp matrix
        char[] word = new char[n];
        Arrays.fill(word, '#');
        // The sequence goes from a-z so we start from a, row 0 is a, 1 is b and so on
        char ch = 'a';
        for (int i = 0; i < n; i++) {
            // Check if this character position is filled
            // If not filled then we have to find the positions for the ith word
            if (word[i] == '#') {
                // the character goes beyond z then its invalid
                if (ch > 'z') {
                    return "";
                }
                word[i] = ch;
                // We can also start from j = 0; but this is a symmetric matrix
                // lcp[0][1] = lcp[1][0] So we do not need to do repeated work
                // So we can consider only the top half the matrix so go from i to n
                for (int j = i + 1; j < n; j++) {
                    // If there is a lcp > 0 then it means the ith and jth character are same
                    // So we can say in the ith and jth position the words are same
                    if (lcp[i][j] > 0) {
                        word[j] = word[i];
                    }
                }
                // Once its found for this character we can move to the next character
                ch++;
            }
        }
        // Now we have recreated the word, but we will have to verify if its the right one
        // To do that we have to build the lcp for the word we created
        int[][] ourLcp = new int[n][n];
        // Do a bottom up DP
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    // If either of them are in the edge or the last character then the substring/lcp is 1
                    if (i == n - 1 || j == n - 1) {
                        ourLcp[i][j] = 1;
                    } else {
                        // The lcp of this character would be 1 + lcp of next position character
                        ourLcp[i][j] = ourLcp[i + 1][j + 1] + 1;
                    }
                }
            }
        }

        // Now Verify
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ourLcp[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return new String(word);
    }

    public String findTheString(int[][] lcp) {
        return execute(lcp);
    }

    public static void main(String[] args) {
        FindTheStringWithLCP obj = new FindTheStringWithLCP();
        System.out.println(obj.findTheString(new int[][]{{4, 0, 2, 0}, {0, 3, 0, 1}, {2, 0, 2, 0}, {0, 1, 0, 1}})); // "abab"
//        System.out.println(obj.findTheString(new int[][]{{3, 2, 1}, {2, 2, 0}, {1, 0, 1}})); // "aaa"
//        System.out.println(obj.findTheString(new int[][]{{2, 0}, {0, 1}})); // "ab"
//        System.out.println(obj.findTheString(new int[][]{{3, 2, 1}, {2, 2, 0}, {1, 0, 0}})); // ""
    }
}
