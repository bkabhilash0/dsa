package linked_list;

import linked_list.components.dll.DoublyLinkedList;
import linked_list.components.dll.Node;

/**
 * <a href="https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1">Delete All Occurrences of a Given Key in DLL</a>
 * You are given the head_ref of a doubly Linked List and a Key.
 * Your task is to delete all occurrences of the given key if it is present and return the new DLL.
 */
public class DeleteAllOccurrencesOfAGivenKeyInDLL {
    // Time Complexity: O(n) where n is the number of nodes in the linked list, we are iterating through the linked list once
    // Space Complexity: O(1) since we are not using any extra space
    private static Node optimal(Node head, int x) {
        if (head == null) {
            return null;
        }
        Node current = head;
        while (current != null) {
            Node nextNode = current.next;
            if (current.data == x) {
                Node next = current.next;
                Node prev = current.prev;
                if (prev != null) {
                    prev.next = next;
                }
                if (next != null) {
                    next.prev = prev;
                }
                // We check this explicitly because if the head node is the one to be deleted, we need to update the head pointer to point to the next node.
                if (current == head) {
                    head = next;
                }
            }
            current = nextNode;
        }
        return head;
    }

    static Node deleteAllOccurOfX(Node head, int x) {
        // code here
        return optimal(head, x);
    }

    public static void main(String[] args) {
        Node head = DoublyLinkedList.createListOfSizeN(5);
        head = DoublyLinkedList.insertAtTail(head, 3);
        head = DoublyLinkedList.insertAtTail(head, 4);
        head = DoublyLinkedList.insertAtTail(head, 3);
        DoublyLinkedList.printList(head);
        head = DeleteAllOccurrencesOfAGivenKeyInDLL.deleteAllOccurOfX(head, 1);
        DoublyLinkedList.printList(head);
    }
}
