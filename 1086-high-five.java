import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

class Solution {
    public int[][] highFive(int[][] items) {
        final var minHeapMap = new TreeMap<Integer, PriorityQueue<Integer>>();

        for (final var item : items) {
            final var studentId = item[0];
            final var score = item[1];

            minHeapMap.putIfAbsent(studentId, new PriorityQueue<>());
            final var heap = minHeapMap.get(studentId);

            if (heap.size() < 5) {
                heap.add(score);
            } else if (score > heap.peek()) {
                heap.poll();
                heap.add(score);
            }
        }

        return minHeapMap
                .entrySet()
                .stream()
                .map(entry -> {
                    final var studentId = entry.getKey();
                    final var heap = entry.getValue();
                    final var topFiveAverage = (int) heap.stream()
                            .mapToInt(value -> value)
                            .average()
                            .orElseThrow();
                    return new int[]{studentId, topFiveAverage};
                }).toArray(int[][]::new);
    }
}