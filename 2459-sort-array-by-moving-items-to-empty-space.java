class Solution {
    public int sortArray(int[] nums) {
        return Math.min(
                calculateForZeroOnTheLeftVariant(nums),
                calculateForZeroOnTheRightVariant(nums));
    }

    private static int calculateForZeroOnTheRightVariant(int[] nums) {
        final var n = nums.length;
        final int[] pos = calculatePositions(nums);

        int result = swapUntilPossibleRightVariant(pos);
        for (int i = 0; i < n - 1; i++) {
            if (pos[i + 1] != i) {
                int tmp = pos[i + 1];
                pos[i + 1] = pos[0];

                pos[0] = tmp;
                result++;
                result += swapUntilPossibleRightVariant(pos);
            }
        }

        return result;
    }

    private static int calculateForZeroOnTheLeftVariant(int[] nums) {
        final var n = nums.length;
        final int[] pos = calculatePositions(nums);

        int result = swapUntilPossibleLeftVariant(pos);

        for (int i = 1; i < n; i++) {
            if (pos[i] != i) {

                int tmp = pos[i];
                pos[i] = pos[0];
                pos[0] = tmp;

                result++;
                result += swapUntilPossibleLeftVariant(pos);
            }
        }

        return result;
    }

    private static int swapUntilPossibleRightVariant(int[] pos) {
        final var n = pos.length;

        int result = 0;
        while (pos[0] != n - 1) {
            int zeroPos = pos[0];
            int whatShouldBeThere = zeroPos + 1;
            int whatShouldBeTherePos = pos[whatShouldBeThere];

            pos[0] = whatShouldBeTherePos;
            pos[whatShouldBeThere] = zeroPos;

            result++;
        }
        return result;
    }

    private static int swapUntilPossibleLeftVariant(int[] pos) {
        int result = 0;
        while (pos[0] != 0) {
            int zeroPos = pos[0];
            int whatShouldBeTherePos = pos[zeroPos];

            pos[0] = whatShouldBeTherePos;
            pos[zeroPos] = zeroPos;

            result++;
        }
        return result;
    }

    private static int[] calculatePositions(int[] nums) {
        final var n = nums.length;

        final var pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums[i]] = i;
        }
        return pos;
    }
}