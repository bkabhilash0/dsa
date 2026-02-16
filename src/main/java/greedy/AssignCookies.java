package greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/assign-cookies/description/">Assign Cookies</a>
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with;
 * and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 */
public class AssignCookies {
    // Time Complex: O(mlogm + nlogn) + O(min(m,n)) where m is the number of children and n is the number of cookies, we are sorting both the arrays
    // Space Complex: O(1) if we are sorting the arrays in place
    private int optimal(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        int m = g.length;
        int n = s.length;
        while (i < m && j < n) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }

    public int findContentChildren(int[] g, int[] s) {
        return optimal(g, s);
    }

    public static void main(String[] args) {
        AssignCookies ac = new AssignCookies();
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        System.out.println(ac.findContentChildren(g, s));
    }
}
