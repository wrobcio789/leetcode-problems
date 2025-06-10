class Solution {
    int maxSize = 1;

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return maxSize;
    }

    public Result dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new Result(true, 1, root.val, root.val);
        }

        Result leftResult = new Result(true, 0, root.val, root.val);
        if (root.left != null) {
            leftResult = dfs(root.left);
        }

        Result rightResult = new Result(true, 0, root.val, root.val);
        if (root.right != null) {
            rightResult = dfs(root.right);
        }

        final var isBst = leftResult.isBst() && rightResult.isBst() && (root.right == null || rightResult.min() > root.val) && (root.left == null || leftResult.max() < root.val);
        final var newSize = leftResult.size() + rightResult.size() + 1;
        final var min = leftResult.min();
        final var max = rightResult.max();

        if (isBst) {
            maxSize = Math.max(maxSize, newSize);
        }

        return new Result(isBst, newSize, min, max);
    }

    private record Result(boolean isBst, int size, int min, int max) {}
}