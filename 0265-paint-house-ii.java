import java.util.Arrays;

class Solution {
    public int minCostII(int[][] costs) {
        final var n = costs.length;
        final var k = costs[0].length;

        final var dp = new int[2][k];
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < n; i++) {
            final var prevDp = dp[(i + 1) % 2];
            final var curDp = dp[i%2];
            final var minimums = findMinimum(prevDp);
            for (int j = 0; j < k; j++) {
                curDp[j] = costs[i][j] + (j != minimums.smallestIndex() ? minimums.smallest() : minimums.secondSmallest());
            }
        }

        return Arrays.stream(dp[(n-1) % 2])
                .min()
                .orElseThrow();
    }

    private MinimumNumbers findMinimum(int[] arr) {
        int min = arr[0], minIndex = 0, secondMin = Integer.MAX_VALUE;
        for(int i = 1; i<arr.length; i++) {
            if(arr[i] < min) {
                secondMin = min;
                min = arr[i];
                minIndex = i;
            } else if (arr[i] < secondMin) {
                secondMin = arr[i];
            }
        }

        return new MinimumNumbers(min, secondMin, minIndex);
    }

    private record MinimumNumbers(int smallest, int secondSmallest, int smallestIndex) {}
}