import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    private final Trie trie = new Trie();

    public String replaceWords(List<String> dictionary, String sentence) {
        dictionary.forEach(trie::insert);

        return Arrays.stream(sentence.split(" "))
            .map(trie::findShortestEnd)
            .collect(Collectors.joining(" "));

    }

    private static class Trie {

        private final TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                node = node.getChildAt(word.charAt(i));
            }
            node.makeEnd();

        }

        public String findShortestEnd(String word) {
            TrieNode node = root;

            for(int i = 0; i < word.length(); i++) {
                node = node.getChildAt(word.charAt(i));
                if (node.isEnd()) {
                    return word.substring(0, i + 1);
                }
            }

            return word;
        }

        private static class TrieNode {
            private boolean isEnd = false;
            private final Map<Character, TrieNode> children = new HashMap<>();

            public TrieNode getChildAt(char character) {
                return children.computeIfAbsent(character, ignored -> new TrieNode());
            }

            public boolean isEnd() {
                return isEnd;
            }

            public void makeEnd() {
                this.isEnd = true;
            }
        }
    }
}