import java.util.Arrays;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int a = 1, b = Integer.MAX_VALUE;

        while (a < b) {
            final var mid = a + (b - a) / 2;
            final var result = calculateHoursNeeded(piles, mid);

            if (result <= h) {
                b = mid;
            } else {
                a = mid + 1;
            }
        }

        return a;
    }

    private int calculateHoursNeeded(int[] piles, int k) {
        return Arrays.stream(piles).map(value -> (value + k - 1) / k).sum();
    }
}