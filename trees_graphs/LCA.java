class Solution {
  TreeNode ans = null;
  
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      LCAHelper(root, p, q);
      return ans;
  }
  
  public boolean LCAHelper(TreeNode curr, TreeNode p, TreeNode q) {
      if(curr == null) return false;
      
      int left = LCAHelper(curr.left, p, q) ? 1 : 0;
      int right = LCAHelper(curr.right, p, q) ? 1 : 0;
      int mid = (curr==p || curr==q) ? 1 : 0;
      
      if (left + right + mid >= 2) ans = curr;
      return (mid + left + right > 0);
  }
}