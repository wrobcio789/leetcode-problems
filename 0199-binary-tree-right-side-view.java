import java.util.*;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        final var queue = new LinkedList<QueuePair>();
        queue.add(new QueuePair(root, 0));

        final var result = new ArrayList<Integer>();

        TreeNode previousNode = null;
        int previousLevel = 0;

        while (!queue.isEmpty()) {
            final var pair = queue.poll();
            final var node = pair.node();
            final var level = pair.level();

            if (previousLevel != level) {
                previousLevel = level;
                result.add(previousNode.val);
            }

            if (node == null) {
                continue;
            }
            previousNode = node;


            queue.add(new QueuePair(node.left, level + 1));
            queue.add(new QueuePair(node.right, level + 1));
        }

        return result;
    }

    record QueuePair(TreeNode node, int level) {
    }
}