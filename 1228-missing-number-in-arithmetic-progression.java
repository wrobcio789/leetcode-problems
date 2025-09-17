class Solution {
    public int missingNumber(int[] arr) {
        final var n = arr.length;
        final var diff = (arr[n - 1] - arr[0]) / n;

        int min = 1, max = arr.length - 1;
        while (min < max) {
            final var mid = (max + min) / 2;
            final var expectedPos = arr[0] + mid * diff;

            if (arr[mid] == expectedPos) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return arr[0] + min * diff;
    }
}