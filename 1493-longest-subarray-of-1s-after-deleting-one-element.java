class Solution {
    public int longestSubarray(int[] nums) {
        final var size = nums.length;

        int result = 0;
        boolean containsZero = false;
        for (int i = 0, j = 0; i < size; i++) {
            j = Math.max(i, j);

            while (j < size && (nums[j] == 1 || !containsZero)) {
                containsZero |= nums[j] == 0;
                j++;
            }

            result = Math.max(result, j - i - 1);

            if (i != j && nums[i] == 0) {
                containsZero = false;
            }
        }

        return result;
    }
}