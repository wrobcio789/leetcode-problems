import java.util.ArrayList;

class Codec {

    public TreeNode encode(Node root) {
        return encode(root, Side.LEFT);
    }

    public TreeNode encode(Node root, Side side) {
        if (root == null) {
            return null;
        }

        final var encodedRoot = new TreeNode(root.val);

        TreeNode current = encodedRoot;
        if (side == Side.LEFT) {
            for (final var child : root.children) {
                current.left = encode(child, Side.RIGHT);
                current = current.left;
            }
        } else {
            for (final var child : root.children) {
                current.right = encode(child, Side.LEFT);
                current = current.right;
            }
        }

        return encodedRoot;
    }

    public Node decode(TreeNode root) {
        return decode(root, Side.LEFT);
    }

    private Node decode(TreeNode root, Side side) {
        if (root == null) {
            return null;
        }

        final var decodedRoot = new Node(root.val, new ArrayList<>());

        if (side == Side.LEFT) {
            TreeNode current = root.left;
            while (current != null) {
                decodedRoot.children.add(decode(current, Side.RIGHT));
                current = current.left;
            }
        } else {
            TreeNode current = root.right;
            while (current != null) {
                decodedRoot.children.add(decode(current, Side.LEFT));
                current = current.right;
            }
        }

        return decodedRoot;

    }

    private enum Side {LEFT, RIGHT}
}
