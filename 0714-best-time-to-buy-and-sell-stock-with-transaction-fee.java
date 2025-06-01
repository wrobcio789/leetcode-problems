class Solution {
    private static final int HAS_STOCK = 0;
    private static final int NO_STOCK = 1;

    public int maxProfit(int[] prices, int fee) {
        final var n = prices.length;

        if (n == 0) {
            return 0;
        }

        final var dp = new int[2][n];
        dp[HAS_STOCK][0] = -prices[0];
        dp[NO_STOCK][0] = 0;

        for (int i = 1; i < n; i++) {
            dp[HAS_STOCK][i] = Math.max(dp[NO_STOCK][i - 1] - prices[i], dp[HAS_STOCK][i - 1]);
            dp[NO_STOCK][i] = Math.max(dp[HAS_STOCK][i - 1] + prices[i] - fee, dp[NO_STOCK][i - 1]);
        }

        return dp[NO_STOCK][n - 1];
    }
}