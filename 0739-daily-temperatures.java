import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        final var n = temperatures.length;
        final var result = new int[n];

        final var stack = new Stack<StackRecord>();
        for(int i = n - 1; i>= 0; i--) {
            final var current = temperatures[i];

            while(!stack.empty() && stack.peek().temp() <= current) {
                stack.pop();
            }

            if(stack.empty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek().index() - i;
            }

            stack.add(new StackRecord(current, i));
        }

        return result;
    }

    private record StackRecord(int temp, int index) {}
}