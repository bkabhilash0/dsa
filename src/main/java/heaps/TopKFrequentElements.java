package heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/top-k-frequent-elements">Top K Frequent Elements</a>
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 */
public class TopKFrequentElements {
    // Time Complexity: O(N) + O(NlogN) + O(k)
    // Space Complexity: O(N) + O(N) for the hashmap and the sorted list
    private int[] bruteForce(int[] nums, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        // Now iterate and sort the keys based on the frequency
        ArrayList<Integer> sortedKeys = new ArrayList<>(frequencyMap.keySet());
        // Sort with decreasing order
        sortedKeys.sort((a, b) -> Integer.compare(frequencyMap.get(b), frequencyMap.get(a)));
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = sortedKeys.get(i);
        }
        return result;
    }

    // Time Complexity: O(N) + O(NlogK) + O(KlogK)
    // Space Complexity: O(N) for the hashmap and O(k) for the heap
    private int[] better(int[] nums, int k) {
        class Pair {
            final int key;
            final int value;

            Pair(int key, int value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return String.format("(%d, %d)", key, value);
            }
        }
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        // We can use a min heap of size k as we want the top k elements with max frequency. So we will remove the minimum frequency element when the size exceeds k
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
        for (Integer key : frequencyMap.keySet()) {
            pq.add(new Pair(key, frequencyMap.get(key)));
            if (pq.size() > k) {
                pq.remove();
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.remove().key;
        }
        return result;
    }

    // Time Complexity: O(N) + O(N) + O(N)
    // Space Complexity: O(N) + O(N) for the hashmap and the buckets
    private int[] optimal(int[] nums, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        ArrayList<Integer>[] buckets = new ArrayList[nums.length + 1];

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create buckets, its basically like a array hashmap
        // The index represents the freq and values represents the numbers with that frequency
        for (Integer key : frequencyMap.keySet()) {
            int freq = frequencyMap.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        int[] result = new int[k];
        int index = 0;
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            // Check if that index element has any values
            if (buckets[i] != null) {
                // If that index has values, iterate and add them to the list until the result array is filled
                for (int num : buckets[i]) {
                    result[index++] = num;
                    if (index == k) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public int[] topKFrequent(int[] nums, int k) {
        return optimal(nums, k);
    }

    public static void main(String[] args) {
        TopKFrequentElements tkf = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = tkf.topKFrequent(nums, k);
        System.out.println(java.util.Arrays.toString(result));
    }
}
