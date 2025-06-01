class Solution {
    public void moveZeroes(int[] nums) {
        int  writingIndex = 0;

        for(int i = 0; i<nums.length; i++) {
            nums[writingIndex] = nums[i];
            if(nums[i] != 0) {
                writingIndex++;
            }
        }

        while(writingIndex < nums.length) {
            nums[writingIndex++] = 0;
        }

    }
}