import java.util.Stack;

class StockSpanner {

    private final Stack<StackRecord> stack = new Stack<>();
    private int day = 0;


    public int next(int price) {
        day++;
        while(!stack.empty() && stack.peek().value() <= price) {
            stack.pop();
        }
        final var result = day - (stack.empty() ? 0 : stack.peek().day());
        stack.push(new StackRecord(price, day));

        return result;
    }

    private record StackRecord(int value, int day) {}
}