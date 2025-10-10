import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

class Solution {
    public int visibleMountains(int[][] peaks) {
        final var mountains = Arrays.stream(peaks)
                .map(peak -> new Mountain(peak[0] - peak[1], peak[0] + peak[1]))
                .sorted(Comparator.comparing(Mountain::start).thenComparing(Comparator.comparing(Mountain::end).reversed()))
                .toList();

        int result = 0;
        int currentEnd = Integer.MIN_VALUE;
        for (int i = 0; i < mountains.size(); i++) {
            final var mountain = mountains.get(i);
            if (mountain.end() > currentEnd) {
                if (!(i + 1 < mountains.size() && Objects.equals(mountain, mountains.get(i + 1)))) {
                    result++;
                }
                currentEnd = mountain.end();
            }
        }

        return result;
    }

    private record Mountain(int start, int end) {
    }
}