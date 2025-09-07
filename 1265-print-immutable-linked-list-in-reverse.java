class Solution {
    public void printLinkedListInReverse(ImmutableListNode head) {
        printLinkedListInReverse(head, null);
    }

    private void printLinkedListInReverse(ImmutableListNode begin, ImmutableListNode end) {
        if (begin == null) {
            return;
        }

        if (begin == end) {
            begin.printValue();
            return;
        }

        final var middle = findMiddle(begin, end);
        printLinkedListInReverse(middle.getNext(), end);
        printLinkedListInReverse(begin, middle);
    }

    private ImmutableListNode findMiddle(ImmutableListNode head, ImmutableListNode end) {
        ImmutableListNode slowPointer = head;
        ImmutableListNode fastPointer = head;

        while (fastPointer.getNext() != end) {
            slowPointer = slowPointer.getNext();

            fastPointer = fastPointer.getNext();
            if (fastPointer.getNext() != end) {
                fastPointer = fastPointer.getNext();
            }
        }

        return slowPointer;
    }
}
