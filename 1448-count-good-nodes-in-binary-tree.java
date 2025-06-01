import org.antlr.v4.runtime.tree.Tree;

import java.util.*;

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
    public int goodNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return countGood(root, root.val);
    }

    private int countGood(TreeNode root, int limit) {
        if(root == null) {
            return 0;
        }

        final var newLimit = Math.max(root.val, limit);
        return (root.val >= limit ? 1 : 0) + countGood(root.left, newLimit) + countGood(root.right, newLimit);
    }
}