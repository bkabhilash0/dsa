package linked_list;

import linked_list.components.ListNode;

/**
 * <a href="https://leetcode.com/problems/swap-nodes-in-pairs/description/">Swap Nodes in Pairs</a>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 */
public class SwapNodesInPairs {
    // Time Complexity: O(n) where n is the no of nodes in the linked list
    // Space Complexity: O(1) No Extra Space taken
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode current = head;
        while (current != null && current.next != null) {
            ListNode nextOfNext = current.next.next;
            ListNode second = current.next;

            second.next = current;
            current.next = nextOfNext;
            prev.next = second;
            prev = current;

            current = nextOfNext;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        SwapNodesInPairs snp = new SwapNodesInPairs();
        ListNode res = snp.swapPairs(head);
        Utils.printList(res);
    }
}
