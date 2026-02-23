package linked_list;

import linked_list.components.dll.DoublyLinkedList;
import linked_list.components.dll.Node;

import java.util.ArrayList;

/**
 * <a href="https://www.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1">Pair Sum in Doubly Linked List</a>
 * Given a sorted doubly linked list of positive distinct elements,
 * the task is to find pairs in a doubly-linked list whose sum is equal to given value target.
 */
public class PairSumInDoublyLinkedList {
    // Time Complexity: O(n^2) where n is the number of nodes in the linked list
    // Space Complexity: O(1) as we are not using any extra space for storing pairs, we are just returning the result list which is not counted in space complexity.
    private static ArrayList<ArrayList<Integer>> bruteForce(int target, Node head) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Node current = head;
        while (current != null) {
            Node runner = current.next;
            while (runner != null) {
                int sum = 0;
                sum = current.data + runner.data;
                if (sum == target) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(current.data);
                    pair.add(runner.data);
                    result.add(pair);
                    break;
                }
                if (sum > target) {
                    break;
                }
                runner = runner.next;
            }

            current = current.next;
        }
        return result;
    }

    // Time Complexity: O(2n) where n is the number of nodes in the linked list
    // Space Complexity: O(1) as we are not using any extra space for storing
    private static ArrayList<ArrayList<Integer>> optimal(int target, Node head) {
        Node left = head;
        Node right = head;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        // Set the right pointer to the end of the linked list that is the tail
        while (right.next != null) {
            right = right.next;
        }

        // The right crosses left when sum == target and we move both left and right together
        while (left != null && left != right && right != null && right.next != left) {
            int sum = left.data + right.data;
            if(sum == target){
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(left.data);
                pair.add(right.data);
                result.add(pair);
                left = left.next;
                right = right.prev;
            }else if(sum < target){
                left = left.next;
            }else{
                right = right.prev;
            }
        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        // code here
        return optimal(target, head);
    }

    public static void main(String[] args) {
        Node head = DoublyLinkedList.createListOfSizeN(9);
        DoublyLinkedList.printList(head);
        head = DoublyLinkedList.deleteNode(head, 3);
        head = DoublyLinkedList.deleteNode(head, 7);
        DoublyLinkedList.printList(head);
        int target = 7;
        ArrayList<ArrayList<Integer>> pairs = findPairsWithGivenSum(target, head);
        System.out.println("Pairs with sum " + target + ": " + pairs);
    }
}
