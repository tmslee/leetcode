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
    
  public List<Integer> inorderTraversalRecursive(TreeNode root) {
      List<Integer> ans = new ArrayList();
      if(root == null) return ans;
      List<Integer> left = inorderTraversal(root.left);
      List<Integer> right = inorderTraversal(root.right);
      ans.addAll(left);
      ans.add(root.val);
      ans.addAll(right);
      return ans;
  }
  
  public TreeNode[] flatten (TreeNode root) {
      if(root == null) return new TreeNode[]{null, null};
      TreeNode[] left = flatten(root.left);
      TreeNode[] right = flatten(root.right);
      
      if(left[1] != null) {
          root.left = left[1];
          left[1].right = root;
      }
      if(right[0] != null) {
          root.right = right[0];
          right[0].left = root;
      }
      
      TreeNode head = root;
      TreeNode tail = root;
      while(head.left != null) head = head.left;
      while(tail.right != null) tail = tail.right;
      return new TreeNode[]{head, tail};
  }
  
  public List<Integer> inorderTraversalIterative(TreeNode root) {
      List<Integer> ans = new ArrayList();
      TreeNode[] headTail = flatten(root);
      TreeNode curr = headTail[0];
      while(curr != null) {
          ans.add(curr.val);
          curr = curr.right;
      }
      return ans;
  }
}