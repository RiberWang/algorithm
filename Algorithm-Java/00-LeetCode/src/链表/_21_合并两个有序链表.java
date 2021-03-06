package 链表;

import java.util.LinkedList;

// https://leetcode-cn.com/problems/merge-two-sorted-lists/
public class _21_合并两个有序链表 {
    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode newHeade = new ListNode(-1);
        ListNode prev = newHeade;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            }
            else {
                prev.next = l2;
                l2 = l2.next;
            }

            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;

        return newHeade.next;
    }

    static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);

            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);

            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        System.out.println(mergeTwoLists(l1, l2));
        System.out.println(mergeTwoLists2(l1, l2));
    }
}
