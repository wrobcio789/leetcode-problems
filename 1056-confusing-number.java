class Solution {

    private static final int[] REVERSE_MAP = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    public boolean confusingNumber(int n) {
        var num = n;

        int reversedNum = 0;
        while (num > 0) {
            final var digit = num % 10;
            num /= 10;

            final var reversedDigit = REVERSE_MAP[digit];
            if (reversedDigit == -1) {
                return false;
            }

            reversedNum = (reversedNum * 10) + reversedDigit;
        }

        return reversedNum != n;

    }
}