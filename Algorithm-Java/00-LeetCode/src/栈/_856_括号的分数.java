package 栈;

import java.util.Stack;

// https://leetcode-cn.com/problems/score-of-parentheses/
public class _856_括号的分数 {
    static int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack();
        stack.push(0);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println("c=" + c);
            if (c == '(') {
                stack.push(0);
            }
            else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2*v, 1));
            }
            System.out.println("stack=" + stack);
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(scoreOfParentheses("((("));
    }
}
