package linked_list;

import linked_list.components.ListNode;

public class Utils {
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode createList(int size) {
        ListNode head = new ListNode(1);
        ListNode current = head;
        for (int i = 2; i <= size; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        return head;
    }

    public static ListNode createPalindromeList(int size) {
        ListNode head = new ListNode(1);
        ListNode current = head;
        for (int i = 2; i <= size; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        for (int i = size - 1; i >= 1; i--) {
            current.next = new ListNode(i);
            current = current.next;
        }
        return head;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked once
    // Space Complex: O(1) since we are not using any extra space to store the values of the nodes, we are just using a few pointers to keep track of the current node
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode front;
        while (current != null) {
            front = current.next;
            current.next = prev;
            prev = current;
            current = front;
        }
        return prev;
    }
}
