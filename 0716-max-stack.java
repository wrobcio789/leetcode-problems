import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

class MaxStack {
    final Map<Integer, Integer> set = new TreeMap<>(Comparator.reverseOrder());
    final LinkedList<Integer> stack = new LinkedList<>();
    final Map<Integer, Stack<LinkedList.Node<Integer>>> nodeTracker = new HashMap<>();

    public MaxStack() {

    }

    public void push(int x) {
        final var node = stack.insertLast(x);
        set.merge(x, 1, Integer::sum);
        nodeTracker.computeIfAbsent(x, key -> new Stack<>()).push(node);
    }

    public int pop() {
        final var result = stack.popLast();

        set.merge(result, -1, Integer::sum);
        if (set.get(result) == 0) {
            set.remove(result);
        }

       nodeTracker.get(result).pop();

        return result;
    }

    public int top() {
        return stack.getLast();
    }

    public int peekMax() {
        return set.keySet()
                .stream()
                .findFirst()
                .orElseThrow();
    }

    public int popMax() {
        final var result = peekMax();

        set.merge(result, -1, Integer::sum);
        if (set.get(result) == 0) {
            set.remove(result);
        }

        final var nodeToRemove = nodeTracker.get(result).pop();
        stack.remove(nodeToRemove);

        return result;
    }

    private static class LinkedList<T> {
        private Node<T> first;
        private Node<T> last;

        public T getLast() {
            return last.value;
        }

        public Node<T> insertLast(T val) {
            if(first == null) {
                this.first = this.last = new Node<>(val, null, null);
                return this.first;
            }

            final var node = new Node<>(val, this.last, null);
            this.last = this.last.after = node;
            return node;
        }

        public T popLast() {
            final var result = last.value;
            remove(last);
            return result;
        }

        public void remove(Node<T> node) {
            if (node == first) {
                first = node.after;
            }
            if (node == last) {
                last = node.before;
            }

            if (node.before != null) {
                node.before.after = node.after;
            }

            if (node.after != null) {
                node.after.before = node.before;
            }
        }


        private static class Node<T> {
            private final T value;
            private Node<T> before;
            private Node<T> after;

            Node(T value, Node<T> before, Node<T> after) {
                this.value = value;
                this.before = before;
                this.after = after;
            }
        }
    }
}