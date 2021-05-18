/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    
  public Node[] treeToDoublyHelper(Node root) {
      if(root == null) return new Node[]{null, null};

      Node[] leftHT = treeToDoublyHelper(root.left);
      Node[] rightHT = treeToDoublyHelper(root.right);
      
      root.left = leftHT[1];
      root.right = rightHT[0];
      if(leftHT[1] != null) leftHT[1].right = root;
      if(rightHT[0] != null) rightHT[0].left = root;
      
      Node head = root;
      Node tail = root;
      while(head.left != null) head = head.left;
      while(tail.right != null) tail = tail.right;
          
      return new Node[]{head, tail};
  }
  
  
  public Node treeToDoublyList(Node root) {
      Node[] ansHT = treeToDoublyHelper(root);
      if(ansHT[0] != null && ansHT[1] != null) {
          ansHT[0].left = ansHT[1];
          ansHT[1].right = ansHT[0];
      }
      return ansHT[0];
  }
}