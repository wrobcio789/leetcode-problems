import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int largestUniqueNumber(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.summingInt(e -> 1)))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .orElse(-1);
    }
}