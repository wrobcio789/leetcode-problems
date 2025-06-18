class Solution {
    private static final int NO_VALUE = Integer.MAX_VALUE;
    private int result = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root != null) {
            dfs(root);
        }
        return result;
    }

    public int dfs(TreeNode root) {
        int left = root.left == null ? root.val : dfs(root.left);
        int right = root.right == null ? root.val : dfs(root.right);

        if (left != NO_VALUE && left == right && left == root.val) {
            result++;
            return left;
        }

        return NO_VALUE;
    }
}