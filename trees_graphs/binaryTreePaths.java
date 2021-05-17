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
  List<String> ans = new ArrayList();
  
  public void getPaths (TreeNode node, String curr){
      if(node.left == null && node.right == null) ans.add(curr);
      if(node.left != null) {
          String val = Integer.toString(node.left.val);
          getPaths(node.left, curr+"->" + val);
      }
      if(node.right != null) {
          String val = Integer.toString(node.right.val);
          getPaths(node.right, curr+"->" + val);
      }
  }
  
  public List<String> binaryTreePaths(TreeNode root) {
      if(root == null) return ans;
      String val = Integer.toString(root.val);
      getPaths(root, val);
      return ans;
  }
}