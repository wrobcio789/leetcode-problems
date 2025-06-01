import java.util.*;

class Solution {
    public int maxLevelSum(TreeNode root) {
        final var queue = new LinkedList<QueuePair>();
        queue.add(new QueuePair(root, 1));

        var currentLevel = 1;
        var currentSum = 0;
        var maxSum = Integer.MIN_VALUE;
        var resultLevel = 1;

        while (!queue.isEmpty()) {
            final var pair = queue.poll();
            final var node = pair.node();
            final var level = pair.level();

            if (level != currentLevel) {

                if (currentSum > maxSum) {
                    resultLevel = currentLevel;
                    maxSum = currentSum;
                }

                currentLevel = level;
                currentSum = 0;
            }

            if (node == null) {
                continue;
            }

            currentSum += node.val;

            queue.add(new QueuePair(node.left, level + 1));
            queue.add(new QueuePair(node.right, level + 1));
        }

        return resultLevel;

    }

    private record QueuePair(TreeNode node, int level) {
    }
}