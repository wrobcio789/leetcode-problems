class Solution {
    public int[] countBits(int n) {
        final var result = new int[n + 1];
        result[0] = 0;

        int writingIndex = 1;
        for (int cpSize = 1, readingIndex = 0; writingIndex <= n; cpSize *= 2, readingIndex = 0) {
            int limit = Math.min(n, writingIndex + cpSize - 1);
            while (writingIndex <= limit) {
                result[writingIndex++] = result[readingIndex++] + 1;
            }
        }

        return result;
    }
}