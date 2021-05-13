class Solution {
  Map<String, GraphNode> nodeMap = new HashMap();
  
  class GraphNode {
      String str;
      Map<String, GraphNode> connections;
      Map<String, Double> vals;
      
      public GraphNode(String str) {
          this.str = str;
          connections = new HashMap();
          vals = new HashMap();
      }
  }
  
  public double getVal(String numerator, String denominator) {
      GraphNode numNode = nodeMap.get(numerator);
      GraphNode denomNode = nodeMap.get(denominator);
      
      if(numNode == null || denomNode == null) return -1;
      if(numNode == denomNode) return 1;
      
      Map<String, Double> valMap = new HashMap();
      valMap.put(numerator, 1.0);
      Set<String> visited = new HashSet();
      
      Queue<GraphNode> q = new LinkedList();
      q.add(numNode);
      while (!q.isEmpty()){
          GraphNode currNode = q.poll();
          visited.add(currNode.str);
          double currVal = valMap.get(currNode.str);
          
          for(String var : currNode.connections.keySet()){
              if(var.equals(denominator)){
                  return currVal*(currNode.vals.get(var));
              }
              if(!visited.contains(var)){
                  q.add(nodeMap.get(var));  
                  valMap.put(var, currVal*currNode.vals.get(var));
              }
          }       
      }
      return -1;
  }
  
  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
      double[] ans = new double[queries.size()];
      for (int i=0 ; i<equations.size() ; i++) {
          String num = equations.get(i).get(0);
          String denom = equations.get(i).get(1);
          
          double val = values[i];
              
          GraphNode numNode = nodeMap.getOrDefault(num, new GraphNode(num));
          GraphNode denomNode = nodeMap.getOrDefault(denom, new GraphNode(denom));
          
          numNode.connections.put(denom, denomNode);
          numNode.vals.put(denom, val);
          denomNode.connections.put(num, numNode);
          denomNode.vals.put(num, 1/val);
          
          nodeMap.put(num, numNode);
          nodeMap.put(denom, denomNode);
      }
      
      for(int i=0 ; i<queries.size() ; i++) {
          ans[i] = getVal(queries.get(i).get(0), queries.get(i).get(1));
      }
      return ans;
  }
}