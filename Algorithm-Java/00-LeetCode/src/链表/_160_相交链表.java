package 链表;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
public class _160_相交链表 {
    /**
     * 哈希集合
     * 首先遍历链表headA，并将链表headA 中的每个节点加入哈希集合中。然后遍历链表headB，对于遍历到的每个节点，判断该节点是否在哈希集合中：
     * 如果当前节点不在哈希集合中，则继续遍历下一个节点；
     * 如果当前节点在哈希集合中，则后面的节点都在哈希集合中，
     * 即从当前节点开始的所有节点都在两个链表的相交部分，因此在链表headB 中遍历到的第一个在哈希集合中的节点就是两个链表相交的节点，返回该节点。
     * 如果链表headB中的所有节点都不在哈希集合中，则两个链表不相交，返回
     *
     * @param headA
     * @param headB
     * @return
     */
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> listNodeSet = new HashSet<ListNode>();
        ListNode tempNode = headA;
        while (tempNode != null) {
            listNodeSet.add(tempNode);
            tempNode = tempNode.next;
        }
        tempNode = headB;
        while (tempNode != null) {
            if (listNodeSet.contains(tempNode)) {
                return tempNode;
            }
            tempNode = tempNode.next;
        }

        return null;
    }

    static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(1);

        ListNode head1 = new ListNode(8);
        ListNode head2 = new ListNode(4);
        ListNode head3 = new ListNode(5);

        l1.next.next = head1;
        l1.next.next.next = head2;
        l1.next.next.next.next = head3;

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(0);
        l2.next.next = new ListNode(1);

        l2.next.next.next = head1;
        l2.next.next.next.next = head2;
        l2.next.next.next.next.next = head3;

//        System.out.println(getIntersectionNode(l1, l2));
        System.out.println(getIntersectionNode2(l1, l2));
    }
}
