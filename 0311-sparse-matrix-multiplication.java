class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        final var resultHeight = mat1.length;
        final var resultWidth = mat2[0].length;

        final var kSize = mat1[0].length;

        final var result = new int[resultHeight][resultWidth];

        for (int i = 0; i < resultHeight; i++) {
            for (int j = 0; j < resultWidth; j++) {
                int total = 0;
                for (int k = 0; k < kSize; k++) {
                    total += mat1[i][k] * mat2[k][j];
                }

                result[i][j] = total;
            }
        }

        return result;
    }
}