import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        int previousEnd = Integer.MIN_VALUE;
        for (final var interval : intervals) {
            if (interval[0] < previousEnd) {
                return false;
            }
            previousEnd = interval[1];
        }

        return true;
    }
}