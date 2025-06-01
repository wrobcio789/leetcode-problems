import java.util.HashMap;
import java.util.Optional;

class Solution {

    public int equalPairs(int[][] grid) {
        final var root = new TrieNode();

        final int n = grid.length;
        for (int i = 0; i < n; i++) {
            var currentChild = root;
            for (int j = 0; j < n; j++) {
                currentChild = currentChild.createChild(grid[j][i]);
            }
            currentChild.finish();
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            var currentChild = root;
            for (int j = 0; j < n && currentChild != null; j++) {
                currentChild = currentChild.goToChild(grid[i][j]);
            }
            result += Optional.ofNullable(currentChild).map(TrieNode::getVisits).orElse(0);
        }

        return result;
    }

    static private class TrieNode {
        private final HashMap<Integer, TrieNode> children = new HashMap<>();
        private int visits = 0;

        public TrieNode createChild(int num) {
            return children.computeIfAbsent(num, key -> new TrieNode());
        }

        public TrieNode goToChild(int num) {
            return children.getOrDefault(num, null);
        }

        public int getVisits() {
            return visits;
        }

        public void finish() {
            this.visits++;
        }
    }
}