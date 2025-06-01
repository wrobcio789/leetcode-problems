class Solution {
    private static final int MODULO = 1000_000_000 + 7;

    public int numTilings(int n) {
        int d1 = 1;
        int d2 = 2;
        int d3 = 5;
        int d4 = 0;

        if (n == 1) {
            return d1;
        }

        if (n == 2) {
            return d2;
        }

        if (n == 3) {
            return d3;
        }

        for (int i = 4; i <= n; i++) {
            d4 = (int) ((2L * d3 + d1) % MODULO);
            d1 = d2;
            d2 = d3;
            d3 = d4;

        }
        return d4;
    }
}