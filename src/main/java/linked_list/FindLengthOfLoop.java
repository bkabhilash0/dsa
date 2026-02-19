package linked_list;

import linked_list.components.Node;

import java.util.HashMap;

/**
 * <a href="https://www.geeksforgeeks.org/problems/find-length-of-loop/1">Find Length of Loop</a>
 * Given a linked list, find the length of the loop in the linked list, if it is present, if there is no loop in the linked list then return 0.
 * Note:
 * Do not modify the linked list.
 * Expected Time Complexity: O(N)
 * Expected Auxiliary Space: O(1)
 */
public class FindLengthOfLoop {
    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complex: O(n) where n is the number of nodes in the linked list
    private int bruteForce(Node head) {
        // code here
        HashMap<Node, Integer> map = new HashMap<>();
        Node current = head;
        int index = 0;
        while (current != null) {
            if (map.containsKey(current)) {
                return (index - map.get(current)) + 1;
            }
            index++;
            map.put(current, index);
            current = current.next;
        }
        return 0;
    }

    private int countNodes(Node head1, Node head2) {
        // Count is 1 here because we are starting from the slow.next so we are counting the slow node as well
        int count = 1;
        Node current = head1;
        while (current != head2) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complex: O(1) since we are not using any extra space to store
    private int optimal(Node head) {
        if (head == null || head.next == null) return 0;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return countNodes(slow.next, fast);
            }
        }
        return 0;
    }

    public int lengthOfLoop(Node head) {
        // code here
        return bruteForce(head);
    }

    public static void main(String[] args) {
        FindLengthOfLoop fll = new FindLengthOfLoop();
        Node head = Utils.createList2(5);
        // Create a cycle in the linked list
        head.next.next.next.next.next = head.next;
        int res = fll.lengthOfLoop(head);
        System.out.println(res);
    }
}
