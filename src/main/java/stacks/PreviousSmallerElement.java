package stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * <a href="https://www.geeksforgeeks.org/problems/previous-smaller-element/1">Previous Smaller Element</a>
 * You are given an integer array arr[ ]. For every element in the array, your task is to determine its Previous Smaller Element (PSE).
 * The Previous Smaller Element (PSE) of an element x is the first element that appears to the left of x in the array and is strictly smaller than x.
 * Note: If no such element exists, assign -1 as the PSE for that position.
 */
public class PreviousSmallerElement {
    // Time Complexity: O(N) where N is the length of the input array, we are iterating through the array once and each element is pushed and popped at most once from the stack
    // Space Complexity: O(N) in the worst case when the input array is in increasing order, we are storing all the elements in the stack
    private static ArrayList<Integer> solvePrevSmallElement(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();
        for(int i: arr){
            // For smaller element we check if the i is less than or equal to the top of the stack, if it is we pop the stack until we find an element that is smaller than i or the stack becomes empty
            while(!stack.isEmpty() && i <= stack.peek()){
                stack.pop();
            }
            res.add(stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        return res;
    }

    public static ArrayList<Integer> prevSmaller(int[] arr) {
        // code here
        return solvePrevSmallElement(arr);
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 2};
        ArrayList<Integer> res = prevSmaller(arr);
        System.out.println(res);
    }
}
