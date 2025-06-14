import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        final var start = toBeRemoved[0];
        final var end = toBeRemoved[1];

        final var result = new ArrayList<List<Integer>>();

        for (final var interval : intervals) {
            final var a = interval[0];
            final var b = interval[1];

            if (a < start) {
                if (b > end) {
                    result.add(List.of(a, start));
                    result.add(List.of(end, b));
                } else if (b > start){
                    result.add(List.of(a, start));
                } else {
                    result.add(List.of(a, b));
                }
            } else if (a == start || a < end) {
                if (b > end) {
                    result.add(List.of(end, b));
                }
            } else {
                result.add(List.of(a, b));
            }
        }

        return result;
    }
}