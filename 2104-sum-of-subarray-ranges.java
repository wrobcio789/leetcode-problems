import java.util.Stack;

class Solution {
    public long subArrayRanges(int[] nums) {
        final var n = nums.length;

        long result = 0;
        long minSum = 0;
        long maxSum = 0;

        final var minStack = new Stack<PoppedNumber>();
        final var maxStack = new Stack<PoppedNumber>();

        for (int i = 0; i < n; i++) {
            final long newElem = nums[i];

            int pops = 1;
            while (!minStack.isEmpty() && minStack.peek().value() >= newElem) {
                final var popped = minStack.pop();
                pops += popped.times();
                minSum -= popped.value() * popped.times();
                minSum += newElem * popped.times();
            }
            minSum += newElem;
            minStack.push(new PoppedNumber(newElem, pops));

            pops = 1;
            while (!maxStack.isEmpty() && maxStack.peek().value() <= newElem) {
                final var popped = maxStack.pop();
                pops += popped.times();
                maxSum -= popped.value() * popped.times();
                maxSum += newElem * popped.times();
            }
            maxSum += newElem;
            maxStack.push(new PoppedNumber(newElem, pops));

            result += maxSum - minSum;
        }

        return result;
    }

    private record PoppedNumber(long value, int times) {
    }
}