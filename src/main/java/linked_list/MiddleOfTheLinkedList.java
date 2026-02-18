package linked_list;

import linked_list.components.ListNode;

/**
 * <a href="https://leetcode.com/problems/middle-of-the-linked-list/description/">...</a>
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 */
public class MiddleOfTheLinkedList {
    // Time Complex: O(n) where n is the number of nodes in the linked list
    // Space Complex: O(1) since we are not using any extra space to store the values of the nodes
    private ListNode bruteForce(ListNode head) {
        int len = 0;
        ListNode current = head;
        while (current != null) {
            len++;
            current = current.next;
        }
        int position = len / 2;
        current = head;
        while (position > 0) {
            current = current.next;
            position--;
        };
        return current;
    }

    // Time Complex: O(n/2) ~O(n) where n is the number of nodes in the linked list, we are iterating through the linked once
    // Space Complex: O(1) since we are not using any extra space to store
    private ListNode optimal(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode middleNode(ListNode head) {
        return bruteForce(head);
    }

    public static void main(String[] args) {
        MiddleOfTheLinkedList motl = new MiddleOfTheLinkedList();
        ListNode head = Utils.createList(6);
        Utils.printList(head);
        ListNode res = motl.middleNode(head);
        System.out.println(res.val);
    }
}
