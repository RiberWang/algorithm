package 队列;

import java.util.Queue;
import java.util.Stack;

// https://leetcode-cn.com/problems/implement-queue-using-stacks/
public class _232_用栈实现队列 {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public _232_用栈实现队列() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** 入队
     * Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** 出队
     * Removes the element from in front of queue and returns that element. */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /** 获取队头元素
     * Get the front element. */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    /** 是否为空
     * Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
