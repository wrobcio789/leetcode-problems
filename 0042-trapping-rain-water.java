import java.util.Stack;

class Solution {
    public int trap(int[] heights) {
        final var peaks = new Stack<Peak>();

        int result = 0;
        for (int x = 0; x < heights.length; x++) {
            final var height = heights[x];

            while (!peaks.isEmpty() && peaks.peek().height < height) {
                final var base = peaks.pop();
                if (peaks.isEmpty()) {
                    break;
                }
                final var wall = peaks.peek();
                result += (Math.min(wall.height, height) - base.height()) * (x - wall.x() - 1);
            }

            peaks.add(new Peak(x, height));
        }

        return result;
    }

    private record Peak(int x, int height) {
    }
}