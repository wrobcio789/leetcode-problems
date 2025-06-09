import java.util.HashSet;

class Solution {
    public Node findRoot(List<Node> tree) {
        final var nodes = new HashSet<>(tree);

        for (final var node : tree) {
            for (final var children : node.children) {
                nodes.remove(children);
            }
        }

        return nodes.stream()
                .findAny()
                .orElseThrow();
    }
}