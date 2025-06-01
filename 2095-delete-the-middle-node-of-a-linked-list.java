/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }

        ListNode node = head.next;
        ListNode beforeRemoval = head;

        while (node.next != null && node.next.next != null) {
            node = node.next.next;
            beforeRemoval = beforeRemoval.next;
        }

        final var nodeToRemove = beforeRemoval.next;
        beforeRemoval.next = nodeToRemove.next;

        return head;
    }
}