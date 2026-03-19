package linked_list;

import linked_list.components.ListNode;

/**
 * <a href="https://leetcode.com/problems/reverse-linked-list-ii/description/">Reverse Linked List 2</a>
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 */
public class ReverseLinkedListII {
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // Time Complexity: O(n) + O(k) where k is the range to be reversed
    // Space Complexity: O(1)
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        int count = 0;
        ListNode current = head;
        // Get the start node
        while (current != null) {
            count++;
            if (count == left) {
                break;
            }
            prev.next = current;
            prev = current;
            current = current.next;
        }
        // Current starts from the left node
        ListNode start = current;
        // Get the end node
        while (current != null && count < right) {
            count++;
            current = current.next;
        }
        ListNode end = current;
        // Store the nodes after the end
        ListNode next = end.next;
        end.next = null;
        // Reverse the start to end nodes and attach it to the prev node of the actual start
        prev.next = reverse(start);
        // Set the start node next to the remaining nodes after the actual end node
        start.next = next;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int left = 2;
        int right = 4;
        ReverseLinkedListII rlli = new ReverseLinkedListII();
        ListNode res = rlli.reverseBetween(head, left, right);
        Utils.printList(res);
    }
}
