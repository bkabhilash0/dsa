package linked_list;

/**
 * <a href="https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1">...</a>
 * Given the head of a linked list where nodes can contain values 0s, 1s, and 2s only.
 * Your task is to rearrange the list so that all 0s appear at the beginning,
 * followed by all 1s, and all 2s are placed at the end.
 */
public class SortALinkedListOfZOT {
    // Time Complexity: O(n) where n is the number of nodes in the linked list
    // Space Complexity: O(1) since we are not using any extra space to store the values of the nodes in the linked list, we are just rearranging the pointers
    private Node bruteForce(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        int[] count = new int[3];
        Node current = head;
        while (current != null) {
            count[current.data]++;
            current = current.next;
        }
        current = head;
        while (current != null) {
            if (count[0] > 0) {
                current.data = 0;
                count[0]--;
            } else if (count[1] > 0) {
                current.data = 1;
                count[1]--;
            } else {
                current.data = 2;
            }
            current = current.next;
        }
        return head;
    }

    // Time Complexity: O(n) where n is the number of nodes in the linked list
    // Space Complexity: O(1) since we are not using any extra space to store the values of the nodes in the linked list, we are just rearranging the pointers
    private Node optimal(Node head){
        if (head == null || head.next == null) {
            return head;
        }
        Node zeroHead = new Node(0);
        Node oneHead = new Node(0);
        Node twoHead = new Node(0);
        Node zeroTail = zeroHead;
        Node oneTail = oneHead;
        Node twoTail = twoHead;
        Node current = head;
        while (current != null) {
            if (current.data == 0) {
                zeroTail.next = current;
                zeroTail = zeroTail.next;
            } else if (current.data == 1) {
                oneTail.next = current;
                oneTail = oneTail.next;
            } else {
                twoTail.next = current;
                twoTail = twoTail.next;
            }
            current = current.next;
        }
        zeroTail.next = oneHead.next != null ? oneHead.next : twoHead.next;
        oneTail.next = twoHead.next;
        twoTail.next = null;
        return zeroHead.next != null ? zeroHead.next : (oneHead.next != null ? oneHead.next : twoHead.next);
    }

    public Node segregate(Node head) {
        // code here
        return optimal(head);
    }

    public static void main(String[] args) {
        SortALinkedListOfZOT sazllo = new SortALinkedListOfZOT();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(0);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(0);
        Node result = sazllo.segregate(head);
        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }
}
