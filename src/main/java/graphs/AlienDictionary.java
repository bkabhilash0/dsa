package graphs;

import java.util.*;

/**
 * <a href="https://www.geeksforgeeks.org/problems/alien-dictionary/1">Alien Dictionary</a>
 * A new alien language uses the English alphabet, but the order of letters is unknown.
 * You are given a list of words[] from the alien language’s dictionary, where the words are claimed to be sorted lexicographically according to the language’s rules.
 * Your task is to determine the correct order of letters in this alien language based on the given words.
 * If the order is valid, return a string containing the unique letters in lexicographically increasing order as per the new language's rules.
 * If there are multiple valid orders, return any one of them.
 * However, if the given arrangement of words is inconsistent with any possible letter ordering, return an empty string ("").
 * A string a is lexicographically smaller than a string b if, at the first position where they differ,
 * the character in a appears earlier in the alien language than the corresponding character in b.
 * If all characters in the shorter word match the beginning of the longer word, the shorter word is considered smaller.
 * Note: Your implementation will be tested using a driver code.
 * It will print true if your returned order correctly follows the alien language’s lexicographic rules; otherwise, it will print false.
 */
public class AlienDictionary {
    private ArrayList<Set<Integer>> createGraph(String[] words) {
        ArrayList<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            graph.add(new HashSet<>());
        // build edges
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            int min = Math.min(w1.length(), w2.length());
            boolean found = false;

            for (int j = 0; j < min; j++) {

                int c1 = w1.charAt(j) - 'a';
                int c2 = w2.charAt(j) - 'a';

                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    found = true;
                    break;
                }
            }

            // invalid prefix case
            if (!found && w1.length() > w2.length())
                return null;
        }

        return graph;
    }

    private String topoSortUsingBFS(String[] words) {
        // code here
        ArrayList<Set<Integer>> adj = createGraph(words);
        if (adj == null) {
            return ""; // Invalid prefix case
        }
        int uniqueChars = 0;
        boolean[] chars = new boolean[26];
        StringBuilder res = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        int[] inDeg = new int[26];

        // Mark the characters that are present in the words
        for (String word : words) {
            for (char c : word.toCharArray()) {
                chars[c - 'a'] = true;
            }
        }

        // Get the count of unique characters
        for (boolean aChar : chars) {
            if (aChar) {
                uniqueChars++;
            }
        }

        // Find the indegree of each vertex
        for (Set<Integer> neighbors : adj) {
            for (int neighbor : neighbors) {
                inDeg[neighbor]++;
            }
        }

        // Get all vertices with indegree 0 and add them to the queue
        for (int i = 0; i < inDeg.length; i++) {
            if (inDeg[i] == 0 && chars[i]) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            res.append((char) ('a' + node));
            for (int neighbor : adj.get(node)) {
                inDeg[neighbor]--;
                if (inDeg[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        if(res.length() != uniqueChars){
            return ""; // If we cannot visit all the vertices, then there is a cycle in the graph and we can return an empty string
        }
        return res.toString();
    }

    private boolean topoSortUsingDFS(int node, ArrayList<Set<Integer>> adj, boolean[] visited, boolean[] path, Deque<Integer> stack) {
        visited[node] = true;
        path[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (topoSortUsingDFS(neighbor, adj, visited, path, stack)) {
                    return true; // Cycle detected
                }
            } else if (path[neighbor]) {
                return true; // Cycle detected
            }
        }
        path[node] = false;
        stack.push(node);
        return false;
    }

    private String topoSortUsingDFS(String[] words) {
        // code here
        int[] chars = new int[26];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                chars[c - 'a'] = 1;
            }
        }
        boolean[] visited = new boolean[26];
        boolean[] path = new boolean[26];
        Deque<Integer> stack = new ArrayDeque<>();
        ArrayList<Set<Integer>> adj = createGraph(words);
        if (adj == null) {
            return ""; // Invalid prefix case
        }
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i] && chars[i] == 1) {
                if (topoSortUsingDFS(i, adj, visited, path, stack)) {
                    return ""; // If we find a cycle in the graph, then we cannot perform topological sort and we can return an empty string
                }
            }
        }
        List<Integer> topoSort = new ArrayList<>();
        while (!stack.isEmpty()) {
            topoSort.add(stack.pop());
        }
        return mapIndicesToCharacters(topoSort);
    }

    private String mapIndicesToCharacters(List<Integer> indices) {
        StringBuilder sb = new StringBuilder();
        for (int index : indices) {
            sb.append((char) ('a' + index));
        }
        return sb.toString();
    }

    // N = number of words
    // L = average length of each word
    // C = number of unique characters (≤ 26) - Nodes
    // E = number of edges (ordering relations)
    // Time Complexity: O(NL + C + E)
    // Space Complexity: O(C + E)
    public String findOrder(String[] words) {
        return topoSortUsingBFS(words);
    }

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        String[] words = {"dddc", "a", "ad", "ab", "b", "be", "cd", "cded"};
        System.out.println(ad.findOrder(words)); // Output: "bdac"
    }
}
