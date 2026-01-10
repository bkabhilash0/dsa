package arrays;

/**
 * <a href="https://leetcode.com/problems/longest-common-prefix/description/">...</a>
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * Example:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] str) {
        if(str.length == 0){
            return "";
        }
        String pre = str[0];
        for (String s : str) {
            while (!s.startsWith(pre)) {
                pre = pre.substring(0, pre.length() - 1);
//                System.out.printf("Current element: %s and Current prefix: %s%n", s, pre);
            }
        }
        return pre;
    }

    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        String[] str = {"flower","flow","flight"};
        String result = lcp.longestCommonPrefix(str);
        System.out.println("Longest Common Prefix: " + result);
    }
}
