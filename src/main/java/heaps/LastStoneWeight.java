package heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * We are playing a game with the stones.
 * On each turn, we choose the heaviest two stones and smash them together.
 * Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 * Return the weight of the last remaining stone. If there are no stones left, return 0.
 */
public class LastStoneWeight {
    // Time Complexity: O(N) + O(NlogN) => N times we are inserting and removing
    // Space Complexity: O(N)
    private int optimal(int[] stones) {
        // By default its a min Heap, adding reverse comparator to make it a max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            pq.add(stone);
        }
        while (pq.size() > 1) {
            int stone1 = pq.remove();
            int stone2 = pq.remove();
            int diff = stone1 - stone2;
            if (diff > 0) {
                pq.add(diff);
            }
        }
        return pq.isEmpty() ? 0 : pq.remove();
    }

    public int lastStoneWeight(int[] stones) {
        return optimal(stones);
    }

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};
        LastStoneWeight lsw = new LastStoneWeight();
        System.out.println(lsw.lastStoneWeight(stones));
    }
}
