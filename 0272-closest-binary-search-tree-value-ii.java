import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        final var heap = new PriorityQueue<>(Comparator.comparingDouble((Integer value) -> Math.abs(value - target)).reversed());
        traverse(root, target, heap, k);
        return heap.stream().toList();
    }

    private void traverse(TreeNode root, double target, PriorityQueue<Integer> heap, int k) {
        if (root == null) {
            return;
        }

        if (heap.size() < k || Math.abs(root.val - target) < Math.abs(heap.peek() - target)) {
            heap.add(root.val);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        traverse(root.left, target, heap, k);
        traverse(root.right, target, heap, k);
    }
}