class Solution {
    public double findMaxAverage(int[] nums, int k) {

        int sum = 0;
        for(int i = 0; i < k; i++) {
            sum += nums[i];
        }

        var maxSum = sum;
        for (int i = 0; i + k < nums.length; i++) {
            sum += (nums[i + k] - nums[i]);
            maxSum = Math.max(sum, maxSum);
        }

        return (double) maxSum /k;

    }
}