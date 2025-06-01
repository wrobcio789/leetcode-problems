import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MIN_NUM = -1000;
    private static final int MAX_NUM = 1000;
    private static final int RANGE = MAX_NUM - MIN_NUM + 1;
    private static final int OFFSET = -MIN_NUM;

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        final var occurences = new byte[RANGE];
        fill(occurences, nums1, (byte) 1);
        fill(occurences, nums2, (byte) 2);

        final ArrayList<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        for (int i = 0; i < RANGE; i++) {
            if(occurences[i] == 3) {
                continue;
            }

            if (occurences[i] == 1) {
                list1.add(i - OFFSET);
            }
            if (occurences[i] == 2) {
                list2.add(i - OFFSET);
            }
        }

        return List.of(list1, list2);
    }

    private void fill(byte[] occurences, int[] nums, byte value) {
        for (final var num : nums) {
            occurences[num + OFFSET] |= value;
        }
    }
}