import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {

    public String addBoldTag(String s, String[] words) {
        final var intervals = findIntervals(s, words);
        if (intervals.size() == 0) {
            return s;
        }

        final var mergedIntervals = mergeIntervals(intervals);
        return buildBoldedText(s, mergedIntervals);
    }

    private static String buildBoldedText(String s, List<Interval> mergedIntervals) {
        int beginIndex = 0;
        final var resultBuilder = new StringBuilder();
        for (final var interval : mergedIntervals) {
            resultBuilder.append(s, beginIndex, interval.begin());
            resultBuilder.append("<b>");
            resultBuilder.append(s, interval.begin(), interval.end());
            resultBuilder.append("</b>");
            beginIndex = interval.end();
        }

        if (beginIndex < s.length()) {
            resultBuilder.append(s, beginIndex, s.length());
        }

        return resultBuilder.toString();
    }

    private static List<Interval> mergeIntervals(List<Interval> intervals) {
        final var mergedIntervals = new ArrayList<Interval>();
        int begin = intervals.get(0).begin();
        int end = intervals.get(0).end();
        for (final var interval : intervals) {

            if (interval.begin() > end) {
                mergedIntervals.add(new Interval(begin, end));
                begin = interval.begin();
                end = interval.end();
            } else {
                end = Math.max(end, interval.end());
            }
        }
        mergedIntervals.add(new Interval(begin, end));
        return mergedIntervals;
    }

    private static List<Interval> findIntervals(String s, String[] words) {
        final var intervals = new ArrayList<Interval>();

        for (final var word : words) {
            for (int index = 0; true; ) {
                index = s.indexOf(word, index);

                if (index != -1) {
                    intervals.add(new Interval(index, index + word.length()));
                    index++;
                    continue;
                }

                break;
            }
        }
        intervals.sort(Comparator.comparingInt(Interval::begin));
        return intervals;
    }

    private record Interval(int begin, int end) {
    }
}
