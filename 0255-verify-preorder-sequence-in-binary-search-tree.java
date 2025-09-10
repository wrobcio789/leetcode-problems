import java.util.Stack;

class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder.length == 0) {
            return true;
        }

        final var stack = new Stack<NodeConstraints>();
        stack.push(new NodeConstraints(new Range(Integer.MIN_VALUE, preorder[0]), new Range(preorder[0], Integer.MAX_VALUE)));

        for (int i = 1; i < preorder.length; i++) {
            final var value = preorder[i];
            while (!stack.isEmpty() && !stack.peek().fits(value)) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                return false;
            }

            final var nodeConstraints = stack.pop();

            if (nodeConstraints.fitsInLeft(value)) {
                final var leftRange = nodeConstraints.left();
                stack.push(new NodeConstraints(Range.EMPTY, nodeConstraints.right()));
                stack.push(new NodeConstraints(new Range(leftRange.lower(), value), new Range(value, leftRange.upper())));
            } else if (nodeConstraints.fitsInRight(value)) {
                final var rightRange = nodeConstraints.right();
                stack.push(new NodeConstraints(new Range(rightRange.lower(), value), new Range(value, rightRange.upper())));
            }
        }

        return true;
    }

    private record Range(int lower, int upper) {
        boolean fits(int value) {
            return value > lower && value < upper;
        }

        public static Range EMPTY = new Range(0, 0);
    }

    private record NodeConstraints(Range left, Range right) {

        boolean fits(int value) {
            return fitsInLeft(value) || fitsInRight(value);
        }

        boolean fitsInLeft(int value) {
            return left.fits(value);
        }

        boolean fitsInRight(int value) {
            return right.fits(value);
        }
    }
}