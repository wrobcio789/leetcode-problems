import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        final var result = new ArrayList<List<Integer>>();
        if (root != null) {
            dfs(root, result);
        }

        return result;
    }

    private int dfs(TreeNode root, List<List<Integer>> heights) {
        final var leftHeigt = root.left == null ? 0 : dfs(root.left, heights);
        final var rightHeight = root.right == null ? 0 : dfs(root.right, heights);

        final var thisHeight = Math.max(leftHeigt, rightHeight) + 1;

        if (thisHeight - 1 >= heights.size()) {
            heights.add(thisHeight - 1, new ArrayList<>());
        }

        heights.get(thisHeight - 1).add(root.val);
        return thisHeight;
    }
}