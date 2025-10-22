import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collector;

class Solution {
    public String removeKdigits(String num, int k) {
        final var numStack = new Stack<Byte>();

        int i = 0, removals = 0;
        for (; i < num.length() && removals < k; i++) {
            final var digit = (byte) (num.charAt(i) - '0');

            while (removals < k && !numStack.isEmpty() && numStack.peek() > digit) {
                numStack.pop();
                removals++;
            }
            numStack.push(digit);
        }

        while (removals < k && !numStack.isEmpty()) {
            numStack.pop();
            removals++;
        }

        final var untrimmedResult = numStack.stream().collect(
                        Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append, Function.identity()))
                .append(num, i, num.length())
                .toString();

        final var leadingZeroesTrimmed = untrimmedResult.replaceFirst("^0+", "");

        return leadingZeroesTrimmed.isEmpty() ? "0" : leadingZeroesTrimmed;
    }
}