class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int nodesFound = 0;
        TreeNode ancestor = null;

        final var stack = new Stack<StackPair>();
        stack.push(new StackPair(root, ProcessingState.BOTH_PENDING));

        while (!stack.empty()) {
            final var currentPair = stack.pop();
            final var node = currentPair.node();
            final var state = currentPair.processingState();

            if (node == null) {
                continue;
            }

            if (state == ProcessingState.BOTH_PENDING) {

                if (node == p || node == q) {
                    nodesFound++;
                    if (nodesFound == 2) {
                        return ancestor;
                    }
                    ancestor = node;
                }
                
                stack.push(new StackPair(node, ProcessingState.RIGHT_PENDING));
                stack.push(new StackPair(node.left, ProcessingState.BOTH_PENDING));
            } else if (state == ProcessingState.RIGHT_PENDING) {
                stack.push(new StackPair(node, ProcessingState.DONE));
                stack.push(new StackPair(node.right, ProcessingState.BOTH_PENDING));
            } else if (Objects.equals(node, ancestor)) {
                ancestor = stack.peek().node();
            }
        }

        return null;
    }

    record StackPair(TreeNode node, ProcessingState processingState) {
    }

    enum ProcessingState {
        BOTH_PENDING,
        RIGHT_PENDING,
        DONE
    }
}