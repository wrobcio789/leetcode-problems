import java.util.List;

class Solution {
    public Node findRoot(List<Node> tree) {

        int xorSum = 0;
        for (final var node : tree) {
            xorSum ^= node.val;
            for (final var children : node.children) {
                xorSum ^= children.val;
            }
        }

        final var rootValue = xorSum;
        return tree.stream()
                .filter(node -> node.val == rootValue)
                .findAny()
                .orElseThrow();
    }
}
