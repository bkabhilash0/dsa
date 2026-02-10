package linked_list;

import linked_list.components.ListNode;

/**
 * <a href="https://leetcode.com/problems/add-two-numbers/description/https://leetcode.com/problems/add-two-numbers/description/">...</a>
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class AddTwoNumbers {
    // Time Complexity: O(max(m, n)) where m and n are the lengths of the two linked lists
    // Space Complexity: O(max(m, n)) for the result linked list
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int sum = 0;
        ListNode l1Head = l1;
        ListNode l2Head = l2;
        ListNode result = new ListNode(0);
        ListNode resultHead = result;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            carry = sum / 10;
            result.next = new ListNode(sum % 10);
            result = result.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            sum = l1.val + carry;
            carry = sum / 10;
            result.next = new ListNode(sum % 10);
            result = result.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            sum = l2.val + carry;
            carry = sum / 10;
            result.next = new ListNode(sum % 10);
            result = result.next;
            l2 = l2.next;
        }

        // Add the carry if it's greater than 0 after processing both linked lists
        if(carry > 0){
            result.next = new ListNode(carry);
        }

        return resultHead.next != null ? resultHead.next : null;
    }

    public static void main(String[] args) {
        AddTwoNumbers atn = new AddTwoNumbers();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode result = atn.addTwoNumbers(l1, l2);
        System.out.println(result.toString());
    }
}
