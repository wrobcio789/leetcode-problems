import java.util.PriorityQueue;

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        final var n = costs.length;

        final var leftHeap = new PriorityQueue<Integer>(candidates);
        final var rightHeap = new PriorityQueue<Integer>(candidates);

        int leftLimit = candidates, rightLimit = Math.max(n - candidates, leftLimit);
        for (int i = 0; i < leftLimit; i++) {
            leftHeap.add(costs[i]);
        }
        for (int i = n - 1; i >= rightLimit; i--) {
            rightHeap.add(costs[i]);
        }

        var total = 0L;
        for (int round = 0; round < k; round++) {
            int left = leftHeap.isEmpty() ? Integer.MAX_VALUE : leftHeap.peek();
            int right = rightHeap.isEmpty() ? Integer.MAX_VALUE : rightHeap.peek();

            if (right < left) {
                rightHeap.poll();
                total += right;

                if (leftLimit < rightLimit) {
                    rightLimit--;
                    rightHeap.add(costs[rightLimit]);
                }

            } else {
                leftHeap.poll();
                total += left;

                if(leftLimit < rightLimit) {
                    leftHeap.add(costs[leftLimit]);
                    leftLimit++;
                }
            }
        }

        return total;
    }
}