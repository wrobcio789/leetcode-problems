public class Solution extends GuessGame {
    public int guessNumber(int n) {

        int min = 0, max = n;

        while (min <= max) {
            final var mid = min + (max - min) / 2;

            final var result = guess(mid);
            if (result < 0) {
                max = mid - 1;
            } else if (result > 0) {
                min = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}