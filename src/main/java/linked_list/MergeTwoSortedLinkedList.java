package linked_list;

import jdk.jshell.execution.Util;
import linked_list.components.ListNode;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.com/problems/merge-two-sorted-lists/description/">Merge two sorted list</a>
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 */
public class MergeTwoSortedLinkedList {
    // Time Complexity: Let m+n be N, O(N) + O(NlogN) + O(N) = O(NlogN)
    // Space Complexity: O(N) where N is the total number of nodes in the two linked lists
    private ListNode bruteForce(ListNode list1, ListNode list2) {
        ArrayList<Integer> values = new ArrayList<>();
        ListNode current1 = list1;
        while (current1 != null) {
            values.add(current1.val);
            current1 = current1.next;
        }
        ListNode current2 = list2;
        while (current2 != null) {
            values.add(current2.val);
            current2 = current2.next;
        }
        values.sort(Integer::compareTo);
        ListNode result = new ListNode(values.get(0));
        ListNode resultHead = result;
        for (int i = 1; i < values.size(); i++) {
            result.next = new ListNode(values.get(i));
            result = result.next;
        }
        return resultHead.next != null ? resultHead.next : null;
    }

    // Time Complexity: O(m + n) where m and n are the number of nodes in the two linked lists, we are iterating through both linked lists once
    // Space Complexity: O(1) since we are not using any extra space
    private ListNode optimal(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode dummyHead = dummy;
        ListNode current1 = list1;
        ListNode current2 = list2;
        ListNode current = dummy;
        while (current1 != null && current2 != null) {
            if (current1.val < current2.val) {
                current.next = current1;
                current1 = current1.next;
            } else {
                current.next = current2;
                current2 = current2.next;
            }
            current = current.next;
        }
        if(current1 != null) current.next = current1;
        if(current2 != null) current.next = current2;
        return dummyHead.next != null ? dummyHead.next : null;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return optimal(list1, list2);
    }

    public static void main(String[] args) {
        MergeTwoSortedLinkedList mtsll = new MergeTwoSortedLinkedList();
        ListNode list1 = Utils.createList(3);
        ListNode list2 = Utils.createList(10);
        ListNode result = mtsll.mergeTwoLists(list1, list2);
        Utils.printList(result);
    }
}
