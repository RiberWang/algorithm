package 链表;

// https://leetcode-cn.com/problems/middle-of-the-linked-list/
public class _876_链表的中间结点 {
    /**
     * 数组
     * @param head
     * @return
     */
    static public ListNode middleNode(ListNode head) {
        ListNode[] list = new ListNode[5];
        int i = 0;
        while (head != null) {
            list[i++] = head;
            head = head.next;
        }

        return list[i / 2];
    }

    /**
     * 单指针
     * @param head
     * @return
     */
    static public ListNode middleNode2(ListNode head) {
        ListNode temp = head;
        int n = 0;
        while (temp != null) {
            n++;
            temp = temp.next;
        }

        int half = 0;
        temp = head;
        while (half < n / 2) {
            half++;
            temp = temp.next;
        }

        return temp;
    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    static public ListNode middleNode3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = null;

        System.out.println(middleNode(node).val);
        System.out.println(middleNode2(node).val);
        System.out.println(middleNode3(node).val);
    }
}


