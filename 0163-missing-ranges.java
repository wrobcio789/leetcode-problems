import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        final var result = new ArrayList<List<Integer>>();

        int previous = lower - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > previous + 1) {
                result.add(List.of(previous + 1, nums[i] - 1));
            }

            previous = nums[i];
        }

        if (upper > previous) {
            result.add(List.of(previous + 1, upper));
        }

        return result;
    }
}