package linked_list;

import linked_list.components.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/intersection-of-two-linked-lists/description/">...</a>
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 * The testcases are generated such that there are no cycles anywhere in the entire linked structure. Note that the linked lists must retain their original structure after the function returns.
 */
public class IntersectionOfTwoLinkedList {
    // Time Complex: O(n + m) where n and m are the number of nodes in the two linked lists, we are iterating through both linked lists once
    // Space Complex: O(n) where n is the number of nodes in the first linked list
    private ListNode bruteForce(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode currentA = headA;
        while (currentA != null) {
            set.add(currentA);
            currentA = currentA.next;
        }

        ListNode currentB = headB;
        while (currentB != null) {
            if (set.contains(currentB)) {
                return currentB;
            }
            currentB = currentB.next;
        }
        return null;
    }

    // Time Complex: O(n + m) where n and m are the number of nodes in the two linked lists, we are iterating through both linked lists once
    // Space Complex: O(1) since we are not using any extra space
    private ListNode better(ListNode A, ListNode B) {
        ListNode currentA = A;
        ListNode currentB = B;
        int lenA = 0;
        int lenB = 0;

        while (currentA != null) {
            lenA++;
            currentA = currentA.next;
        }

        while (currentB != null) {
            lenB++;
            currentB = currentB.next;
        }

        // Find the difference in lengths of the two linked lists
        int delta = Math.abs(lenA - lenB);

        // Now we need to move the pointer of the longer linked list by delta steps
        ListNode longerList = lenA > lenB ? A : B;

        while (delta > 0) {
            longerList = longerList.next;
            delta--;
        }

        // Now we can move both pointers of the linked lists one step at a time until we find the intersection node
        ListNode shorterList = lenA > lenB ? B : A;

        while (longerList != shorterList) {
            longerList = longerList.next;
            shorterList = shorterList.next;
        }
        return longerList;
    }

    // Time Complex: O(n + m) where n and m are the number of nodes in the two linked lists, we are iterating through both linked lists once
    // Space Complex: O(1) since we are not using any extra space
    // Here we are going around each list, when we reach the end of one list, we start traversing the other list,
    // if there is an intersection node, then we will find it in the second iteration,
    // if there is no intersection node, then we will reach the end of both lists and return null
    // When one pointer reaches the end (The shorter list), we take it to head of the other list. This will compensate the delta
    private ListNode optimal(ListNode A, ListNode B) {
        ListNode currentA = A;
        ListNode currentB = B;

        while (currentA != currentB) {
            currentA = currentA == null ? B : currentA.next;
            currentB = currentB == null ? A : currentB.next;
        }
        return currentA;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return optimal(headA, headB);
    }

    public static void main(String[] args) {
        IntersectionOfTwoLinkedList itl = new IntersectionOfTwoLinkedList();
        ListNode headA = Utils.createList(5);
        ListNode headB = Utils.createList(3);
        headB.next = headA.next.next;
        Utils.printList(headA);
        Utils.printList(headB);
        ListNode res = itl.getIntersectionNode(headA, headB);
        System.out.println(res.val);
    }
}
