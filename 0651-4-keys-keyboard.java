import java.util.stream.IntStream;

class Solution {
    public int maxA(int n) {
        final var df = new int[n];

        for (int i = 0; i < Math.min(n, 6); i++) {
            df[i] = i + 1;
        }

        for (int i = 6; i < n; i++) {
            df[i] = IntStream.of(
                    df[i - 3] * 2,
                    df[i - 4] * 3,
                    df[i - 5] * 4,
                    df[i - 6] * 5
            ).max().orElseThrow();
        }

        return df[n - 1];
    }
}