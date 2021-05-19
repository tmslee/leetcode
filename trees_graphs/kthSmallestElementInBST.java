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
  //use inorder traversal using stack and count number of nodes processed
  public int kthSmallest(TreeNode root, int k) {
      Stack<TreeNode> stack = new Stack();
      int count = 0;
      TreeNode curr = root;
      while(curr!=null || !stack.isEmpty()){
          while(curr != null) {
              stack.push(curr);
              curr = curr.left;
          }
          curr = stack.pop();
          count++;
          if(count == k) return curr.val;
          curr = curr.right;
      }
      return -1;
  }
}