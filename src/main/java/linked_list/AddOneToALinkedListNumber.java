package linked_list;

import linked_list.components.Node;

/**
 * <a href="https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1">Add 1 to a Linked List Number</a>
 * You are given a linked list where each element in the list is a node and have an integer data.
 * You need to add 1 to the number formed by concatinating all the list node numbers together and return the head of the modified linked list.
 * Note: The head represents the first element of the given array.
 */
public class AddOneToALinkedListNumber {
    // Time Complex: O(2n) where n is the number of nodes in the linked list, we are iterating through the linked twice,
    // once to calculate the number formed by concatinating all the list node numbers together and
    // once to assign the values of the modified linked list after adding 1 to the number
    // Space Complex: O(1) since we are not using any extra space to store the values of the nodes
    private Node bruteForce(Node head) {
        if (head == null) return head;
        Node current = head;
        int num = 0;
        while (current != null) {
            num = (num * 10) + current.data;
            current = current.next;
        }
        int sum = num + 1;
        String stringSum = String.valueOf(sum);
        Node newHead = head;
        // If after sum the number of digits is greater than the number of nodes in the linked list,
        // then we need to create a new node and make it the head of the linked list.
        for (int i = 0; i < stringSum.length() && head != null; i++) {
            head.data = Integer.parseInt(stringSum.charAt(i) + "");
            if (head.next == null && i == stringSum.length() - 2) {
                head.next = new Node(Integer.parseInt(stringSum.charAt(i + 1) + ""));
                break;
            } else {
                head = head.next;
            }
        }
        return newHead;
    }

    private int executeOptimal(Node head) {
        if(head == null) return 1;
        int carry = executeOptimal(head.next);
        int sum = head.data + carry;
        head.data = sum % 10;
        carry = sum / 10;
        return carry;
    }

    // Time Complex: O(n) where n is the number of nodes in the linked list, we are iterating through the linked once
    // Space Complex: O(n) where n is the number of nodes in the linked list, we are using the call stack to store the recursive calls,
    // the maximum depth of the recursion will be equal to the number of nodes in the linked list
    private Node optimal(Node head) {
        int carry = executeOptimal(head);
        if(carry == 1) {
            Node newNode = new Node(1);
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    public Node addOne(Node head) {
        // code here.
        return optimal(head);
    }

    public static void main(String[] args) {
        AddOneToALinkedListNumber aolt = new AddOneToALinkedListNumber();
        Node head = new Node(9);
        head.next = new Node(9);
        head.next.next = new Node(9);
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
        Node res = aolt.addOne(head);
        while (res != null) {
            System.out.print(res.data + " ");
            res = res.next;
        }
    }
}
