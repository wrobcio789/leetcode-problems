import java.util.Arrays;

class Solution {
    public int maximizeSweetness(int[] sweetness, int k) {
        int min = 0, max = avg(sweetness, k + 1);
        int result = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (satisfies(sweetness, mid, k + 1)) {
                min = mid + 1;
                result = mid;
            } else {
                max = mid - 1;
            }
        }
        return result;
    }

    private int avg(int[] sweetness, int k) {
        return Arrays.stream(sweetness).sum() / k;
    }

    private boolean satisfies(int[] sweetness, int k, int chunks) {
        int gatheredChunks = 0;
        int currentSum = 0;

        for (int i = 0; i < sweetness.length; i++) {
            currentSum += sweetness[i];

            if (currentSum >= k) {
                gatheredChunks++;
                currentSum = 0;
            }
        }
        return gatheredChunks >= chunks;
    }
}