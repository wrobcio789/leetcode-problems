class Solution {
    public int findPeakElement(int[] nums) {
        final var n = nums.length;
        if (n == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }

        int a = 0, b = nums.length - 1;

        while (a <= b) {

            int mid = (a + b) / 2;

            if (nums[mid] < nums[mid + 1]) {
                a = mid + 1;
            } else if (nums[mid] < nums[mid - 1]) {
                b = mid - 1;
            } else {
                return mid;
            }
        }

        return a;
    }
}