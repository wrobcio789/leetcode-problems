class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        for(int i = 0, product = 1; i<result.length; i++) {
            result[i] = product;
            product *= nums[i];
        }

        for(int i = result.length - 1, product = 1; i>= 0; i--) {
            result[i] *= product;
            product *= nums[i];
        }

        return result;

    }
}