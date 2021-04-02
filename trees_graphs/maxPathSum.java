package trees_graphs;

public class maxPathSum {
  public int solution(TreeNode root) {
    Pair<Integer, Integer> res = new Pair(null, null);
    Pair<Integer, Integer> ans = getMaxPathSum(root, res);
    return ans.getValue();
}

public Pair<Integer, Integer> getMaxPathSum(TreeNode root, Pair<Integer, Integer> res) {
    
    Integer globalMax = res.getValue(); 
    Integer connectMax = res.getKey(); 
    
    if(root.left == null && root.right == null) {
        globalMax = globalMax != null ? Math.max(globalMax, root.val) : root.val;
        connectMax = globalMax;
        return new Pair(connectMax, globalMax);
    }
    
    int currVal = root.val;
    
    if(root.left != null && root.right != null) {
        Pair<Integer, Integer> leftP = getMaxPathSum(root.left, res);
        Pair<Integer, Integer> rightP = getMaxPathSum(root.right, res);
        
        int leftMax = leftP.getKey();
        int leftGlobalMax = leftP.getValue();
        int rightMax = rightP.getKey();
        int rightGlobalMax = rightP.getValue();
        
        connectMax = Math.max(currVal, Math.max(leftMax+currVal, rightMax+currVal));
        globalMax = Math.max(
            Math.max(leftGlobalMax, rightGlobalMax),
            Math.max(leftMax+currVal+rightMax, connectMax)    
        ); 
        
    } else if (root.left == null){
        Pair<Integer, Integer> rightP = getMaxPathSum(root.right, res);
        int rightMax = rightP.getKey();
        int rightGlobalMax = rightP.getValue();
        
        connectMax = Math.max(currVal, rightMax+currVal);
        globalMax = Math.max(rightGlobalMax, connectMax);
    } else {
        Pair<Integer, Integer> leftP = getMaxPathSum(root.left, res);
        int leftMax = leftP.getKey();
        int leftGlobalMax = leftP.getValue();
        
        connectMax = Math.max(currVal, leftMax+currVal);
        globalMax = Math.max(leftGlobalMax, connectMax);
    }   
    
    return new Pair(connectMax, globalMax);
}
}
