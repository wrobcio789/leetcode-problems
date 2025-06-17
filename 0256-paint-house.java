import java.util.stream.IntStream;

class Solution {
    private static final int N_COLORS = 3;

    public int minCost(int[][] costs) {
        final var n = costs.length;
        final var df = new int[2][N_COLORS];

        for (int i = 0; i < N_COLORS; i++) {
            df[0][i] = costs[0][i];
        }

        for (int i = 1; i < n; i++) {
            final var currentIndex = i & 1;
            final var lastIndex = (i - 1) & 1;
            df[currentIndex][0] = Math.min(df[lastIndex][1], df[lastIndex][2]) + costs[i][0];
            df[currentIndex][1] = Math.min(df[lastIndex][0], df[lastIndex][2]) + costs[i][1];
            df[currentIndex][2] = Math.min(df[lastIndex][0], df[lastIndex][1]) + costs[i][2];
        }

        final var lastIndex = (n - 1) & 1;
        return IntStream.of(df[lastIndex][0], df[lastIndex][1], df[lastIndex][2])
            .min()
            .orElseThrow();
    }
}