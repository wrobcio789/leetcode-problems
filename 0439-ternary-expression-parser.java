import java.util.Stack;

class Solution {
    public String parseTernary(String expression) {
        final var ternaryStack = new Stack<Character>();

        boolean expectingValue = true;
        for (int i = expression.length() - 1; i >= 0; i--) {
            final var character = expression.charAt(i);
            if (expectingValue) {
                ternaryStack.add(character);
            } else if (character == 'T') {
                final var toRemember = ternaryStack.pop();
                ternaryStack.pop();
                ternaryStack.add(toRemember);
            } else if(character == 'F') {
                ternaryStack.pop();
            }

            expectingValue = character == ':';
        }

        return String.valueOf(ternaryStack.pop());
    }
}