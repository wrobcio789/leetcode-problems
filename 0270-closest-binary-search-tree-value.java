import java.util.Stack;

class Solution {
    public int closestValue(TreeNode root, double target) {
        return findClosestUsingStackDfs(root, target);
    }

    private int findClosestUsingStackDfs(TreeNode root, double target) {
        final var stack = new Stack<TreeNode>();
        stack.push(root);

        var minDifSoFar = Double.MAX_VALUE;
        var result = Integer.MAX_VALUE;

        while(!stack.isEmpty()) {
            final var node = stack.pop();

            final var dif = Math.abs(target - node.val);

            if(dif <= minDifSoFar) {
                final var valueCandidate = dif == minDifSoFar ? result : node.val;
                result = Math.min(valueCandidate, node.val);
                minDifSoFar = dif;
            }

            if(target < node.val && node.left != null) {
                stack.push(node.left);
            }

            if(target > node.val && node.right != null) {
                stack.push(node.right);
            }
        }

        return result;
    }
}