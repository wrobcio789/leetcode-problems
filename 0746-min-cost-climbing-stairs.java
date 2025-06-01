class Solution {
    public int minCostClimbingStairs(int[] cost) {
        final var n = cost.length;
        if (n < 2) {
            return 0;
        }

        int dp0 = 0, dp1 = 0, dp2 = 0;
        for (int i = 2; i <= n; i++) {
            dp2 = Math.min(dp1 + cost[i - 1], dp0 + cost[i - 2]);
            dp0 = dp1;
            dp1 = dp2;
        }

        return dp2;
    }
}