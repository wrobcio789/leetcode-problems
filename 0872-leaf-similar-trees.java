* Definition for a binary tree node.
* public class TreeNode {
* int val;
* TreeNode left;
* TreeNode right;
* TreeNode() {}
* TreeNode(int val) { this.val = val; }
* TreeNode(int val, TreeNode left, TreeNode right) {
* this.val = val;
* this.left = left;
* this.right = right;
* }
* }
*/
class Solution {
   public boolean leafSimilar(TreeNode root1, TreeNode root2) {
       return Objects.equals(gather(root1), gather(root2));
   }

   private List<Integer> gather(TreeNode root) {
       final var list = new LinkedList<Integer>();
       gather(list, root);
       return list;
   }

   private void gather(List<Integer> list, TreeNode root) {
       if(root.left != null) {
           gather(list, root.left);
       }
       if(root.right != null) {
           gather(list, root.right);
       }

       if(root.left == null && root.right == null) {
           list.add(root.val);
       }
   }
}