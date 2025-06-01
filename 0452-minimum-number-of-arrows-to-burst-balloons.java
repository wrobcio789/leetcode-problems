import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparing(arr -> arr[1]));

        if(points.length == 0) {
            return 0;
        }

        int currentEnd = points[0][1];
        int result = 1; //Counted in advance to burst first balloon

        for (final var balloon : points) {
            final var start = balloon[0];
            final var ends = balloon[1];

            if (start > currentEnd) {
                result++;
                currentEnd = ends;
            }
        }

        return result;
    }

}