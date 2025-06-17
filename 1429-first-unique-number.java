import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FirstUnique {

    private final List<Integer> nums = new ArrayList<>();
    private final Map<Integer, Integer> countMap = new HashMap<>();
    int firstUniqueIndex = 0;

    public FirstUnique(int[] nums) {
        for(int num : nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
        if(firstUniqueIndex == nums.size()) {
            return -1;
        }

        return nums.get(firstUniqueIndex);
    }

    public void add(int value) {
        nums.add(value);
        countMap.merge(value, 1, Integer::sum);

        while (firstUniqueIndex < nums.size() && countMap.get(nums.get(firstUniqueIndex)) != 1) {
            firstUniqueIndex++;
        }
    }
}
