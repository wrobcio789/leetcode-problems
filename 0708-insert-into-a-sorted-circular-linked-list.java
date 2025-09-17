class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            final var result = new Node(insertVal);
            result.next = result;
            return result;
        }

        Node current = head;
        while(current.next != head) {
            if(
                    (current.val <= current.next.val && insertVal >= current.val && insertVal <= current.next.val)
                    || (current.val > current.next.val && (insertVal >= current.val || insertVal <= current.next.val))
            ) {
                break;
            }

            current = current.next;
        }

        current.next = new Node(insertVal, current.next);

        return head;
    }
}