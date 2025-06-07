import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int countElements(int[] arr) {
        final var occurrences = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toSet());

        return (int) Arrays.stream(arr)
                .filter(value -> occurrences.contains(value + 1))
                .count();
    }
}