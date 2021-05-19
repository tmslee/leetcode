/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  int ans = 0;
  
  public void sumHelper(TreeNode root, boolean isLeft){
      if(root.left == null && root.right == null && isLeft) ans+= root.val;
      if(root.left != null) {
          sumHelper(root.left, true);
      }
      if(root.right != null){
          sumHelper(root.right, false);
      }
  }
  
  
  public int sumOfLeftLeaves(TreeNode root) {
      if(root == null) return ans;
      sumHelper(root, false);
      return ans;
  }
}