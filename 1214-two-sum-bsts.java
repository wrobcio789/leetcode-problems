import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        final var numsInFirst = new HashSet<Integer>();
        collect(root1, numsInFirst);
        return find(root2, numsInFirst, target);
    }

    private void collect(TreeNode root, Set<Integer> bag) {
        if (root == null) {
            return;
        }

        bag.add(root.val);
        collect(root.left, bag);
        collect(root.right, bag);
    }

    private boolean find(TreeNode root, Set<Integer> existingNums, int target) {
        if (root == null) {
            return false;
        }

        return existingNums.contains(target - root.val)
                || find(root.left, existingNums, target)
                || find(root.right, existingNums, target);
    }
}