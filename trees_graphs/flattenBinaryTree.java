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
    
  public TreeNode flattenHelper(TreeNode node){
      if(node == null) return null;
      if(node.left == null && node.right == null) return node;
      
      TreeNode rightTail= flattenHelper(node.right);
      TreeNode leftTail = flattenHelper(node.left);
      
      if(leftTail != null){
          leftTail.right = node.right;
          node.right = node.left;
          node.left = null;
      }
      
      return rightTail == null ? leftTail : rightTail;
  }
  
  public void flatten(TreeNode root) {
      flattenHelper(root);
      return;
  }
}