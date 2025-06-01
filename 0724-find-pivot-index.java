import java.util.Arrays;

class Solution {
    public int pivotIndex(int[] nums) {
        final var totalSum = Arrays.stream(nums).sum();

        for(int i = 0, result = 0; i < nums.length; i++) {
            if (2 * result == totalSum - nums[i]) {
                return i;
            }

            result += nums[i];
        }

        return -1;
    }
}