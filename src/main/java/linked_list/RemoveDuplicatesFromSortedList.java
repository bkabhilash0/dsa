package linked_list;

import linked_list.components.ListNode;
import org.w3c.dom.NodeList;

/**
 * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/">Remove Duplicates from Sorted List</a>
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 */
public class RemoveDuplicatesFromSortedList {
    // Time Complexity: O(n)
    // Space Complexity: O(1) No Extra Space taken
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode current = head.next;
        while (current != null) {
            if (current.val != prev.val) {
                prev.next = current;
                prev = current;
            }
            current = current.next;
        }
        prev.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-1, new ListNode(-1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4)))))));
        RemoveDuplicatesFromSortedList rdfs = new RemoveDuplicatesFromSortedList();
        ListNode res = rdfs.deleteDuplicates(head);
        Utils.printList(res);
    }
}
