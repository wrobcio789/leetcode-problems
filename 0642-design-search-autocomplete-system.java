import java.util.*;

class AutocompleteSystem {
    private final Trie trie = new Trie();
    private StringBuilder wordBuilder = new StringBuilder();
    private Trie.TrieNode index = trie.root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        final var n = sentences.length;
        for (int i = 0; i < n; i++) {
            trie.insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            final var word = wordBuilder.toString();
            trie.insert(word, 1);

            index = trie.root;
            wordBuilder = new StringBuilder();

            return Collections.emptyList();
        }

        wordBuilder.append(c);
        index = index.goTo(c);

        return index.findTop3();
    }

    private static class Trie {
        private final TrieNode root = new TrieNode();

        public void insert(String sentence, int times) {
            var currentChild = root;
            for (int i = 0; i < sentence.length(); i++) {
                final var character = sentence.charAt(i);
                currentChild = currentChild.goTo(character);
                currentChild.remember(sentence, times);
            }
        }

        private static class TrieNode {
            private final Map<Character, TrieNode> children = new HashMap<>();
            private final Map<String, Integer> occurrences = new HashMap<>();
            private final Comparator<String> comparator = Comparator.<String, Integer>comparing(occurrences::get).reversed().thenComparing(str -> str);

            public TrieNode goTo(char character) {
                return children.computeIfAbsent(character, key -> new TrieNode());
            }

            public void remember(String word, int times) {
                occurrences.merge(word, times, Integer::sum);
            }

            public List<String> findTop3() {
                return occurrences.keySet()
                        .stream()
                        .sorted(comparator)
                        .limit(3)
                        .toList();
            }

        }
    }
}