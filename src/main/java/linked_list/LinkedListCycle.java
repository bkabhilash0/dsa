package linked_list;

import linked_list.components.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/linked-list-cycle/description/">Linked List Cycle</a>
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 */
public class LinkedListCycle {
    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complex: O(n) where n is the number of nodes in the linked list
    private boolean bruteForce(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) {
                return true;
            }
            visited.add(current);
            current = current.next;
        }
        return false;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complex: O(1) since we are not using any extra space to store
    private boolean optimal(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle(ListNode head) {
        return bruteForce(head);
    }

    public static void main(String[] args) {
        LinkedListCycle llc = new LinkedListCycle();
        ListNode head = Utils.createList(5);
        Utils.printList(head);
        // Create a cycle in the linked list
        head.next.next.next.next.next = head.next;
        boolean res = llc.hasCycle(head);
        System.out.println(res);
    }
}
