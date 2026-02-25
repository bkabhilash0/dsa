package linked_list;

import linked_list.components.ListNode;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.com/problems/swapping-nodes-in-a-linked-list/description/">Swapping Nodes in a Linked List</a>
 * You are given the head of a linked list, and an integer k.
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 * Note: The values are only being swapped, not the nodes themselves.
 */
public class SwappingNodesInALinkedList {
    // Time Complex: O(2n) where n is the number of nodes in the linked list, we are iterating through the linked twice,
    // Space Complex: O(1) since we are not using any extra space to store the values of the nodes, we are just using a few pointers to keep track of the current node
    private ListNode bruteForce(ListNode head, int k) {
        ListNode current = head;
        ListNode kthFromStart = null;
        ListNode kthFromEnd = null;
        int i = 1;
        int n = 0;
        while (current != null) {
            if (i == k) {
                kthFromStart = current;
            }
            n++;
            i++;
            current = current.next;
        }
        int lastNodePos = n - k + 1;
        i = 1;
        current = head;
        while (current != null) {
            if (i == lastNodePos) {
                kthFromEnd = current;
                break;
            }
            i++;
            current = current.next;
        }
        if (kthFromStart != null && kthFromEnd != null) {
            int temp = kthFromStart.val;
            kthFromStart.val = kthFromEnd.val;
            kthFromEnd.val = temp;
        }
        return head;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked once
    // Space Complex: O(n) where n is the number of nodes in the linked list
    private ListNode optimal(ListNode head, int k) {
        ArrayList<ListNode> nodes = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }
        int n = nodes.size();
        if (k > n) return head;
        ListNode kthNodeFromStart = nodes.get(k - 1);
        // 5 - 2 = 3, so we need to get the node at index 3 which is the 4th node in the linked list, since the list is 1-indexed
        ListNode kthNodeFromEnd = nodes.get(n - k);
        kthNodeFromStart.val = kthNodeFromStart.val ^ kthNodeFromEnd.val;
        kthNodeFromEnd.val = kthNodeFromStart.val ^ kthNodeFromEnd.val;
        kthNodeFromStart.val = kthNodeFromStart.val ^ kthNodeFromEnd.val;
        return head;
    }

    public ListNode swapNodes(ListNode head, int k) {
        return optimal(head, k);
    }

    public static void main(String[] args) {
        SwappingNodesInALinkedList snial = new SwappingNodesInALinkedList();
        ListNode head = Utils.createList(3);
        ListNode res = snial.swapNodes(head, 1);
        Utils.printList(res);
    }
}
