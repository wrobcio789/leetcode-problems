import java.util.Stack;

public class Solution extends Relation {
    public int findCelebrity(int n) {
        final var stack = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            stack.add(i);
        }

        while (stack.size() > 1) {
            final var a = stack.pop();
            final var b = stack.pop();

            if (knows(a, b)) {
                stack.push(b);
            } else {
                stack.push(a);
            }
        }

        final var candidate = stack.pop();
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                if (knows(candidate, i) || !knows(i, candidate)) {
                    return -1;
                }
            }
        }

        return candidate;
    }
}