import java.util.HashMap;
import java.util.Map;

class Trie {
    final Node root = new Node();

    public void insert(String word) {
        var current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.insert(word.charAt(i));
        }
        current.markEnd();
    }

    public boolean search(String word) {
        var current = root;
        for (int i = 0; i < word.length() && current != null; i++) {
            final var num = word.charAt(i);
            current = current.get(num);
        }

        if (current == null) {
            return false;
        }

        return current.ends();
    }

    public boolean startsWith(String prefix) {
        var current = root;
        for (int i = 0; i < prefix.length() && current != null; i++) {
            final var num = prefix.charAt(i);
            current = current.get(num);
        }

        return current != null;
    }


    private static class Node {
        final Map<Character, Node> children = new HashMap<>();
        boolean ends = false;

        Node insert(char num) {
            return children.computeIfAbsent(num, ignored -> new Node());
        }

        Node get(char num) {
            return children.getOrDefault(num, null);
        }

        void markEnd() {
            ends = true;
        }

        boolean ends() {
            return ends;
        }
    }
}
