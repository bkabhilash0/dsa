package linked_list;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/copy-list-with-random-pointer/description/">Copy List With Random Pointer</a>
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list
 * and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * Return the head of the copied linked list.\
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 */
public class CopyListWithRandomPointer {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int data) {
            this.val = data;
            this.next = null;
            this.random = null;
        }
    }

    // Time Complexity: O(2N) where N is the number of nodes in the linked list, we are traversing through the list twice
    // Space Complexity: O(N) for the map, in the worst case we might have to store all the nodes in the linked list in the map
    // This is accepted by leetcode 100% but we can optimize it to O(1)
    private Node bruteForce(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(0);
        Node tail = dummy;
        Node current = head;
        while (current != null) {
            tail.next = new Node(current.val);
            tail = tail.next;
            map.put(current, tail);
            current = current.next;
        }
        // Now we have the mapping of the original nodes to the copied nodes, we can set the random pointers for the copied nodes
        current = head;
        while (current != null) {
            Node randomNode = current.random;
            if (randomNode != null) {
                // Get the corresponding new node and connect it with the corresponding random node in the copied list
                map.get(current).random = map.get(randomNode);
            }
            current = current.next;
        }
        return dummy.next;
    }

    // Time Complexity: O(3N) where N is the number of nodes in the linked list, we are traversing through the list three times
    // Space Complexity: O(1) since we are not using any extra space
    private Node optimal(Node head) {
        Node current = head;
        // Make sure not to break the orignal list
        while (current != null) {
            Node nextNode = current.next;
            Node copy = new Node(current.val);
            copy.next = nextNode;
            current.next = copy;
            current = nextNode;
        }

        // Connect the random pointer to the copied nodes
        current = head;
        while (current != null && current.next != null) {
            Node randomNode = current.random;
            if (randomNode != null) {
                current.next.random = randomNode.next;
            }
            current = current.next.next;
        }

        Node dummy = new Node(0);
        Node tail = dummy;
        current = head;
        // Now remove the connections between the original nodes and the copied nodes to get the final copied list
        while(current != null && current.next != null) {
            Node copiedNode = current.next;
            tail.next = copiedNode;
            tail = tail.next;
            current.next = copiedNode.next;
            current = current.next;
        }
        return dummy.next;
    }

    public Node copyRandomList(Node head) {
        return optimal(head);
    }

    private void printList(Node head) {
        Node current = head;
        System.out.println("Printing list: ");
        while (current != null) {
            String randomVal = (current.random != null) ? Integer.toString(current.random.val) : "null";
            System.out.print("[" + current.val + ", " + randomVal + "] ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(7);
        Node head2 = new Node(13);
        Node head3 = new Node(11);
        Node head4 = new Node(10);
        Node head5 = new Node(1);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head.random = null;
        head2.random = head;
        head3.random = head5;
        head4.random = head3;
        head5.random = head;
        CopyListWithRandomPointer clwrp = new CopyListWithRandomPointer();
        Node copiedHead = clwrp.copyRandomList(head);
        clwrp.printList(copiedHead);
    }
}
