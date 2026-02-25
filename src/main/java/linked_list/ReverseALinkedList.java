package linked_list;

import linked_list.components.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/reverse-linked-list/description/">...</a>
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseALinkedList {
    // Time Complex: O(2n) where n is the number of nodes in the linked list, we are iterating through the linked twice,
    // once to push the values of the nodes into the stack and once to pop the values from the stack and assign them to the nodes
    // Space Complex: O(n) where n is the number of nodes in the linked list,
    // we are using a stack to store the values of the nodes, the size of the stack will be equal to the number of nodes in the linked list
    private ListNode bruteForce(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }

        current = head;
        while (current != null) {
            current.val = stack.pop();
            current = current.next;
        }
        return head;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked once
    // Space Complex: O(1) since we are not using any extra space to store the values of the nodes, we are just using a few pointers to keep track of the current node
    private ListNode optimal(ListNode head) {
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

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked once
    // Space Complex: O(n) where n is the number of nodes in the linked list, we are using the call stack to store the recursive calls,
    // the maximum depth of the recursion will be equal to the number of nodes in the linked list
    private ListNode recursive(ListNode head, ListNode current) {
        //Utils.printList(current);
        if (current == null || current.next == null) {
            return current;
        }

        ListNode reversed = recursive(head, current.next);
        current.next.next = current;
        current.next = null;
        return reversed;
    }

    public ListNode reverseList(ListNode head) {
        return recursive(head, head);
    }

    public static void main(String[] args) {
        ReverseALinkedList ral = new ReverseALinkedList();
        ListNode head = Utils.createList(5);
//        Utils.printList(head);
        ListNode res = ral.reverseList(head);
        Utils.printList(res);
    }
}
