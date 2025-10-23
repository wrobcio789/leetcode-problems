class Solution {
    public int minimizeArrayValue(int[] nums) {
        int result = 0;
        long cumSum = 0L;
        for (int i = 0; i < nums.length; i++) {
            cumSum += nums[i];
            result = (int) Math.max(result, Math.ceilDiv(cumSum, i + 1L));
        }

        return result;
    }
}