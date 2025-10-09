import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class Solution {
    private static final int LENGTH = 6;

    public void findSecretWord(String[] words, Master master) {
        var options = Arrays.stream(words).toList();

        while (true) {
            final var candidate = findOptimalCandidate(options);

            int result = master.guess(candidate);
            if (result == LENGTH) {
                return;
            }

            options = options.stream()
                    .filter(matches(candidate, result))
                    .toList();
        }
    }

    private String findOptimalCandidate(List<String> words) {
        final var n = words.size();
        final var tmpScore = new int[n];
        for(int i = 0; i<n; i++) {
            tmpScore[i] = (int) words.stream().filter(matches(words.get(i), 1)).count();
        }

        final var minWordIndex = Arrays.stream(tmpScore).min().orElseThrow();
        return words.get(minWordIndex);

    }

    private Predicate<String> matches(String word, int count) {
        return candidate -> {
            int matches = 0;
            for (int i = 0; i < LENGTH; i++) {
                if (word.charAt(i) == candidate.charAt(i)) {
                    matches++;
                }
            }
            return matches == count;
        };
    }
}
