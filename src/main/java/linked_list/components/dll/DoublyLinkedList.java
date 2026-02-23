package linked_list.components.dll;

public class DoublyLinkedList {

    public static Node insertAtHead(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null) {
            return newNode;
        }
        newNode.next = head;
        head.prev = newNode;
        return newNode;
    }

    public static Node insertAtTail(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null) {
            return newNode;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.prev = current;
        return head;
    }

    public static Node createListFromArray(int[] arr) {
        Node head = null;
        for (int num : arr) {
            head = insertAtTail(head, num);
        }
        return head;
    }

    public static Node createListOfSizeN(int n) {
        Node head = null;
        for (int i = 1; i <= n; i++) {
            head = insertAtTail(head, i);
        }
        return head;
    }

    public static Node printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + ", ");
            current = current.next;
        }
        System.out.println();
        return head;
    }

    public static Node deleteNode(Node head, int key) {
        if (head == null) {
            return null;
        }
        if (head.data == key) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            return head;
        }
        Node current = head;
        while (current != null) {
            Node nextNode = current.next;
            if (current.data == key) {
                Node prev = current.prev;
                Node next = current.next;
                prev.next = next;
                if (next != null) {
                    next.prev = prev;
                }
                return head;
            }
            current = nextNode;
        }
        return head;
    }

    public static void main(String[] args) {

    }
}
