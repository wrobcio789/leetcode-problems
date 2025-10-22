import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {

        int result = 0;
        final var prefixSumCount = new HashMap<Integer, Integer>();
        prefixSumCount.put(0, 1);
        for (int i = 0, prefixSum = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            result += prefixSumCount.getOrDefault(prefixSum - k, 0);
            prefixSumCount.merge(prefixSum, 1, Integer::sum);
        }

        return result;
    }
}