class Solution {
    public int findUnsortedSubarray(int[] nums) {
        final var n = nums.length;

        int leftIndex = -1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                leftIndex = i;
                break;
            }
        }

        if (leftIndex == -1) {
            return 0;
        }

        int rightIndex = n - 1;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                rightIndex = i;
                break;
            }
        }

        final int rightBound = rightIndex;
        for (int i = leftIndex; i <= rightBound; i++) {
            while (leftIndex >= 0 && nums[i] < nums[leftIndex]) {
                leftIndex--;
            }

            while (rightIndex < n && nums[i] > nums[rightIndex]) {
                rightIndex++;
            }
        }

        return rightIndex - leftIndex - 1;
    }
}