import java.util.stream.IntStream;

class Solution {
    public int longestConsecutive(TreeNode root) {
        return dfs(root, 1, root.val);
    }

    private int dfs(TreeNode root, int seq, int fathersValue) {
        final var isPartOfSeq = root.val == fathersValue + 1;
        final var newSeq = isPartOfSeq ? seq + 1 : 1;

        int resultRight = newSeq;
        if (root.right != null) {
            resultRight = dfs(root.right, newSeq, root.val);
        }

        int resultLeft = newSeq;
        if (root.left != null) {
            resultLeft = dfs(root.left, newSeq, root.val);
        }

        return IntStream.of(newSeq, resultLeft, resultRight)
                .max()
                .getAsInt();
    }
}