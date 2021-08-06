package 栈;

import java.util.HashMap;
import java.util.Stack;

// https://leetcode-cn.com/problems/valid-parentheses/
public class _20_有效的括号 {
    /**
     * 字符串替换操作和循环 耗性能
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }

    public boolean isValid2(String s) {
//        String[] string = {};
//        int length1 = string.length; // 属性

        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') { // 左字符
                stack.push(c);
            }
            else { // 右括号
                if (stack.isEmpty()) return false;

                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '[' && c != ']') return false;
                if (left == '{' && c != '}') return false;
            }
        }

        return stack.isEmpty();
    }


//    private HashMap<Character, Character> map = new HashMap<>();
//    public _20_有效的括号() {
//        map.put('(', ')');
//        map.put('[', ']');
//        map.put('{', '}');
//    }

    // 静态初始化
    private static HashMap<Character, Character> map = new HashMap<>();
    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    public boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { // 左字符
                stack.push(c);
            }
            else { // 右括号
                if (stack.isEmpty()) return false;

                if (c != map.get(stack.pop())) return false;
            }
        }

        return stack.isEmpty();
    }
}
