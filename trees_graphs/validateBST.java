package trees_graphs;

public class validateBST {
  public boolean solution(TreeNode root) {
    if (root == null) return true;
    return isValidHelper(root, null, null);
  }

  public boolean isValidHelper(TreeNode root, Integer min, Integer max) {        
    int currVal = root.val;
    if((min != null && currVal <= min) || (max != null && currVal >= max)) return false;
    
    boolean leftCheck = root.left == null ? true : false;
    boolean rightCheck = root.right == null ? true : false;
    
    if (root.left != null){
        leftCheck = isValidHelper(root.left, min, max != null ? Math.min(max, currVal) : currVal);
    }
    if (!leftCheck) return false;
    if (root.right != null){
        rightCheck = isValidHelper(root.right, min != null ? Math.max(min, currVal) : currVal, max);
    }
    
    return rightCheck;
}
}
