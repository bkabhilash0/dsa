package linked_list;

import linked_list.components.ListNode;

/**
 * <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/description/">Reverse Nodes in k Group</a>
 * 25. Reverse Nodes in k-Group
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * Follow up: Can you solve the problem in O(1) extra memory space?
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 */
public class ReverseNodesInKGroup {
    // Helper function to reverse a linked list
    // Time Complexity: O(n), where n is the number of nodes in the linked list
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // Time Complexity: O(n) + O(n), where n is the number of nodes in the linked list. One for traversing the linked list and another for reversing the linked list in groups of k.
    // Space Complexity: O(1), as we are using only a constant amount of extra space to store the pointers.
    private ListNode optimal(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        int count = 1;
        ListNode current = head;
        ListNode lastNodeOfThePreviousSegment = null;
        ListNode firstNodeOfTheCurrentSegment = head;
        ListNode newHead = null;
        while (current != null) {
            ListNode firstNodeOfNextSegment = current.next;
            if (count == k) {
                // Set the firstNodeOfNextSegment of the current node to null to break the list into two parts
                current.next = null;
                ListNode firstNodeOfTheReversedSegment = reverse(firstNodeOfTheCurrentSegment);
                if (newHead == null) {
                    newHead = firstNodeOfTheReversedSegment;
                }
                // If there is a previous part of the list, connect it to the reversed part
                if (lastNodeOfThePreviousSegment != null) {
                    lastNodeOfThePreviousSegment.next = firstNodeOfTheReversedSegment;
                }
                // Set the prev as the tail of the reversed part for the firstNodeOfNextSegment iteration
                lastNodeOfThePreviousSegment = firstNodeOfTheCurrentSegment;

                // Start will be the tail of the reversed list, so we need to connect it to the firstNodeOfNextSegment part of the list
                firstNodeOfTheCurrentSegment.next = firstNodeOfNextSegment;
                // Move the start to the first node in the firstNodeOfNextSegment part of the list
                firstNodeOfTheCurrentSegment = firstNodeOfNextSegment;

                // Reset the counter for the firstNodeOfNextSegment group
                count = 0;
            }
            count++;
            current = firstNodeOfNextSegment;
        }
        return newHead;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        return optimal(head, k);
    }

    public static void main(String[] args) {
        ReverseNodesInKGroup rnk = new ReverseNodesInKGroup();
        ListNode head = Utils.createList(5);
        int k = 2;
        ListNode result = rnk.reverseKGroup(head, k);
        Utils.printList(result);
    }
}
