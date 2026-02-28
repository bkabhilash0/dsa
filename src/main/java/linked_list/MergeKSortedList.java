package linked_list;

import linked_list.components.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * <a href="https://leetcode.com/problems/merge-k-sorted-lists/">Merge K Sorted List</a>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class MergeKSortedList {
    // Time Complexity: O(N) + O(N log N) + O(N) = O(N log N) where N is the total number of nodes in all the linked lists, we are visiting each node once and then sorting them
    // Space Complexity: O(N) for the listNodes list
    private ListNode bruteForce(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ArrayList<ListNode> listNodes = new ArrayList<>();
        for (ListNode list : lists) {
            ListNode current = list;
            while (current != null) {
                listNodes.add(current);
                current = current.next;
            }
        }
        if (listNodes.isEmpty()) return null;
        listNodes.sort((a, b) -> Integer.compare(a.val, b.val));
        ListNode head = listNodes.removeFirst();
        ListNode tail = head;
        while (!listNodes.isEmpty()) {
            tail.next = listNodes.removeFirst();
            tail = tail.next;
        }
        return head;
    }

    // Time Complexity: O(N log k) where N is the total number of nodes in all the linked lists and k is the number of linked lists, we are visiting each node once and then adding it to the priority queue which takes log k time
    // Space Complexity: O(k) for the priority queue, in the worst
    private ListNode optimal(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        // Add the head of each linked list to the priority queue
        for (ListNode currentHead : lists) {
            if (currentHead != null) {
                pq.offer(currentHead);
            }
        }

        while (!pq.isEmpty()) {
            ListNode current = pq.poll();
            tail.next = current;
            tail = tail.next;
            if (current.next != null) {
                pq.offer(current.next);
            }
        }
        return dummy.next;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        return dummy.next;
    }

    // Time Complexity: O(N log k) where N is the total number of nodes in all the linked lists and k is the number of linked lists, we are visiting each node once and then merging them which takes log k time
    // Space Complexity: O(1) since we are not using any extra space
    // This is faster than the PQ
    private ListNode mergeSort(ListNode[] lists, int left, int right) {
        if (left > right) return null;
        if (left == right) return lists[left];
        int mid = (left + right) / 2;
        ListNode l1 = mergeSort(lists, left, mid);
        ListNode l2 = mergeSort(lists, mid + 1, right);
        return merge(l1, l2);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeSort(lists, 0, lists.length - 1);
    }

    public static void main(String[] args) {

    }
}
