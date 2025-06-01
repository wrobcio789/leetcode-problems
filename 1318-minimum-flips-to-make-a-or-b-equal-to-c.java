class Solution {
    public int minFlips(int a, int b, int c) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            final var a1 = a & 1;
            final var b1 = b & 1;
            final var c1 = c & 1;

            if (c1 == 1) {
                if (a1 == 0 && b1 == 0) {
                    result += 1;
                }
            } else {
                result += a1 + b1;
            }

            a >>= 1;
            b >>= 1;
            c >>= 1;
        }

        return result;

    }
}