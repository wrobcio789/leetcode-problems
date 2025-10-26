import java.util.PriorityQueue;

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {

        final var usedLadders = new PriorityQueue<Integer>();

        for (int i = 0; i < heights.length - 1; i++) {
            final var jump = heights[i + 1] - heights[i];
            if (jump < 0) {
                continue;
            }

            if (ladders > 0) {
                usedLadders.add(jump);
                ladders--;
            } else if (usedLadders.isEmpty() || usedLadders.peek() > jump) {
                bricks -= jump;
            } else {
                bricks -= usedLadders.poll();
                usedLadders.add(jump);
            }

            if (bricks < 0) {
                return i;
            }
        }

        return heights.length - 1;
    }
}