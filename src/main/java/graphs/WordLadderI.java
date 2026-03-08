package graphs;

import java.util.*;

/**
 * <a href="https://www.geeksforgeeks.org/problems/word-ladder/1">Word Ladder I</a>
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * Note that all the words in the wordlist have the same length of the beginWord and endWord.
 */
public class WordLadderI {
    // Time Complexity: O(N * M * 26) where N is the number of words in the word list and M is the length of each word,
    // we are iterating through the queue and for each word we are generating all possible transformations by changing one character at a time and checking if it is in the word list
    // Space Complexity: O(N) + O(N) for the queue and the set to store the filtered word list
    private int solveWordLadder(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 1;
        Set<String> filteredWordList = new HashSet<>(wordList);
        Queue<String> queue = new ArrayDeque<String>();
        queue.add(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // Lets do a level wise traversal of the queue, so that we can keep track of the number of transformations in the sequence
            // We will process all the words in the current level before moving to the next level,
            // so that we can increment the level count only after processing all the words in the current level
            for (int z = 0; z < size; z++) {
                // All the words polled inside this loop are from the same level, so we can generate all the possible transformations for these words and add them to the queue for the next level
                String word = queue.poll();
                char[] wordChars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[i] = c;
                        String newWord = new String(wordChars);
                        if (!filteredWordList.contains(newWord)) continue;
                        // If this is the end word and it is in the word list, we can return the count of the transformation sequence
                        if (newWord.equals(endWord))
                            return level + 1; // We are adding 1 because the new word would be in the ne
                        // If this is the end word but it is not in the word list, we can ignore it and continue with the next transformation
                        queue.add(newWord);
                        filteredWordList.remove(newWord);

                    }
                    wordChars[i] = word.charAt(i);
                }
            }
            level++;
        }
        return 0;
    }

    // The intution here is that we create a pattern for all the words in the wordlist
    // When a word comes and we need to change the first character, rather than checking with 26 characters and also checking if its in the wordlist
    // We now have a pattern starting with * and we can substitute the first character with * and check if there are any words in the wordlist matching this pattern,
    // this will reduce the time complexity of checking for all the transformations for a word from O(26) to O(1) since we can directly get the list of words matching the pattern from the map
    // Time Complexity: O(N * M) where N is the number of words in the word list and M is the length of each word
    // Space Complexity: O(N * M) for the pattern map and O(N) for the queue and the visited list
    private int solveWordLadderUsingPatternMatching(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 1;
        // Since all the words have the same length of the begin word we shall use this
        int wordLength = beginWord.length();
        Map<String, List<String>> patternMap = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        // We are using a visited Set rather
        Set<String> visited = new HashSet<>();
        int level = 1;
        // Pattern as the key and the words matching the pattern as the value, for example for the word "hot" we will have the following patterns and their corresponding words in the map:
        // *ot -> [hot, dot, lot]
        // h*t -> [hot]
        for (String word : wordList) {
            for (int i = 0; i < wordLength; i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                patternMap.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        queue.add(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] chars = word.toCharArray();
                for (int pos = 0; pos < wordLength; pos++) {
                    chars[pos] = '*';
                    // Example: for the word "hit" and pos = 0, the pattern will be "*it", for pos = 1, the pattern will be "h*t" and for pos = 2, the pattern will be "hi*"
                    String pattern = new String(chars);
                    // Now we have the pattern to wordList Mapping and we can directly get the respective words rather than 26 transformations and checks
                    // Lets call the realted words as neighbors
                    List<String> neighbors = patternMap.get(pattern);
                    if(neighbors == null) {
                        chars[pos] = word.charAt(pos);
                        continue;
                    };
                    for (String neighbor : neighbors) {
                        if (neighbor.equals(endWord)) return level + 1;
                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                    patternMap.remove(pattern);
                    chars[pos] = word.charAt(pos);
                }
            }
            level++;
        }

        return 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return solveWordLadderUsingPatternMatching(beginWord, endWord, wordList);
    }

    private static void runTest(WordLadderI wl, String begin, String end, List<String> list, int expected) {
        int result = wl.ladderLength(begin, end, list);
        System.out.println("Begin: " + begin +
                ", End: " + end +
                ", Output: " + result +
                ", Expected: " + expected);
    }

    public static void main(String[] args) {
        WordLadderI wl = new WordLadderI();
        runTest(wl, "hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"), 5);
        runTest(wl, "hit", "cog", List.of("hot", "dot", "dog", "lot", "log"), 0);
        runTest(wl, "a", "c", List.of("a", "b", "c"), 2);
        runTest(wl, "lost", "cost", List.of("most", "fist", "lost", "cost", "fish"), 2);
        runTest(wl, "talk", "tail", List.of("talk", "tons", "fall", "tail", "gale", "hall", "negs"), 0);
    }
}
