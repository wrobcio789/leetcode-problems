import java.util.List;

class Solution {
    public boolean validWordSquare(List<String> words) {
        final var n = words.size();
        for (int i = 0; i < n; i++) {
            if (words.get(i).length() > n || !isOkForN(words, i)) {
                return false;
            }
        }

        return true;
    }

    boolean isOkForN(List<String> words, int i) {
        final var n = words.size();

        for (int k = 0; k < n; k++) {
            if (getChar(words, i, k) != getChar(words, k, i)) {
                return false;
            }
        }

        return true;
    }

    private char getChar(List<String> words, int row, int column) {
        final var word = words.get(row);
        if (column < word.length()) {
            return word.charAt(column);
        }
        return 0;
    }
}