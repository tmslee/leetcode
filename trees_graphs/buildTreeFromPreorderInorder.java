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
  int preorderIdx;
  Map<Integer, Integer> indexMap = new HashMap();
  
  public TreeNode buildTreeHelper (int[] preorder, int[] inorder, int start, int end) {
      if(start > end) return null;
      
      int currVal = preorder[preorderIdx];
      preorderIdx++;
      int currIdx = indexMap.get(currVal);
      
      TreeNode currNode = new TreeNode(currVal);
      currNode.left = buildTreeHelper(preorder, inorder, start, currIdx-1);
      currNode.right = buildTreeHelper(preorder, inorder, currIdx+1, end);
      
      return currNode;
  }
  
  public TreeNode buildTree(int[] preorder, int[] inorder) {
      preorderIdx = 0;
      
      for(int i=0 ; i<inorder.length ; i++){
          indexMap.put(inorder[i], i);
      }
      
      return buildTreeHelper(preorder, inorder, 0, preorder.length-1);
  }
}