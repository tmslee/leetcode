class Solution {
  public boolean isMirror (TreeNode t1, TreeNode t2) {
      if(t1 == null && t2 == null) return true;
      if(t1 != null && t2 != null && t1.val == t2.val) {
          return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
      } 
      return false;
  }
  
  public boolean isSymmetric(TreeNode root) {
      return isMirror (root, root);
  }
}