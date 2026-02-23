package linked_list;

import linked_list.components.dll.DoublyLinkedList;
import linked_list.components.dll.Node;

/**
 * <a href="https://www.geeksforgeeks.org/problems/remove-duplicates-from-a-sorted-doubly-linked-list/0">Remove duplicates from a sorted doubly linked list</a>
 * Given a doubly linked list of n nodes sorted by values, the task is to remove duplicate nodes present in the linked list.
 */
public class RemoveDuplicatesFromASortedDoublyLinkedList {
    // Time Complexity: O(n^2) where n is the number of nodes in the linked list
    // Space Complexity: O(1) as we are not using any extra space for storing duplicates, we are just modifying the existing linked list.
    private Node bruteForce(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node current = head;
        while (current != null) {
            Node runner = current.next;
            while (runner != null) {
                if (current.data == runner.data) {
                    Node prev = runner.prev;
                    Node next = runner.next;
                    if (prev != null) {
                        prev.next = next;
                    }
                    if (next != null) {
                        next.prev = prev;
                    }
                }
                runner = runner.next;
            }
            current = current.next;
        }
        return head;
    }

    // Time Complexity: O(n) where n is the number of nodes in the linked list
    // Space Complexity: O(1) as we are not using any extra space for storing duplicates, we are just modifying the existing linked list.
    private Node optimal(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node current = head;
        Node currentPostion = head;
        while (current != null) {
            Node nextNode = current.next;
            // Node removal or swapping
//            if (current.data != currentPostion.data) {
//                currentPostion.next = current;
//                currentPostion = current;
//            }
            // Just the data swapping
            if (current.data != currentPostion.data) {
                currentPostion = currentPostion.next;
                currentPostion.data = current.data;
            }
            current = nextNode;
        }
        currentPostion.next = null;
        return head;
    }

    Node removeDuplicates(Node head) {
        // Code Here.
        return optimal(head);
    }

    public static void main(String[] args) {
        Node head = DoublyLinkedList.createListOfSizeN(4);
        DoublyLinkedList.printList(head);
        head = DoublyLinkedList.insertAtHead(head, 1);
        head = DoublyLinkedList.insertAtHead(head, 1);
        DoublyLinkedList.printList(head);
        RemoveDuplicatesFromASortedDoublyLinkedList solution = new RemoveDuplicatesFromASortedDoublyLinkedList();
        head = solution.removeDuplicates(head);
        DoublyLinkedList.printList(head);
    }
}
