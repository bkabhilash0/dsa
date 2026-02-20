package linked_list;

import linked_list.components.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/linked-list-cycle-ii/description/">...</a>
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
 * It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 */
public class LinkedListCycleII {
    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complex: O(n) where n is the number of nodes in the linked list
    private ListNode bruteForce(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) {
                return current;
            }
            visited.add(current);
            current = current.next;
        }
        return null;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complex: O(1) since we are not using any extra space to store
    private ListNode optimal(ListNode head) {
        // One Node with cycle
        if(head == null || head.next == head) return head;
        // One Node without cycle
        if(head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                slow = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public ListNode detectCycle(ListNode head) {
        return optimal(head);
    }

    public static void main(String[] args) {
        LinkedListCycleII llc = new LinkedListCycleII();
        ListNode head = Utils.createList(5);
        Utils.printList(head);
        // Create a cycle in the linked list
        head.next.next.next.next.next = head.next;
        ListNode res = llc.detectCycle(head);
        System.out.println(res.val);
    }
}
