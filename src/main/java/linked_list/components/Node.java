package linked_list.components;

public class Node {
    public int data;
    public Node next;
    public Node bottom;

    public Node(int d) {
        data = d;
        next = null;
    }

    public Node(int d, Node bottom) {
        data = d;
        next = null;
        this.bottom = bottom;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}
