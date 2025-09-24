import java.util.Stack;
import java.util.function.IntBinaryOperator;

class Solution {
    public int calculate(String s) {
        final var numStack = new Stack<Integer>();
        final var operators = new Stack<Operator>();
        for (int i = 0; i < s.length(); i++) {
            final var character = s.charAt(i);
            if (Character.isDigit(character)) {
                int num = character - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num *= 10;
                    num += s.charAt(i + 1) - '0';
                    i++;
                }
                numStack.push(num);
            } else {
                final var operator = Operator.parse(character);
                if (operator == Operator.LEFT_BRACKET) {
                    operators.push(operator);
                } else if (operator == Operator.RIGHT_BRACKET) {
                    while (!operators.isEmpty() && operators.peek() != Operator.LEFT_BRACKET) {
                        performOperation(numStack, operators);
                    }
                    operators.pop();
                } else {
                    while (!operators.isEmpty() && operator.getPrecedence() <= operators.peek().getPrecedence()) {
                        performOperation(numStack, operators);
                    }
                    operators.push(operator);
                }
            }
        }

        while (!operators.isEmpty()) {
            performOperation(numStack, operators);
        }

        return numStack.pop();
    }

    private void performOperation(Stack<Integer> numStack, Stack<Operator> operators) {
        final var b = numStack.pop();
        final var a = numStack.pop();
        final var operator = operators.pop();
        numStack.push(operator.operate(a, b));
    }

    private enum Operator {
        LEFT_BRACKET(-1, (a, b) -> {
            throw new RuntimeException("error");
        }),
        RIGHT_BRACKET(-1, (a, b) -> {
            throw new RuntimeException("error");
        }),
        MULTIPLY(2, (a, b) -> a * b),
        DIVIDE(2, (a, b) -> a / b),
        ADD(1, Integer::sum),
        SUBSTRACT(1, (a, b) -> a - b);

        private final int precedence;
        private final IntBinaryOperator operation;

        Operator(int precedence, IntBinaryOperator operation) {
            this.precedence = precedence;
            this.operation = operation;
        }

        public int getPrecedence() {
            return precedence;
        }

        public int operate(int a, int b) {
            return operation.applyAsInt(a, b);
        }

        public static Operator parse(char token) {
            return switch (token) {
                case '(' -> LEFT_BRACKET;
                case ')' -> RIGHT_BRACKET;
                case '*' -> MULTIPLY;
                case '/' -> DIVIDE;
                case '+' -> ADD;
                case '-' -> SUBSTRACT;
                default -> throw new IllegalStateException("Unexpected value: " + token);
            };
        }
    }
}