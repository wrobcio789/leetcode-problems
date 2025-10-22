class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;

        int prefixSum = nums[0];
        int minSoFar = Math.min(0, prefixSum);
        int result = nums[0];
        for (int i = 1; i < n; i++) {
            int newPrefixSum = prefixSum + nums[i];
            result = Math.max(result, newPrefixSum - minSoFar);
            prefixSum = newPrefixSum;
            minSoFar = Math.min(minSoFar, newPrefixSum);
        }

        return result;
    }
}