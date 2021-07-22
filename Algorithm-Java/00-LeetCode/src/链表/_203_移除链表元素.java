package 链表;

import org.w3c.dom.Node;

// https://leetcode-cn.com/problems/remove-linked-list-elements/
public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);

//        return (head.val == val) ? head.next : head;
        if (head.val == val) {
            return head.next;
        }
        else  {
            return head;
        }
    }

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return null;

        // 新建一个虚拟节点 指向头结点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode tempNode = dummyHead;
        while (tempNode.next != null) {
            if (tempNode.next.val == val) {
                tempNode.next = tempNode.next.next;
            }
            else {
                tempNode = tempNode.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

    }
}
