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
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode evenBegin = head.next;
        ListNode even = head.next;

        if (even.next == null) {
            return head;
        }

        int n = 3;
        ListNode node = head.next.next;
        while (node != null) {
            if ((n & 1) == 1) {
                odd.next = node;
                odd = node;
            } else {
                even.next = node;
                even = node;
            }
            node = node.next;
            n++;
        }

        odd.next = evenBegin;
        even.next = null;
        return head;
    }
}