class Solution {
    public int maxProfit(int[] prices) {
        final var n = prices.length;

        int minSoFar = prices[0];
        int result = 0;
        for (int i = 1; i < n; i++) {
            result = Math.max(result, prices[i] - minSoFar);
            minSoFar = Math.min(minSoFar, prices[i]);
        }

        return result;
    }
}