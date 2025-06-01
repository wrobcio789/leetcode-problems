class Solution {
    public int uniquePaths(int rows, int cols) {
        int[][] dp = new int[2][cols];

        for (int i = 0; i < cols; i++) {
            dp[0][i] = 1;
        }

        for (int row = 1; row < rows; row++) {
            int currentRowIndex = row%2;
            int previousRowIndex = (row - 1) % 2;
            dp[currentRowIndex][0] = 1;

            for (int col = 1; col < cols; col++) {
                dp[currentRowIndex][col] = dp[previousRowIndex][col] + dp[currentRowIndex][col - 1];
            }
        }

        return dp[(rows - 1)%2][cols - 1];
    }
}