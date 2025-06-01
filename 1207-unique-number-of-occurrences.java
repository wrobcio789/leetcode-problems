import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        final var occurrences = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));

        return occurrences.size() == new HashSet<>(occurrences.values()).size();
    }
}