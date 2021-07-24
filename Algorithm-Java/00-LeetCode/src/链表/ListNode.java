package 链表;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + val);
        while (next != null) {
            sb.append(", " + next.val);

            next = next.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
