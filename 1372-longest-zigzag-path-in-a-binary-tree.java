/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public int longestZigZag(TreeNode root) {
        return solve(root, null, -1);
    }

    private int solve(TreeNode root, Direction dir, int depth) {
        if (root == null) {
            return depth;
        }

        final var leftResult = solve(root.left, Direction.LEFT, dir == Direction.LEFT ? 0 : depth + 1);
        final var rightResult = solve(root.right, Direction.RIGHT, dir == Direction.RIGHT ? 0 : depth + 1);

        return Math.max(leftResult, rightResult);
    }

    private enum Direction {
        LEFT, RIGHT;
    }
}