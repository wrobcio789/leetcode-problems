import java.util.*;

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        final var trie = new Trie();
        for (final var product : products) {
            trie.insert(product);
        }

        return trie.suggest(searchWord);
    }

    class Trie {
        private final Node root = new Node();

        public void insert(String word) {
            var current = root;
            for (int i = 0; i < word.length(); i++) {
                current = current.insert(word.charAt(i));
            }
            current.markEnd();
        }

        public List<List<String>> suggest(String searchWord) {
            final var result = new ArrayList<List<String>>();

            int i = 0;
            var prefix = "";
            var current = root;
            for (; i < searchWord.length(); i++) {
                final var character = searchWord.charAt(i);
                current = current.get(character);
                prefix = prefix + character;

                if (current == null) {
                    break;
                }

                result.add(current.findSuggestions(prefix));
            }

            for(; i<searchWord.length(); i++) {
                result.add(new ArrayList<>());
            }

            return result;
        }

        private static class Node {
            private static final int RANGE = 'z' - 'a' + 1;
            private final Node[] children = new Node[RANGE];
            boolean ends = false;

            Node insert(char num) {
                int index = num - 'a';
                if (children[index] == null) {
                    children[index] = new Node();
                }
                return children[index];
            }

            Node get(char num) {
                return children[num - 'a'];
            }

            void markEnd() {
                ends = true;
            }

            boolean ends() {
                return ends;
            }

            List<String> findSuggestions(String prefix) {
                final var result = new LinkedList<String>();
                findSuggestions(result, prefix);
                return result;
            }

            private void findSuggestions(final List<String> suggestions, String accu) {
                if (suggestions.size() == 3) {
                    return;
                }

                if (ends()) {
                    suggestions.add(accu);
                }

                for (int i = 0; i < children.length; i++) {
                    final var child = children[i];
                    if (child != null) {
                        child.findSuggestions(suggestions, accu + (char) (i + 'a'));
                    }
                }
            }
        }
    }
}
