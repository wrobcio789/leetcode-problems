class Solution {
    public int search(int[] nums, int target) {
        final var offset = findOffset(nums);
        return findTarget(nums, target, offset);
    }

    private int findTarget(int[] nums, int target, int offset) {
        final var n = nums.length;

        int min = 0, max = n - 1;

        while (min <= max) {
            final var mid = (max + min) / 2;

            final var number = at(nums, mid, offset);
            if (number < target) {
                min = mid + 1;
            } else if (number > target) {
                max = mid - 1;
            } else {
                return (mid + offset) % n;
            }
        }

        return -1;
    }

    private int findOffset(int[] nums) {
        final var n = nums.length;

        if (nums[0] <= nums[n - 1]) {
            return 0;
        }

        int min = 0, max = n - 1;
        while (min <= max) {
            final var mid = (max + min) / 2;

            if (nums[mid] > nums[mid + 1]) {
                return mid + 1;
            } else {
                if (nums[mid] >= nums[min]) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }
            }
        }

        return 0;
    }

    private int at(int[] arr, int index, int offset) {
        final var n = arr.length;
        index = (index + offset) % n;
        return arr[index];
    }
}