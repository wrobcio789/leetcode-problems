import java.util.HashMap;

class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        final var num2ToIndexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums2.length; i++) {
            num2ToIndexMap.put(nums2[i], i);
        }

        int n = nums1.length;
        final var result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = num2ToIndexMap.get(nums1[i]);
        }

        return result;
    }
}