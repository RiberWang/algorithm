package 链表;

// https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
public class _237_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
