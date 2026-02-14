package linked_list;

import linked_list.components.ListNode;

import java.util.ArrayList;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 */
public class RemoveNthNodeFromEndOfList {
    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list twice
    // Space Complex: O(n) since we are storing the values of the linked list in the array list, the size of the array list can grow up to n in the worst case
    private ListNode bruteForce(ListNode head, int n) {
        ArrayList<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }
        current = head;
        int indexToRemove = values.size() - n;
        if (indexToRemove == 0) {
            return head.next;
        }

        int index = 0;
        while (current.next != null) {
            if (index == indexToRemove - 1) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
            index++;
        }

        return head;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complex: O(1) since we are modifying the linked list in place, we are not using any extra space to store the values of the linked list
    private ListNode optimal(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        while (n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        ListNode slow = head;

        // Which means that we have to remove the head of the linked list as n == length of the linked list
        if (fast == null) {
            return head.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return optimal(head, n);
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList removeNthNodeFromEndOfList = new RemoveNthNodeFromEndOfList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
        int n = 2;
        ListNode result = removeNthNodeFromEndOfList.removeNthFromEnd(head, n);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}

