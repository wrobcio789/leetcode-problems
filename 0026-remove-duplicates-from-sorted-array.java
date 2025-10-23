class Solution {
    public int removeDuplicates(int[] nums) {
        int offset = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i - offset] = nums[i];
            int baseI = i;
            while (i + 1 < nums.length && nums[baseI] == nums[i + 1]) {
                i++;
                offset++;
            }
            result++;
        }

        return result;
    }
}