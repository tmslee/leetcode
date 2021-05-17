/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
  Map<Node, Node> seen = new HashMap();
  
  public Node cloneGraph(Node node) {
      if(node == null) return node;
      
      if(seen.containsKey(node)) return seen.get(node);
      
      Node clone = new Node(node.val, new ArrayList<Node>());
      seen.put(node, clone);
      
      for(Node neighbor : node.neighbors) {
          clone.neighbors.add(cloneGraph(neighbor));
      }
      
      return clone;
  }
}