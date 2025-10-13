import com.redteam.test.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int FALSE = 0;
    private static final int TRUE = 1;
    private static final int OR = 2;
    private static final int AND = 3;
    private static final int XOR = 4;
    private static final int NOT = 5;

    public int minimumFlips(TreeNode root, boolean result) {
        final var memory = new HashMap<TreeNode, Flips>();
        final var flips = traverse(root, memory);
        return result ? flips.one() : flips.zero();
    }

    private Flips traverse(TreeNode root, Map<TreeNode, Flips> mem) {
        final var cache = mem.get(root);
        if (cache != null) {
            return cache;
        }

        final var flips = switch (root.val) {
            case FALSE -> new Flips(0, 1);
            case TRUE -> new Flips(1, 0);
            case OR -> {
                final var leftFlips = traverse(root.left, mem);
                final var rightFlips = traverse(root.right, mem);
                yield new Flips(leftFlips.zero() + rightFlips.zero(), Math.min(leftFlips.one(), rightFlips.one()));
            }
            case AND -> {
                final var leftFlips = traverse(root.left, mem);
                final var rightFlips = traverse(root.right, mem);
                yield new Flips(Math.min(leftFlips.zero(), rightFlips.zero()), leftFlips.one() + rightFlips.one());
            }
            case XOR -> {
                final var leftFlips = traverse(root.left, mem);
                final var rightFlips = traverse(root.right, mem);
                yield new Flips(
                        Math.min(leftFlips.zero() + rightFlips.zero(), leftFlips.one() + rightFlips.one()),
                        Math.min(leftFlips.zero() + rightFlips.one(), leftFlips.one() + rightFlips.zero()));
            }
            case NOT -> {
                final var childFlips = root.left == null ? traverse(root.right, mem) : traverse(root.left, mem);
                yield new Flips(childFlips.one(), childFlips.zero());
            }
            default -> throw new IllegalStateException("Unexpected value: " + root.val);
        };

        mem.put(root, flips);
        return flips;
    }

    private record Flips(int zero, int one) {
    }
}