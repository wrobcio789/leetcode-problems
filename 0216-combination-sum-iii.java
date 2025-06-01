import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        final var result = new ArrayList<List<Integer>>();
        combinationSum(result, new LinkedList<>(), 0, k, n);
        return result;
    }

    private void combinationSum(List<List<Integer>> bag, LinkedList<Integer> accu, int sum, int k, int n) {
        if (accu.size() == k && sum == n) {
            bag.add(new ArrayList<>(accu));
            return;
        } else if (accu.size() >= k || sum > n) {
            return;
        }

        int begin = accu.isEmpty() ? 1 : accu.getLast() + 1;
        for (int i = begin; i <= 9; i++) {
            accu.add(i);
            combinationSum(bag, accu, sum + i, k, n);
            accu.removeLast();
        }
    }
}