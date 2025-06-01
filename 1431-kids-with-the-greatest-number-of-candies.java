import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = IntStream.of(candies).max().orElse(0);
        return Arrays.stream(candies)
                .mapToObj(count -> count + extraCandies >= maxCandies)
                .toList();
    }
}