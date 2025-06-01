class Solution {
    public int maxArea(int[] height) {

        int i = 0, j = height.length - 1;
        int result = 0;

        while (i < j) {
            final var distance = j - i;

            if (height[i] < height[j]) {
                result = Math.max(result, distance * height[i]);
                i++;
            } else {
                result = Math.max(result, distance * height[j]);
                j--;
            }

        }

        return result;
    }
}