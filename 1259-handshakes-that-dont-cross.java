class Solution {
    private static final long MOD = 1000_000_000 + 7L;

    public int numberOfWays(int numPeople) {
        final var n = numPeople / 2;

        final var dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int k = 0; k < i; k++) {
                dp[i] += ((long) dp[k] * dp[i - k - 1]) % MOD;
                dp[i] %= MOD;
            }
        }

        return dp[n];
    }
}