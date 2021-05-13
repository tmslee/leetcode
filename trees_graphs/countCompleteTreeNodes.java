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
  public int countNodes(TreeNode root) {
      if(root == null) return 0;
      if(root.left == null && root.right == null) return 1;
      int left = countNodes(root.left);
      int right = countNodes(root.right);
      int ans = 1;
      if(left > 0) ans += left;
      if(right > 0) ans += right;
      return ans;
  }
}