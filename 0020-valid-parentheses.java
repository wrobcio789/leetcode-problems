import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        final var stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            final var bracket = s.charAt(i);

            if (bracket == '(' || bracket == '[' || bracket == '{') {
                stack.push(bracket);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                final var stackElem = stack.pop();
                final var expectedBracket = switch (stackElem) {
                    case '(' -> ')';
                    case '[' -> ']';
                    case '{' -> '}';
                    default -> throw new IllegalStateException("Unexpected value: " + stackElem);
                };
                if (bracket != expectedBracket) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}