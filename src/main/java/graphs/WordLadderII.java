package graphs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/word-ladder-ii/description/">Word Ladder 2</a>
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * Sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists.
 * Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 */
public class WordLadderII {
    // Time Complexity: O(N * M * No of Neighbors) where N is the number of words in the word list and M is the length of each word,
    // we are iterating through the queue and for each word we are generating all possible transformations by changing one character at a time and checking if it is in the word list
    // Space Complexity: O(N) + O(N) + O(N) for the queue and the set to store the filtered word list
    private List<List<String>> executeFindLadders(String beginWord, String endWord, List<String> dictionary) {
        List<List<String>> res = new ArrayList<>();
        Set<String> wordList = new HashSet<>(dictionary);
        wordList.add(beginWord);
        if (!wordList.contains(endWord)) return res;
        Map<String, List<String>> patternMap = new HashMap<>();

        // Create Pattern Map
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                patternMap.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        boolean found = false;
        queue.add(new ArrayList<>(List.of(beginWord)));
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            // Do level by level processing
            Set<String> usedWords = new HashSet<>();
            List<List<String>> newItems = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<String> words = queue.poll();
                // We only need the last word preset in the list
                String word = words.getLast();
                for (int j = 0; j < word.length(); j++) {
                    String pattern = word.substring(0, j) + "*" + word.substring(j + 1);
                    List<String> neighbors = patternMap.get(pattern);
                    if (neighbors == null) continue;
                    for (String newWord : neighbors) {
                        // The new word has to be in the wordList and not present in the visited array
                        if (newWord.equals(word)) continue;
                        if (!visited.contains(newWord)) {
                            List<String> newSequence = new ArrayList<>(words);
                            newSequence.add(newWord);
                            newItems.add(newSequence);
                            usedWords.add(newWord);
                            if (newWord.equals(endWord)) {
                                found = true;
                            }
                        }
                    }
                }
            }
            queue.addAll(newItems);
            visited.addAll(usedWords);
            if (found) break;
        }
        if (queue.isEmpty()) return res;
        int minSize = queue.peek().size();
        while (!queue.isEmpty()) {
            List<String> words = queue.poll();
            if (words.size() != minSize) {
                break;
            }
            if (words.getLast().equals(endWord)) {
                res.add(words);
            }
        }
        return res;
    }

    private void dfs(String word, String beginWord, List<String> path, Map<String, Set<String>> parentMap, List<List<String>> res) {
        if (word.equals(beginWord)) {
            // Create a copy of the path else, reference modification will cause issue
            List<String> pathCopy = new ArrayList<>(path);
            Collections.reverse(pathCopy);
            res.add(pathCopy);
            return;
        }

        if(!parentMap.containsKey(word)) return;

        // backtrack with the parent
        for (String parent : parentMap.get(word)) {
            path.add(parent);
            dfs(parent, beginWord, path, parentMap, res);
            path.removeLast();
        }
    }

    // Time Complexity: O(M * N * k) + O(num of Shortest Paths) : M -> Number of words, N -> Length of the word, k -> No of Neighbors
    // Time Complexity: O(N) -> Simplified
    private List<List<String>> parentMapMethod(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!dict.contains(endWord)) return res;
        Map<String, Set<String>> parentMap = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();

        queue.offer(beginWord);
        dict.remove(beginWord);
        // This is only used to check if the end word was found, we aren't terminating the loop based on this as we need to build parent map for words
        boolean found = false;
        while (!queue.isEmpty() && !found) {
            // Initialize a new queue for next level
            Set<String> levelVisited = new HashSet<>();
            int size = queue.size();
            for (int z = 0; z < size; z++) {
                String word = queue.poll();
                for (int i = 0; i < word.length(); i++) {
                    char[] chars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String newWord = new String(chars);
                        if (!dict.contains(newWord)) continue;
                        if (levelVisited.add(newWord)) {
                            queue.offer(newWord);
                        }
                        parentMap.computeIfAbsent(newWord, k -> new HashSet<>()).add(word);
                        if (newWord.equals(endWord)) {
                            found = true;
                        }
                        chars[i] = word.charAt(i);
                    }
                }
            }
            // Mark the all visited ones in the visited -> We can also remove it from the dict
            dict.removeAll(levelVisited);
        }
        if (!found) return res;

        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, beginWord, path, parentMap, res);
        return res;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        return parentMapMethod(beginWord, endWord, wordList);
    }

    public static void main(String[] args) {
        WordLadderII wordLadderII = new WordLadderII();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        List<List<String>> res = wordLadderII.findLadders(beginWord, endWord, wordList);
        System.out.println(res);
    }
}
