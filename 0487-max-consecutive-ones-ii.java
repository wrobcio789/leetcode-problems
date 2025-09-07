class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        final var n = nums.length;

        int result = 0;
        for (int i = 0, j = 0, zeroesSoFar = 0; i < n; i++) {
            j = Math.max(i, j);

            while (j < n && (nums[j] == 1 || zeroesSoFar < 1)) {
                zeroesSoFar += (nums[j] == 0 ? 1 : 0);
                j++;
            }

            int distance = j - i;
            result = Math.max(result, distance);

            zeroesSoFar -= (nums[i] == 0 ? 1 : 0);
        }

        return result;
    }
}