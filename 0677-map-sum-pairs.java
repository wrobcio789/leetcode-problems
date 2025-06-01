import java.util.Map;
import java.util.HashMap;

class MapSum {
    private final Trie trie = new Trie();

    public void insert(String key, int val) {
        trie.insert(key, val);
    }
    
    public int sum(String prefix) {
        return trie.getSum(prefix);    
    }

    private static class Trie {

        private final TrieNode root = new TrieNode();

        public void insert(String word, int value) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                node = node.getChildAt(word.charAt(i));
            }

            int diff = value - node.getCurrentValue();
            node.setCurrentValue(value);

            node = root;
            for (int i = 0; i < word.length(); i++) {
                node.addAccumulatedSum(diff);
                node = node.getChildAt(word.charAt(i));
            }

        }

        public int getSum(String word) {
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                node = node.getChildAt(word.charAt(i));
            }

            return node.getSum();
        }

        private static class TrieNode {
            private int childrenSums = 0;
            private int value = 0;
            private final Map<Character, TrieNode> children = new HashMap<>();

            public TrieNode getChildAt(char character) {
                return children.computeIfAbsent(character, ignored -> new TrieNode());
            }

            public int getSum() {
                return childrenSums + value;
            }

            public int getCurrentValue() {
                return value;
            }

            public void setCurrentValue(int value) {
                this.value = value;
            }

            public void addAccumulatedSum(int value) {
                childrenSums += value;
            }
        }
    }
}