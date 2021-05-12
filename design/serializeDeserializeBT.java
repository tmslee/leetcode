/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
      if(root == null) return "null";
      
      StringBuilder ans = new StringBuilder();
      String left = serialize(root.left);
      String right = serialize(root.right);
      ans.append(Integer.toString(root.val));
      ans.append(",");
      ans.append(left);
      ans.append(",");
      ans.append(right);
      
      return ans.toString();
  }
  
  public TreeNode deserializeHelper(List<String> nodes) {
      String currValStr = nodes.get(0);
      nodes.remove(0);
      
      if(currValStr.equals("null")) return null;
      else {
          TreeNode currNode = new TreeNode(Integer.parseInt(currValStr));
          currNode.left = deserializeHelper(nodes);
          currNode.right = deserializeHelper(nodes);
          return currNode;
      }
  }
  
  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
      List<String> nodes = new ArrayList<String>(Arrays.asList(data.split(",")));
      return deserializeHelper(nodes);
  }
}