package linked_list;

import linked_list.components.ListNode;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 148. Sort List
 * <a href="https://leetcode.com/problems/sort-list/description/">Sort List</a>
 * Given the head of a linked list, return the list after sorting it in ascending order.
 */
public class SortList {
    // Time Complexity: O(N) + O(N log N) + O(N) = O(N log N) where N is the number of nodes in the linked list
    // Space Complexity: O(N) for the values list, in the worst case we might have to store all the nodes in the linked list in the values list
    private ListNode bruteForce(ListNode head) {
        if (head == null || head.next == null) return head;
        ArrayList<ListNode> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current);
            current = current.next;
        }
        values.sort((a, b) -> Integer.compare(a.val, b.val));
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (ListNode node : values) {
            tail.next = node;
            tail = tail.next;
        }
        tail.next = null;
        return dummy.next;
    }

    // Time Complexity: O(N + M) where N and M are the number of nodes in the two linked lists, we are visiting each node once
    // Space Complexity: O(1) since we are not using any extra space
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        // This is also redundant coz both the list won't be null at the same time so we are already handling what to connect to the tail
//        tail.next = null;

        // Can be simplified to tail.next = (l1 != null) ? l1 : l2; but I am keeping it like this for better readability
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        // If I set this as null, the l1 or l2 list that is connected would become null
//        tail.next = null;
        return dummy.next;
    }

    // Time Complexity: O(N/2) where N is the number of nodes in the linked list, we are visiting each node once
    // Space Complexity: O(1) since we are not using any extra space to store the values of the nodes
    private ListNode findMiddle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        // Adjusting the fast pointer one step ahead to ensure that in the case of even number of nodes,
        // the middle node is the first middle node, this is important for the merge sort algorithm to work correctly
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Time Complexity: O(N log N) where N is the number of nodes in the linked list, we are dividing the linked list into two halves log N times and merging the two halves takes O(N) time
    // Space Complexity: O(log N) for the recursive stack
    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = findMiddle(head);
        ListNode rightHead = mid.next;
        // Set the next pointer of the middle node to null to split the linked list into two halves
        mid.next = null;
        // List Returned by the left and right recursive calls will be the head of the sorted linked list for the left and right halves respectively
        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(rightHead);
        return merge(l1, l2);
    }

    // Time Complexity: O(N log N) where N is the number of nodes in the linked list, we are dividing the linked list into two halves log N times and merging the two halves takes O(N) time
    // Space Complexity: O(log N) for the recursive stack
    private ListNode optimal(ListNode head) {
        return mergeSort(head);
    }

    public ListNode sortList(ListNode head) {
        return optimal(head);
    }

    public static void main(String[] args) {
        SortList sl = new SortList();
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        Utils.printList(head);
        head = sl.sortList(head);
        Utils.printList(head);
    }
}
