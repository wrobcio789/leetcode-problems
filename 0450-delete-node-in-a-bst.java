class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                final var lowestSuccessor = findLowestSuccessor(root);
                root.val = lowestSuccessor.val;
                root.right = deleteNode(root.right, lowestSuccessor.val);
                return root;
            }
        }
        return root;
    }

    private TreeNode findLowestSuccessor(TreeNode root) {
        return findLowest(root.right);
    }

    private TreeNode findLowest(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return findLowest(root.left);
    }
}