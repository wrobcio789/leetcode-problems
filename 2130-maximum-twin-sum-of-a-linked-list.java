/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int pairSum(ListNode head) {
        final var middleNode = splitByMiddle(head);
        var firstHalf = head;
        var secondHalf = reverse(middleNode);

        int result = 0;
        while(firstHalf != secondHalf) {
            result = Math.max(result, firstHalf.val + secondHalf.val);

            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return result;
    }

    private ListNode reverse(ListNode head) {
        var current = head;
        var previous = (ListNode) null;

        while(current != null) {
            final var next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }

    private ListNode splitByMiddle(ListNode head) {
        ListNode result = head;
        var node = head;
        while (true) {
            node = node.next.next; //It's guaranteed that number of nodes is even;
            if(node == null) {
                final var answer = result.next;
                result.next = null;
                return answer;
            }
            result = result.next;
        }
    }
}
