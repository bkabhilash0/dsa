package linked_list;

import linked_list.components.ListNode;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.com/problems/odd-even-linked-list/description/">...</a>
 * Given the head of a singly linked list, group all the nodes with odd indices
 * together followed by the nodes with even indices, and return the reordered list.
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 */
public class OddEvenLinkedList {
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Time Complexity: O(n) where n is the number of nodes in the linked list
    // Space Complexity: O(n) for the list that stores the values of the nodes in the linked list
    private ListNode bruteForce(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ArrayList<Integer> nodeValues = new ArrayList<>();
        ListNode current = head;
        while (current.next != null && current.next.next != null) {
            nodeValues.add(current.val);
            current = current.next.next;
        }
        nodeValues.add(current.val);

        current = head.next;
        while (current.next != null && current.next.next != null) {
            nodeValues.add(current.val);
            current = current.next.next;
        }
        nodeValues.add(current.val);

        current = head;
        while (current != null) {
            current.val = nodeValues.removeFirst();
            current = current.next;
        }
        return head;
    }

    // Time Complexity: O(n) where n is the number of nodes in the linked list
    // Space Complexity: O(1) since we are not using any extra space to store the values of the nodes in the linked list, we are just rearranging the pointers
    private ListNode optimal(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
//        printList(head);
        ListNode oddHead = head;
        ListNode evenHead = oddHead.next;
        ListNode originalEvenHead = evenHead;
        while (evenHead != null && evenHead.next != null) {
            oddHead.next = evenHead.next;
            oddHead = oddHead.next;
            evenHead.next = oddHead.next;
            evenHead = evenHead.next;
        }
        oddHead.next = originalEvenHead;
        return head;
    }

    public ListNode oddEvenList(ListNode head) {
        return optimal(head);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        OddEvenLinkedList oddEvenLinkedList = new OddEvenLinkedList();
        ListNode result = oddEvenLinkedList.oddEvenList(head);
        oddEvenLinkedList.printList(result);
    }
}
