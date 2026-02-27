package linked_list;

import linked_list.components.Node;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * <a href="https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1">Flattening a Linked List</a>
 * Given a linked list containing n head nodes where every node in the linked list contains two pointers:
 * (i) next points to the next node in the list.
 * (ii) bottom points to a sub-linked list where the current node is the head.
 * Each of the sub-linked lists nodes and the head nodes are sorted in ascending order based on their data.
 * Flatten the linked list such that all the nodes appear in a single level while maintaining the sorted order.
 * Note:
 * 1. ↓ represents the bottom pointer and → represents the next pointer.
 * 2. The flattened list will be printed using the bottom pointer instead of the next pointer.
 */
public class FlatteningALinkedList {
    // Let N = n * m = total number of nodes in the linked list, where n is the number of head nodes and m is the average number of nodes in each sub-linked list
    // Time Complexity: O(N) + O(N log N) + O(N) = O(N log N)
    // Space Complexity: O(N) for the values list, in the worst case we might have to store all the nodes in the linked list in the values list
    private Node bruteForce(Node root) {
        if (root == null) return null;
        ArrayList<Node> values = new ArrayList<>();
        Node current = root;
        while (current != null) {
            Node bottomCurrent = current.bottom;
            values.add(current);
            while (bottomCurrent != null) {
                values.add(bottomCurrent);
                bottomCurrent = bottomCurrent.bottom;
            }
            current = current.next;
        }
        values.sort(Comparator.comparingInt(a -> a.data));
        Node dummy = new Node(-1);
        Node tail = dummy;
        while (!values.isEmpty()) {
            tail.bottom = values.removeFirst();
            tail.next = null;
            tail = tail.bottom;
        }
        tail.next = null;
        tail.bottom = null;
        return dummy.bottom;
    }

    // Let N = n * m = total number of nodes in the linked list, where n is the number of head nodes and m is the average number of nodes in each sub-linked list
    // Time Complexity: O(N*m) where N is the total number of nodes in the linked list, we are visiting each node once
    // Space Complexity: O(n) for the recursive stack, in the worst case we might have to visit all the head nodes in the linked list and add them to the recursive stack
    private Node optimal(Node root) {
        if (root == null) return null;
        if (root.next == null) {
            return root;
        }
        Node dummy = new Node(-1);
        Node tail = dummy;
        Node merged = optimal(root.next);
        Node l1 = root;
        Node l2 = merged;
        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                tail.bottom = l1;
                l1 = l1.bottom;
            } else {
                tail.bottom = l2;
                l2 = l2.bottom;
            }
            tail = tail.bottom;
            tail.bottom = null;
            tail.next = null;
        }

        if (l1 != null) {
            tail.bottom = l1;
        }
        if (l2 != null) {
            tail.bottom = l2;
        }

        return dummy.bottom;
    }

    public Node flatten(Node root) {
        // code here
        return optimal(root);
    }

    private void printList(Node head) {
        Node current = head;
        System.out.println("Printing list: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.bottom;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FlatteningALinkedList flatteningALinkedList = new FlatteningALinkedList();
        Node head = new Node(5, new Node(7, new Node(8)));
        Node node2 = new Node(10, new Node(20));
        Node node3 = new Node(19, new Node(22));
        Node node4 = new Node(28, new Node(40, new Node(45)));
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        Node res = flatteningALinkedList.flatten(head);
        flatteningALinkedList.printList(res);
    }
}
