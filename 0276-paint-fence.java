class Solution {
    public int numWays(int n, int k) {
        int[] dp = new int[3];
        dp[0] = k;
        dp[1] = k * k;

        if (n <= 2) {
            return dp[n - 1];
        }

        for (int i = 2; i < n; i++) {
            dp[2] = (dp[1] + dp[0]) * (k - 1);

            dp[0] = dp[1];
            dp[1] = dp[2];
        }

        return dp[2];
    }
}