class Solution{
  Map<String, List<String>> childrenMap = new HashMap();
  List<String> ans = new ArrayList();
  int numEdges;
  
  public void populateChildrenMap (List<List<String>> tickets) {
       for(List<String> ticket : tickets) {
          String from = ticket.get(0);
          String dest = ticket.get(1);
          List<String> children = childrenMap.getOrDefault(from, new ArrayList<String>());
          children.add(dest);
          childrenMap.put(from, children);
      }
      
  }
  
  public boolean backtrack(List<String> curr) {
      // System.out.println(curr);
      if(curr.size() -1 == numEdges) {
          ans.addAll(curr);
          return true;
      }
      
      String currFlight = curr.get(curr.size()-1);
      List<String> children = childrenMap.get(currFlight);
      
      System.out.println(currFlight);
      System.out.println(children);
      
      
      if(children != null){
          int numChildren = children.size();
          PriorityQueue<String> childrenCopy = new PriorityQueue(children);
          for(int i=0 ; i<numChildren ; i++){
              String child = childrenCopy.poll();
              children.remove(child);
              curr.add(child);
              
              if(backtrack(curr)) return true;   
              
              children.add(child);
              curr.remove(curr.size()-1);
          }
      }
      return false;
  }
  
  public List<String> findItinerary(List<List<String>> tickets) {
      numEdges = tickets.size();
      populateChildrenMap(tickets);
      List<String> curr = new ArrayList();
      curr.add("JFK");
      backtrack(curr);
      return ans;
  }
}


class Solution {
  //Eulerian path
  public List<String> findItinerary(List<List<String>> tickets) {
      for (List<String> ticket : tickets)
          targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
      visit("JFK");
      return route;
  }
  

  Map<String, PriorityQueue<String>> targets = new HashMap<>();
  List<String> route = new LinkedList();

  void visit(String airport) {
      while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
          visit(targets.get(airport).poll());
      route.add(0, airport);
  }
}

