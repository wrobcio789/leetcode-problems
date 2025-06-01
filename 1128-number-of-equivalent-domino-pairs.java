import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        return Arrays.stream(dominoes)
                .map(this::mapDomino)
                .collect(
                    Collectors.collectingAndThen(
                        Collectors.groupingBy(Function.identity(), HashMap::new, countingInt()),
                        occurrences -> occurrences.values()
                                .stream()
                                .mapToInt(this::occurrencesToEquivalentPairs)
                                .sum()
                    )
                );
    }

    private int occurrencesToEquivalentPairs(int occurrences) {
        return ((occurrences - 1) * occurrences) / 2;
    }

    private int mapDomino(int[] domino) {
        final int a = domino[0];
        final int b = domino[1];
        return a > b ? a * 10 + b : b * 10 + a;
    }

    private static <T> Collector<T, ?, Integer> countingInt() {
        return Collectors.summingInt(ignored -> 1);
    }
}