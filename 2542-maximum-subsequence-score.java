import java.util.*;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        final var n = nums1.length;
        final var pairs = IntStream.range(0, n)
                .mapToObj(i -> new Pair(nums1[i], nums2[i]))
                .sorted(Comparator.comparing(Pair::num2).reversed())
                .toArray(Pair[]::new);

        final var heap = new HeapWithSum();
        for (int i = 0; i < k; i++) {
            final var pair = pairs[i];
            heap.add(pair.num1());
        }

        long result = heap.getSum() * pairs[k - 1].num2();

        for (int i = k; i < n; i++) {
            final var pair = pairs[i];
            heap.add(pair.num1());
            heap.poll();

            result = Math.max(result, heap.getSum() * pairs[i].num2());
        }

        return result;
    }

    record Pair(int num1, int num2) {
    }

    static class HeapWithSum extends PriorityQueue<Integer> {

        private long sum = 0;

        @Override
        public boolean offer(Integer e) {
            final var added = super.offer(e);
            if (added) {
                sum += e;
            }
            return added;
        }

        @Override
        public Integer poll() {
            Integer e = super.poll();
            if (e != null) {
                sum -= e;
            }
            return e;
        }

        @Override
        public boolean remove(Object o) {
            boolean removed = super.remove(o);
            if (removed) {
                sum -= (int) o;
            }
            return removed;
        }

        @Override
        public void clear() {
            super.clear();
            sum = 0;
        }

        public long getSum() {
            return sum;
        }
    }
}