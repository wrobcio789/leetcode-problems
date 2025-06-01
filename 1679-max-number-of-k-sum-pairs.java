import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int maxOperations(int[] nums, int k) {
        final var occurrences = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.summingInt(num -> 1)));

        return occurrences
                .entrySet()
                .stream()
                .map(entry -> Math.min(entry.getValue(), occurrences.getOrDefault(k - entry.getKey(), 0)))
                .mapToInt(value -> value)
                .sum() / 2;
    }
}