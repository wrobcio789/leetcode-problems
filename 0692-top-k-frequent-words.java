import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        final var freqMap = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ignored -> 1)));

        final var comparator = Comparator.comparingInt(FreqWord::frequency).thenComparing(Comparator.comparing(FreqWord::word).reversed());
        final var freqHeap = new PriorityQueue<>(comparator);

        freqMap.forEach((word, frequency) -> {
            final var candidate = new FreqWord(word, frequency);
            freqHeap.add(candidate);
            if (freqHeap.size() > k) {
                freqHeap.poll();
            }
        });

        return collectWordsInReversedOrder(freqHeap);
    }

    private static ArrayList<String> collectWordsInReversedOrder(PriorityQueue<FreqWord> freqHeap) {
        final var result = new ArrayList<String>();
        while (!freqHeap.isEmpty()) {
            result.add(freqHeap.poll().word());
        }
        Collections.reverse(result);

        return result;
    }


    private record FreqWord(String word, int frequency) {
    }
}