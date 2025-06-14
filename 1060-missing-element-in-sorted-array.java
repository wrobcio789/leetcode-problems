class Solution {
    public int missingElement(int[] nums, int k) {
        int a = 0, b = nums.length - 1;

        while (a < b) {
            final var mid = b - (b - a)/2;

            final var numsMissing = nums[mid] - nums[0] - mid;

            if(numsMissing < k) {
                a = mid;
            } else {
                b = mid - 1;
            }
        }

        return nums[0] + a + k;
    }
}