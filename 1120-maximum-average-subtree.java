class Solution {
    double result = Double.MIN_VALUE;

    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return result;
    }

    private Average dfs(TreeNode root) {
        int sum = root.val;
        int count = 1;

        if (root.left != null) {
            final var left = dfs(root.left);
            sum += left.sum();
            count += left.count();
        }

        if (root.right != null) {
            final var right = dfs(root.right);
            sum += right.sum();
            count += right.count();
        }

        final var result = new Average(sum, count);
        this.result = Math.max(this.result, result.avg());
        return result;
    }

    private record Average(int sum, int count) {
        public double avg() {
            return (double) sum / count;
        }
    }
}