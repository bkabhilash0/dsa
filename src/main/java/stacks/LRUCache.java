package stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCache {
    private static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node[] map;
    int capacity;
    int count;
    Node head;
    Node tail;

    // We maintain the DLL such that LRU would be on the tail and most recently used on the head
    public LRUCache(int capacity) {
        map = new Node[10_000+1];
        this.capacity = capacity;
        this.count = 0;
        // Our Main nodes are going to be between this -1......-1
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    private void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        count--;
    }

    private void insertNode(Node newNode) {
        Node temp = head.next;
        newNode.next = temp;
        newNode.prev = head;
        head.next = newNode;
        temp.prev = newNode;
        count++;
    }

    public int get(int key) {
        if (map[key] == null) {
            return -1;
        }
        // When the cache is used update the list such that its put on the head
        Node node = map[key];
        map[key] = null;
        deleteNode(node);
        insertNode(node);
        map[key] = node;
        return node.value;
    }

    public void put(int key, int value) {
        if (map[key] != null) {
            Node currentNode = map[key];
            map[key] = null;
            deleteNode(currentNode);
        }
        if (count == capacity) {
            // Remove the least recently used key
            map[tail.prev.key] = null;
            deleteNode(tail.prev);
        }
        insertNode(new Node(key, value));
        map[key] = head.next;
    }

    public static void main(String[] args) {

    }
}
