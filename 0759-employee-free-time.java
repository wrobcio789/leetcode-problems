import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        final var emps = schedule.size();
        final var assignedIntervals = new ArrayList<AssignedInterval>();
        for (int i = 0; i < emps; i++) {
            final var intervals = schedule.get(i);
            for (final var interval : intervals) {
                assignedIntervals.add(new AssignedInterval(i, interval.start, false));
                assignedIntervals.add(new AssignedInterval(i, interval.end, true));
            }
        }
        assignedIntervals.sort(Comparator.comparing(AssignedInterval::time).thenComparing(AssignedInterval::isEnd));

        final var result = new ArrayList<Interval>();
        int busyEmployeesCount = 0;
        int previousEnd = Integer.MIN_VALUE;
        for (final var event : assignedIntervals) {
            if (event.isEnd()) {
                previousEnd = Math.max(previousEnd, event.time());
                busyEmployeesCount--;
            } else {
                if (busyEmployeesCount == 0 && previousEnd != Integer.MIN_VALUE) {
                    result.add(new Interval(previousEnd, event.time()));
                }
                busyEmployeesCount++;
            }
        }

        return result;
    }

    private record AssignedInterval(int empId, int time, boolean isEnd) {
    }
}