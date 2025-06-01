import java.util.HashMap;

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
    public int pathSum(TreeNode root, int targetSum) {
        final var prefixSumsOccurrences = new HashMap<Long, Integer>();
        prefixSumsOccurrences.put(0L, 1);

        return findAllWays(root, prefixSumsOccurrences, 0, targetSum);
    }

    public int findAllWays(TreeNode root, HashMap<Long, Integer> prefixSums, long prefixSum, int targetSum) {
        if (root == null) {
            return 0;
        }

        final var thisPrefixSum = prefixSum + root.val;
        final var thisResult = prefixSums.getOrDefault(thisPrefixSum - targetSum, 0);

        prefixSums.compute(thisPrefixSum, (key, value) -> value == null ? 1 : value + 1);
        final var result = thisResult
                + findAllWays(root.left, prefixSums, thisPrefixSum, targetSum)
                + findAllWays(root.right, prefixSums, thisPrefixSum, targetSum);
        prefixSums.computeIfPresent(thisPrefixSum, (key, value) -> value - 1);

        return result;
    }
}