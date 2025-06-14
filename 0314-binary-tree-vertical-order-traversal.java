import java.util.*;


class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        final Map<Integer, List<Integer>> columns = new TreeMap<>();

        bfs(columns, root);

        return columns
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .toList();
    }

    private void bfs(Map<Integer, List<Integer>> columns, TreeNode root) {
        if(root == null) {
            return;
        }

        final var queue = new LinkedList<QueuePair>();
        queue.add(new QueuePair(root, 0));

        while (!queue.isEmpty()) {
            final var pair = queue.poll();
            final var node = pair.root();
            final var column = pair.column();

            columns.putIfAbsent(column, new ArrayList<>());
            columns.get(column).add(node.val);

            if (node.left != null) {
                queue.add(new QueuePair(node.left, column - 1));
            }

            if (node.right != null) {
                queue.add(new QueuePair(node.right, column + 1));
            }
        }

    }

    private record QueuePair(TreeNode root, int column) {
    }
}