import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        return concat(
               Stream.of(root.val),
                leftBoundary(root).stream(),
                leaves(root).stream(),
                rightBoundary(root).stream()
        ).toList();
    }

    private List<Integer> leftBoundary(TreeNode root) {
        if(root.left == null) {
            return Collections.emptyList();
        }

        final var bag = new ArrayList<Integer>();
        leftBoundary(bag, root.left);

        return bag;
    }

    private List<Integer> rightBoundary(TreeNode root) {
        if(root.right == null) {
            return Collections.emptyList();
        }

        final var bag = new ArrayList<Integer>();
        rightBoundary(bag, root.right);

        return bag;
    }

    private List<Integer> leaves(TreeNode root) {
        if(root.right == null && root.left == null) {
            return Collections.emptyList();
        }

        final var bag = new ArrayList<Integer>();
        leaves(bag, root);

        return bag;
    }


    private void leftBoundary(List<Integer> bag, TreeNode root) {
        if (root.left != null) {
            bag.add(root.val);
            leftBoundary(bag, root.left);
        } else if (root.right != null) {
            bag.add(root.val);
            leftBoundary(bag, root.right);
        }
    }

    private void leaves(List<Integer> bag, TreeNode root) {
        if(root.left == null && root.right == null) {
            bag.add(root.val);
            return;
        }

        if (root.left != null) {
            leaves(bag, root.left);
        }

        if (root.right != null) {
            leaves(bag, root.right);
        }
    }

    private void rightBoundary(List<Integer> bag, TreeNode root) {
        if (root.right != null) {
            rightBoundary(bag, root.right);
            bag.add(root.val);
        } else if (root.left != null) {
            rightBoundary(bag, root.left);
            bag.add(root.val);
        }
    }

    @SafeVarargs
    private <T> Stream<T> concat(final Stream<T>... streams) {
        return Arrays.stream(streams).flatMap(Function.identity());
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}