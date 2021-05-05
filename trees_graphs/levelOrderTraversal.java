class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> ans = new ArrayList<>();
      if (root == null) return ans;
      
      LinkedList<TreeNode> queue = new LinkedList<>();
      queue.addLast(root);
      while(!queue.isEmpty()) {
          int currSize = queue.size();
          List<Integer> currList = new ArrayList<>();
          for(int i=0; i<currSize ; i++) {
              TreeNode node = queue.removeFirst();
              currList.add(node.val);
              if(node.left != null) queue.addLast(node.left);
              if(node.right != null) queue.addLast(node.right);
          }
          ans.add(currList);
      }
      return ans;
  }
}