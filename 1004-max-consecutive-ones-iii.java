class Solution {
    public int longestOnes(int[] nums, int k) {

        int result = 0;
        final var size = nums.length;

        for (int i = 0, j = 0, flips = 0; i < size; i++) {
            j = Math.max(i, j);

            while (j < size && (nums[j] == 1 || flips < k)) {
                flips += (nums[j] ^ 1);
                j++;
            }

            if (i != j) {
                flips -= (nums[i] ^ 1);

            }
            result = Math.max(result, j - i);
        }

        return result;
    }
}