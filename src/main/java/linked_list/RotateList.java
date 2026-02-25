package linked_list;

import linked_list.components.ListNode;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.com/problems/rotate-list/description/">Rotate List</a>
 * Given the head of a linked list, rotate the list to the right by k places.
 */
public class RotateList {
    // Time Complex: O(2n + k) where n is the number of nodes in the linked list,
    // we are iterating through the linked list once to get the values of the nodes and then we are rotating the list k times
    // Space Complex: O(n) where n is the number of nodes in the linked list
    private ListNode bruteForce(ListNode head, int k) {
        ArrayList<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }
        if(values.isEmpty()) return head;
        int rotations = k % values.size();
        if (rotations == 0) return head;
        for (int i = 0; i < rotations; i++) {
            // Take the last value and add it to the front of the list, this will rotate the list to the right by one place
            int lastValue = values.removeLast();
            values.addFirst(lastValue);
        }
        current = head;
        for (int value : values) {
            current.val = value;
            current = current.next;
        }
        return head;
    }

    // Time Complex: O(n) + O(n - (k % len)) = ~O(2n) where n is the number of nodes in the linked list, we are iterating through the linked list twice,
    // Space Complex: O(1) since we are not using any extra space to store the values of the nodes
    private ListNode optimal(ListNode head, int k) {
        ListNode current = head;
        ListNode tail = null;
        int n = 0;
        while (current != null) {
            n++;
            tail = current;
            current = current.next;
        }
        if(n == 0) return head;
        int rotations = k % n;
        if (rotations == 0) return head;
        int breakPoint = n - rotations;
        current = head;
        while (breakPoint > 1) {
            current = current.next;
            breakPoint--;
        }
        tail.next = head;
        head = current.next;
        current.next = null;
        return head;
    }

    public ListNode rotateRight(ListNode head, int k) {
        return optimal(head, k);
    }

    public static void main(String[] args) {
        RotateList rl = new RotateList();
        ListNode head = Utils.createList(5);
        int k = 2;
        ListNode result = rl.rotateRight(head, k);
        Utils.printList(result);
    }
}
