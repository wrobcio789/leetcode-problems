class Solution {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode current = head;
        while (current != null) {
            for (int i = 0; i < m - 1 && current != null; i++) {
                current = current.next;
            }

            if (current == null) {
                break;
            }

            final var base = current;
            for (int i = 0; i < n && current != null; i++) {
                current = current.next;
            }

            if (current == null) {
                base.next = null;
            } else {
                base.next = current.next;
                current = current.next;
            }

        }
        return head;
    }
}