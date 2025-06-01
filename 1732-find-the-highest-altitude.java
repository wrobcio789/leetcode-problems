import java.util.Arrays;

class Solution {
    public int largestAltitude(int[] gains) {
        return Arrays.stream(gains)
                .boxed()
                .reduce(
                        new PrefixResult(0, 0),
                        PrefixResult::withGain,
                        (ignored1, ignored2) -> {
                            throw new UnsupportedOperationException();
                        }
                ).result();
    }

    private record PrefixResult(int result, int altitude) {

        public PrefixResult withGain(int gain) {
            final var newAltitude = altitude + gain;
            return new PrefixResult(Math.max(result, newAltitude), newAltitude);
        }
    }
}