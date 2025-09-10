class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        final var lastIndex = findLastIndex(nums, target);
        final var firstIndex = findFirstIndex(nums, target);

        if (lastIndex == -1) {
            return false;
        }

        return 2 * (lastIndex - firstIndex + 1) > nums.length;
    }

    int findFirstIndex(int[] nums, int target) {
        int min = 0, max = nums.length - 1;

        int first = -1;
        while (min <= max) {
            int mid = (max + min) / 2;

            if (nums[mid] >= target) {
                first = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }

            if (nums[mid] == target) {
                first = mid;
            }
        }

        return first;
    }

    int findLastIndex(int[] nums, int target) {
        int min = 0, max = nums.length - 1;

        int last = -1;
        while (min <= max) {
            int mid = (max + min) / 2;

            if (nums[mid] <= target) {
                min = mid + 1;
            } else if (nums[mid] > target) {
                max = mid - 1;
            }

            if (nums[mid] == target) {
                last = mid;
            }
        }

        return last;
    }
}