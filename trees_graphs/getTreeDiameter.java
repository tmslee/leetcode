class Solution {
  int diameter = 0;
  
  public int getLongestPath(TreeNode root) {
      if(root == null) return 0;
      
      int leftPath = getLongestPath(root.left);
      int rightPath = getLongestPath(root.right);
      
      diameter = Math.max(diameter, leftPath + rightPath);
      
      return Math.max(leftPath+1, rightPath+1);
  }
  
  public int diameterOfBinaryTree(TreeNode root) {
      getLongestPath(root);
      return diameter;
  }
}