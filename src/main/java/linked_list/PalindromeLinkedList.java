package linked_list;

import linked_list.components.ListNode;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/palindrome-linked-list/description/">...</a>
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 */
public class PalindromeLinkedList {
    // Time Complex: O(2n) where n is the number of nodes in the linked list, we are iterating through the linked twice,
    // once to push the values of the nodes into the stack and once to pop the values
    // Space Complex: O(n) where n is the number of nodes in the linked list, we are using a stack to store the values of the nodes,
    // the size of the stack will be equal to the number of nodes in the linked list
    private boolean bruteForce(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }
        current = head;
        while (current != null) {
            if (current.val == stack.pop()) {
                current = current.next;
            } else {
                return false;
            }
        }
        return true;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked once
    // Space Complex: O(1) since we are not using any extra space to store the values of the nodes, we are just using a few pointers to keep track of the current node
    private boolean optimal(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reversed = Utils.reverseList(slow);
        Utils.printList(reversed);
        ListNode current = head;
        while (reversed != null && current != null) {
            System.out.println("current: " + current.val + " reversed: " + reversed.val);
            if (current.val != reversed.val) {
                return false;
            }
            current = current.next;
            reversed = reversed.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        return optimal(head);
    }

    public static void main(String[] args) {
        PalindromeLinkedList pll = new PalindromeLinkedList();
        ListNode head = Utils.createPalindromeList(6);
        Utils.printList(head);
        System.out.println(pll.isPalindrome(head));
    }
}
