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
        for(String word : wordList) {
            for(int i = 0; i < word.length(); i++) {
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
                    if(neighbors == null) continue;
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
            if(words.getLast().equals(endWord)) {
                res.add(words);
            }
        }
        return res;
    }

    private List<List<String>> executeMoreOptimal(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        Map<String, List<String>> parents = new HashMap<>();

        Set<String> current = new HashSet<>();
        current.add(beginWord);

        boolean found = false;

        while (!current.isEmpty() && !found) {

            dict.removeAll(current);
            Set<String> next = new HashSet<>();

            for (String word : current) {

                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {

                    char old = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {

                        chars[i] = c;
                        String newWord = new String(chars);

                        if (!dict.contains(newWord)) continue;

                        next.add(newWord);

                        parents
                                .computeIfAbsent(newWord, k -> new ArrayList<>())
                                .add(word);

                        if (newWord.equals(endWord))
                            found = true;
                    }

                    chars[i] = old;
                }
            }

            current = next;
        }

        if (!found) return res;

        List<String> path = new ArrayList<>();
        path.add(endWord);

        dfs(endWord, beginWord, parents, path, res);

        return res;
    }

    private void dfs(String word, String beginWord,
                     Map<String, List<String>> parents,
                     List<String> path,
                     List<List<String>> res) {

        if (word.equals(beginWord)) {
            List<String> copy = new ArrayList<>(path);
            Collections.reverse(copy);
            res.add(copy);
            return;
        }

        for (String parent : parents.getOrDefault(word, new ArrayList<>())) {
            path.add(parent);
            dfs(parent, beginWord, parents, path, res);
            path.remove(path.size() - 1);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        return executeMoreOptimal(beginWord, endWord, wordList);
    }

    public static void main(String[] args) {
        WordLadderII wordLadderII = new WordLadderII();
        String beginWord = "a";
        String endWord = "c";
        List<String> wordList = List.of("a","b","c");
        List<List<String>> res = wordLadderII.findLadders(beginWord, endWord, wordList);
        System.out.println(res);
    }
}
