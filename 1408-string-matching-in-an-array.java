import java.util.Arrays;
import java.util.List;


class Solution {
    private static final int NUMS = 'z' - 'a' + 1;

    private final Trie trie = new Trie();

    public List<String> stringMatching(String[] words) {
        Arrays.stream(words)
            .forEach(this::insertAllSuffixes);

        return Arrays.stream(words)
            .filter(this::containsWord)
            .toList();
    }

    private void insertAllSuffixes(String word) {
        for(int i = 0; i < word.length(); i++) {
            trie.insertWord(word.substring(i));
        }
    }

    private boolean containsWord(String word) {
        return trie.countVisits(word) > 1;
    }

    private static class Trie {

        private TrieNode root = new TrieNode();

        public void insertWord(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                final char character = word.charAt(i);
                node = node.childAt(character);
                node.incrementVisits();
            }
        }

        public int countVisits(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                final char character = word.charAt(i);
                node = node.childAt(character);
            }

            return node.getVisits();
        }

        private static class TrieNode {
            private int visits = 0;
            private final TrieNode[] children = new TrieNode[NUMS];

            public void incrementVisits() {
                visits += 1;
            }

            public int getVisits() {
                return visits;
            }

            public TrieNode childAt(char character) {
                final var index = character - 'a';
                if(children[index] != null) {
                    return children[index];
                }

                return children[index] = new TrieNode();
            }
        }
    }
}