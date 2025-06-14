class Solution {
    public ListNode plusOne(ListNode head) {
        final var rest = addOne(head);
        if (rest > 0) {
            return new ListNode(1, head);
        }

        return head;
    }

    public int addOne(ListNode head) {
        if (head.next == null) {
            return addDigit(head, 1);
        } else {
            final var rest = addOne(head.next);
            return addDigit(head, rest);
        }
    }

    private int addDigit(ListNode head, int value) {
        head.val += value;

        if (head.val > 9) {
            head.val -= 10;
            return 1;
        }

        return 0;
    }
}