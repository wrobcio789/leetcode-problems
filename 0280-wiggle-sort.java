import java.util.Arrays;
import java.util.Random;

class Solution {
    public void wiggleSort(int[] nums) {
        final var n = nums.length;
        final var numsCopy = Arrays.stream(nums).toArray();
        final var median = quickselect_median_ceil(nums);

        int lessIndex = 0, moreIndex = 0;
        int writingIndex = 0;
        for (; writingIndex < n; writingIndex++) {
            if (writingIndex % 2 == 0) {
                while (lessIndex < n && numsCopy[lessIndex] >= median) {
                    lessIndex++;
                }

                if (lessIndex == n) {
                    nums[writingIndex] = median;
                } else {
                    nums[writingIndex] = numsCopy[lessIndex++];
                }

            } else {
                while (moreIndex < n && numsCopy[moreIndex] <= median) {
                    moreIndex++;
                }

                if (moreIndex == n) {
                    nums[writingIndex] = median;
                } else {
                    nums[writingIndex] = numsCopy[moreIndex++];
                }

            }
        }

    }

    private int quickselect_median_ceil(int[] nums) {
        final var n = nums.length;
        return quickselect_median(nums, n / 2);
    }

    private int quickselect_median(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }

        final var pivotIndex = new Random().nextInt(0, nums.length);
        final var pivot = nums[pivotIndex];

        final var lows = Arrays.stream(nums).filter(num -> num < pivot).toArray();
        final var equals = Arrays.stream(nums).filter(num -> num == pivot).toArray();
        final var highs = Arrays.stream(nums).filter(num -> num > pivot).toArray();

        if (k < lows.length) {
            return quickselect_median(lows, k);
        } else if (k < lows.length + equals.length) {
            return equals[0];
        } else
            return quickselect_median(highs, k - lows.length - equals.length);
    }
}