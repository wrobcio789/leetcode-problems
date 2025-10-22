class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int n = nums1.length, m = nums2.length;

        if ((n + m) % 2 == 1) {
            final int searchedNum = (n + m) / 2;
            return findKthNum(nums1, nums2, searchedNum + 1, 0, 0);
        } else {
            final int searchedNum = (n + m) / 2;
            return (
                    findKthNum(nums1, nums2, searchedNum, 0, 0)
                            + findKthNum(nums1, nums2, searchedNum + 1, 0, 0)
            ) / 2.0;
        }
    }

    private int findKthNum(int[] nums1, int[] nums2, int k, int offset1, int offset2) {
        if (k == 1) {
            return Math.min(getOrDefault(nums1, offset1), getOrDefault(nums2, offset2));
        }

        int lengthLeft1 = nums1.length - offset1;
        int lengthLeft2 = nums2.length - offset2;

        if (lengthLeft1 == 0) {
            return nums2[offset2 + k - 1];
        }

        if (lengthLeft2 == 0) {
            return nums1[offset1 + k - 1];
        }

        int takingLeft, takingRight;
        if (lengthLeft1 < lengthLeft2) {
            takingLeft = Math.min(lengthLeft1, k / 2);
            takingRight = k - takingLeft;
        } else {
            takingRight = Math.min(lengthLeft2, k / 2);
            takingLeft = k - takingRight;
        }

        if (nums1[offset1 + takingLeft - 1] < nums2[offset2 + takingRight - 1]) {
            return findKthNum(nums1, nums2, k - takingLeft, offset1 + takingLeft, offset2);
        } else {
            return findKthNum(nums1, nums2, k - takingRight, offset1, offset2 + takingRight);
        }
    }

    private int getOrDefault(int[] nums, int index) {
        if (index < nums.length) {
            return nums[index];
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}));
    }
}