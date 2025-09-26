import java.util.Arrays;

class Solution {
    public int minCostII(int[][] costs) {
        final var n = costs.length;
        final var k = costs[0].length;

        final var dp = new int[n][k];
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j] = findMinForIndexNotEqualK(dp[i - 1], j) + costs[i][j];
            }
        }

        return Arrays.stream(dp[n-1])
                .min()
                .orElseThrow();
    }

    private int findMinForIndexNotEqualK(int[] arr, int k) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (i != k) {
                result = Math.min(result, arr[i]);
            }
        }

        return result;
    }
}