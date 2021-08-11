package 队列;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/implement-stack-using-queues/
public class _225_用队列实现栈 {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public _225_用队列实现栈() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
//        queue1 = queue2;
        Queue temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
//        int i = 0;
//        while (!queue1.isEmpty()) {
//            if (i != 0) {
//                sb.append(", ");
//            }
//            sb.append(queue1.toArray()[i]);
//            i++;
//        }

        for (int i = 0; i < queue1.toArray().length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(queue1.toArray()[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        _225_用队列实现栈 queue = new _225_用队列实现栈();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.top());
        System.out.println(queue);
    }
}
