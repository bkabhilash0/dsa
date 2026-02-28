package linked_list;

public class Node {
    int data;
    Node next;
    Node random;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node(int data, Node next, Node random) {
        this.data = data;
        this.next = next;
        this.random = random;
    }

    @Override
    public String toString() {
        return "Node : { Data: " + data + ", Next: " + (next != null ? this.hashCode() : "null") + " }";
    }
}
