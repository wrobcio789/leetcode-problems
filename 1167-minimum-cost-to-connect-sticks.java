import java.util.PriorityQueue;

class Solution {
    public int connectSticks(int[] sticks) {
        final var heap = new PriorityQueue<Integer>();
        for (final var stick : sticks) {
            heap.add(stick);
        }

        int result = 0;
        while (heap.size() > 1) {
            final var a = heap.poll();
            final var b = heap.poll();
            final var connectedStick = a + b;

            result += connectedStick;
            heap.add(connectedStick);
        }

        return result;
    }
}