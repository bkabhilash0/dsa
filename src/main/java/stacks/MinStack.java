package stacks;

import java.util.ArrayDeque;

/**
 * <a href="https://www.geeksforgeeks.org/problems/min-stack/1">Min Stack</a>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * Implement the MinStack class:
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 */
public class MinStack {
    ArrayDeque<Long> stack;
    long min = Integer.MAX_VALUE;

    public MinStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int val) {
        if (stack.isEmpty() || val >= min) {
            min = Math.min(min, val);
            stack.push((long) val);
            return;
        }
        long newValue = (2L * val) - min;
        min = val;
        stack.push(newValue);
    }

    public void pop() {
        long top = stack.pop();
        if (top < min) {
            min = (2L * min) - top;
        }
        if(stack.isEmpty()){
            min = Integer.MAX_VALUE;
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        } else if (stack.peek() >= min) {
            long top = stack.peek();
            return (int) top;
        }
        return (int) min;
    }

    public int getMin() {
        if (stack.isEmpty()) {
            return -1;
        }
        return (int) min;
    }

    public void printStack() {
        System.out.println(stack);
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);

        System.out.println(minStack.top());     // 2147483647
        minStack.pop();

        System.out.println(minStack.getMin());  // 2147483646
        minStack.pop();

        System.out.println(minStack.getMin());  // 2147483646
        minStack.pop();

        minStack.push(2147483647);

        System.out.println(minStack.top());     // 2147483647
        System.out.println(minStack.getMin());  // 2147483647

        minStack.push(-2147483648);

        System.out.println(minStack.top());     // -2147483648
        System.out.println(minStack.getMin());  // -2147483648

        minStack.pop();

        System.out.println(minStack.getMin());  // 2147483647
    }
}
