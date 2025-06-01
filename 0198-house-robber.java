class Solution {
    public int rob(int[] nums) {
        final var n = nums.length;

        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int dp0 = nums[0];
        int dp1 = Math.max(nums[0], nums[1]);
        int dp2 = 0;

        for (int i = 2; i < n; i++) {
            dp2 = Math.max(dp0 + nums[i], dp1);
            dp0 = dp1;
            dp1 = dp2;
        }
        return dp2;
    }
}