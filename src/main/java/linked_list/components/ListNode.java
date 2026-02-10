package linked_list.components;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode : { Value: " + val + ", Next: " + (next != null ? this.hashCode() : "null") + " }";
    }
}
