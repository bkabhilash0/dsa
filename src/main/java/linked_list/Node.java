package linked_list;

public class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node : { Data: " + data + ", Next: " + (next != null ? this.hashCode() : "null") + " }";
    }
}
