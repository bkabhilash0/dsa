package linked_list;

import linked_list.components.ListNode;

/**
 * <a href="https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/">...</a>
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing,
 * where n is the size of the linked list. For example, if n = 1, 2, 3, 4, or 5, then the middle node index is 0, 1, 1, 2, or 2, respectively.
 */
public class DeleteTheMiddleNodeOfLinkedList {
    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list twice
    // Space Complex: O(1) since we are not using any extra space
    private ListNode bruteForce(ListNode head) {
        if(head == null || head.next == null) return null;
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        int mid = length / 2;
        current = head;
        // Don't go till the mid element, go till the element before the mid element
        while(mid > 1){
            mid--;
            current = current.next;
        }

        current.next = current.next.next;
        return head;
    }

    // Time Complex: O(n/2) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complex: O(1) since we are not using any extra space
    private ListNode optimal(ListNode head) {
        if(head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head.next.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public ListNode deleteMiddle(ListNode head) {
        return optimal(head);
    }

    public static void main(String[] args) {
        DeleteTheMiddleNodeOfLinkedList dtml = new DeleteTheMiddleNodeOfLinkedList();
        ListNode head = Utils.createList(5);
        Utils.printList(head);
        ListNode res = dtml.deleteMiddle(head);
        Utils.printList(res);
    }
}

