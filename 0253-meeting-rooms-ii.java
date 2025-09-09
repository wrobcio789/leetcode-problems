import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        final var endingQueue = new PriorityQueue<Integer>();
        endingQueue.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            final var interval = intervals[i];
            if (interval[0] >= endingQueue.peek()) {
                endingQueue.poll();
            }

            endingQueue.add(interval[1]);
        }

        return endingQueue.size();
    }
}