package linked_list;

import java.util.ArrayList;

/*
1. getNumberOfNodes(Node head): Returns the total number of nodes in the linked list.
2. arrayToLinkedList(int[] arr): Converts an array of integers into a linked list and returns the head of the linked list.
3. linkedListToArray(Node head): Converts a linked list back into an array of integers and returns the array.
4. printLinkedList(Node head): Prints the elements of the linked list in a readable format.
5. getHead(Node head): Returns the head node of the linked list.
6. getTail(Node head): Returns the tail node of the linked list.
7. getNodeAtPosition(Node head, int position): Returns the node at the specified position in the linked list (1-based index). If the position is out of bounds, it should return null.
 */
public class CustomLinkedList {
    public int getSize(Node head) {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public static Node arrayToLinkedList(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];
            Node newNode = new Node(num);
            temp.next = newNode;
            temp = newNode;
        }

        return head;
    }

    public static int[] linkedListToArray(Node head) {
        if (head == null) return new int[0];
//        int[] result = new int[getSize(head)];
        ArrayList<Integer> result = new ArrayList<>();
        int index = 0;
        Node temp = head;
        while (temp != null) {
//            result[index++] = temp.data;
            result.add(temp.data);
            temp = temp.next;
        }
//        return result;
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void printLinkedList(Node head) {
        Node temp = head;
        System.out.print("Linked List: ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public Node getHead(Node head) {
        return head;
    }

    public Node getTail(Node head) {
        if (head == null) return null;

        Node temp = head;
        while (temp.next != null) temp = temp.next;
        return temp;
    }

    public Node getNodeAtPosition(Node head, int position) {
        if (head == null || position < 0) return null;
        int pos = 0;
        Node temp = head;
        while (temp != null) {
            pos++;
            if (pos == position) return temp;
            temp = temp.next;
        }
        return null;
    }

    public Node getNodeWithValue(Node head, int value) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == value) return temp;
            temp = temp.next;
        }
        return null;
    }

    public boolean isElementPresent(Node head, int value) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == value) return true;
            temp = temp.next;
        }
        return false;
    }

    // Insertions
    public Node insertHead(Node head, int value) {
        Node newNode = new Node(value);
        if (head == null) {
            return newNode;
        } else {
            newNode.next = head;
        }
        return newNode;
    }

    public Node insertTail(Node head, int value) {
        if (head == null) {
            return new Node(value);
        }
        Node newNode = new Node(value);
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        return head;
    }

    public void insertNode(Node head, int value) {
        Node newNode = new Node(value);
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void insertNodeAtPosition(Node head, int value, int position) {
        Node newNode = new Node(value);
        int pos = 0;
        Node temp = head;
        while (temp != null) {
            pos++;
            if (pos == position - 1) {
                newNode.next = temp.next;
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
    }

    // Deletions
    public Node deleteHead(Node head) {
        if (head == null) return null;
        head = head.next;
        return head;
    }

    public Node deleteTail(Node head) {
        if (head == null || head.next == null) return null;

        Node temp = head;
        while (temp.next.next != null) temp = temp.next;
        temp.next = null;
        return head;
    }

    public Node deleteNodeWithValue(Node head, int value) {
        if (head == null) return null;
        if (head.data == value) {
            head = head.next;
            return head;
        }
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == value) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    public Node deleteNodeAtPosition(Node head, int position) {
        if (head == null) {
            return null;
        }
        if (position == 1) {
            head = head.next;
            return head;
        }
        int pos = 0;
        Node temp = head;
        while (temp.next != null) {
            pos++;
            if (pos == position - 1) {
                temp.next = temp.next.next;
                return head;
            }
            temp = temp.next;
        }
        return head;
    }


    public static void main(String[] args) {
        CustomLinkedList linkedList = new CustomLinkedList();
        int[] arr = {1, 2, 3, 4, 5};
        Node head = arrayToLinkedList(arr);
        linkedList.printLinkedList(head);
        System.out.println("Number of Nodes: " + linkedList.getSize(head));
        System.out.println("Head Node: " + linkedList.getHead(head));
        System.out.println("Tail Node: " + linkedList.getTail(head));
        System.out.println("Node at Position 3: " + linkedList.getNodeAtPosition(head, 3));
        System.out.println("Is element 4 present? " + linkedList.isElementPresent(head, 4));
        System.out.println("Node with value 2: " + linkedList.getNodeWithValue(head, 2));
        System.out.println("Inserting 0 at head...");
        head = linkedList.insertHead(head, 0);
        System.out.println("Inserting 6 at tail...");
        head = linkedList.insertTail(head, 6);
        System.out.println("Inserting Node with value 7...");
        linkedList.insertNode(head, 7);
        linkedList.printLinkedList(head);
        System.out.println("Inserting Node with value 8 at position 4...");
        linkedList.insertNodeAtPosition(head, 8, 4);
        linkedList.printLinkedList(head);

        System.out.println("Deleting head...");
        head = linkedList.deleteHead(head);
        linkedList.printLinkedList(head);
        System.out.println("Deleting tail...");
        head = linkedList.deleteTail(head);
        linkedList.printLinkedList(head);
        System.out.println("Deleting node with value 3...");
        head = linkedList.deleteNodeWithValue(head, 3);
        linkedList.printLinkedList(head);
        System.out.println("Deleting node at position 2...");
        head = linkedList.deleteNodeAtPosition(head, 2);
        linkedList.printLinkedList(head);

        linkedList.printLinkedList(head);
        int[] result = linkedListToArray(head);
        System.out.println("Array from Linked List: " + java.util.Arrays.toString(result));

    }
}
