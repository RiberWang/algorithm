package 链表;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/palindrome-linked-list/
public class _234_回文链表 {
    static boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<Integer>();

        ListNode tempNode = head;
        while (tempNode != null) {
            list.add(tempNode.val);
            tempNode = tempNode.next;
        }
        int front = 0;
        int back = list.size() - 1;
        while (front < back) {
            if (!list.get(front).equals(list.get(back))) {
                return false;
            }

            front++;
            back--;
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(listNode));
    }
}
