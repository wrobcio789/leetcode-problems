class Solution {
    public int longestConsecutive(TreeNode root) {
        return dfs(root).consecutive();
    }

    public Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(0, 0, 0);
        }

        final var value = root.val;

        final var resultLeft = dfs(root.left);
        final var resultRight = dfs(root.right);

        final var leftAscending = isAscending(root.left, value) ? resultLeft.ascending() : 0;
        final var rightAscending = isAscending(root.right, value) ? resultRight.ascending() : 0;
        final var leftDescending = isDescending(root.left, value) ? resultLeft.descending() : 0;
        final var rightDescending = isDescending(root.right, value) ? resultRight.descending() : 0;

        final var localAscending = 1 + Math.max(leftAscending, rightAscending);
        final var localDescending = 1 + Math.max(leftDescending, rightDescending);

        final var localConsecutive = 1 + Math.max(leftAscending + rightDescending, leftDescending + rightAscending);

        final var consecutive = Math.max(localConsecutive,
                Math.max(resultLeft.consecutive(), resultRight.consecutive()));
        return new Result(consecutive, localAscending, localDescending);
    }

    private boolean isAscending(TreeNode root, int value) {
        return root != null && root.val + 1 == value;
    }

    private boolean isDescending(TreeNode root, int value) {
        return root != null && root.val == value + 1;
    }

    private record Result(int consecutive, int ascending, int descending) {
    }
}
