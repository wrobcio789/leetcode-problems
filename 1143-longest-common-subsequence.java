class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        final var n1 = text1.length();
        final var n2 = text2.length();

        final var dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                dp[i][j] = Math.max(
                        (text1.charAt(i - 1) == text2.charAt(j - 1) ? 1 : 0) + dp[i - 1][j - 1],
                        Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }

        return dp[n1][n2];
    }
}