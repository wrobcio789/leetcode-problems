import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        final var n = nums.length;
        final var heap = new PriorityQueue<Integer>(11, Comparator.naturalOrder());

        for (int i = 0; i < Math.min(n, k); i++) {
            heap.add(nums[i]);
        }

        for (int i = k; i < n; i++) {
            final var num = nums[i];
            if (num > heap.peek()) {
                heap.poll();
                heap.add(num);
            }
        }

        return heap.peek();
    }
}