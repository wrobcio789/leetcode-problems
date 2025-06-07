import java.util.Arrays;

class Solution {
    private static final int LEFT_SHIFT = 0;

    public String stringShift(String s, int[][] shifts) {
        final var totalShift = Arrays.stream(shifts).mapToInt(shift -> {
            final var dir = shift[0];
            final var amount = shift[1];
            return amount * ((dir == LEFT_SHIFT) ? -1 : 1);
        }).sum();

        final var n = s.length();
        final var resultBuilder = new StringBuilder();
        int beginIndex = ((-totalShift % n) + n) % n;

        for (int i = beginIndex; i < n; i++) {
            resultBuilder.append(s.charAt(i));
        }

        for (int i = 0; i < beginIndex; i++) {
            resultBuilder.append(s.charAt(i));
        }

        return resultBuilder.toString();
    }
}