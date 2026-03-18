package strings;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/group-anagrams/description/">Group Anagrams</a>
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 */
public class GroupAnagrams {
    // Time Complexity: O(3 * K) where k is the size of the strings
    // Space Complexity: O(26)
    private boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            count[str1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < str2.length(); i++) {
            count[str2.charAt(i) - 'a']--;
        }
        for (int j : count) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(N^2 * K) where N is the number of strings and K is the size of the strings,
    // we are iterating through the array of strings and
    // for each string we are comparing it with all the other strings to check if they are anagrams
    // Space Complexity: O(N) for the taken set
    private List<List<String>> bruteForce(String[] strs, int n) {
        HashSet<String> taken = new HashSet<>();
        List<List<String>> result = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (taken.contains(strs[i])) {
                continue;
            }
            List<String> list = new java.util.ArrayList<>();
            list.add(strs[i]);
            taken.add(strs[i]);
            for (int j = i + 1; j < n; j++) {
                if (isAnagram(strs[i], strs[j])) {
                    // Add to the same list
                    list.add(strs[j]);
                    // Mark it already taken into a list
                    taken.add(strs[j]);
                }
            }
            result.add(list);
        }
        return result;
    }

    // Time Complexity: O(N * k log k) + (N)
    // Space Complexity: O(N) for the map
    private List<List<String>> better(String[] strs, int n) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sortedStr = new String(arr);
            map.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    // Time Complexity: O(2k)
    // Space Complexity: O(26) for the count array and O(k) for the StringBuilder
    private String getCountKey(String str) {
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                String key = (char)(i + 'a') + "" + count[i];
                sb.append(key);
            }
        }
        return sb.toString();
    }

    // Time Complexity: O(N*k) Better than Sorting
    // Space Complexity: O(N) for the map
    private List<List<String>> optimal(String[] strs, int n) {
        List<List<String>> result = new java.util.ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            String countKey = getCountKey(str);
            map.computeIfAbsent(countKey, k -> new ArrayList<>()).add(str);
        }
        for (String key : map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        return optimal(strs, strs.length);
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams ga = new GroupAnagrams();
        System.out.println(ga.groupAnagrams(strs)); // [["bat"],["nat","tan"],["ate","eat","tea"]]
    }
}
