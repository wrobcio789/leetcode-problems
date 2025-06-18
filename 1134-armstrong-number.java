class Solution {
    public boolean isArmstrong(int n) {
        final var k = countDigits(n);
        final var sum = sumKthPowerOfDigits(n, k);
        return sum == n;
    }

    private int pow(int a, int exp) {
        int result = 1;
        for(int i = 0; i<exp; i++) {
            result *= a;
        }

        return result;
    }

    private int sumKthPowerOfDigits(int n, int k) {
        var sum = 0;
        for (; n > 0; n /= 10) {
            final var digit = n % 10;
            sum += pow(digit, k);
        }

        return sum;
    }

    private int countDigits(int n) {
        for (int i = (int) 1e9, k = 10; i >= 1; i /= 10, k--) {
            if (n >= i) {
                return k;
            }
        }

        return 0;
    }
}