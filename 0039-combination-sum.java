import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        final var result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, 0, new LinkedList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int index, int cumSum, LinkedList<Integer> accu, List<List<Integer>> bag) {
        if (cumSum > target) {
            return;
        }

        if (cumSum == target) {
            bag.add(new ArrayList<>(accu));
        }

        if (index >= candidates.length) {
            return;
        }

        final int currentNum = candidates[index];
        if (currentNum + cumSum > target) {
            return;
        }

        backtrack(candidates, target, index + 1, cumSum, accu, bag);

        int timesAdded = 0;
        while (cumSum + currentNum <= target) {
            accu.add(currentNum);
            cumSum += currentNum;
            timesAdded++;

            backtrack(candidates, target, index + 1, cumSum, accu, bag);
        }

        while (timesAdded-- > 0) {
            accu.removeLast();
        }
    }
}