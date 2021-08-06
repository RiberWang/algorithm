package com.rb;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);

//        stack.add(1, 2); 不合理

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        Stack2<Integer> stack2 = new Stack2<>();
        stack2.push(11);
        stack2.push(22);
        stack2.push(33);
        stack2.push(44);

        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop());
        }
    }
}
