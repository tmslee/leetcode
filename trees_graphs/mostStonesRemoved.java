class Solution {
  //count number of independent stone groups and return sontes - islands
  
  Map<Integer, List<Pair<Integer, Integer>>> rowMap = new HashMap();
  Map<Integer, List<Pair<Integer, Integer>>> colMap = new HashMap();
  Set<Pair<Integer, Integer>> visited= new HashSet();
  
  public void dfs(Pair<Integer, Integer> stone) {
      visited.add(stone);
      if(rowMap.get(stone.getKey()) != null){
          for (Pair<Integer, Integer> childStone : rowMap.get(stone.getKey())) {
              if(!visited.contains(childStone)) dfs(childStone);
          }
      }
      if(colMap.get(stone.getValue()) != null){
          for (Pair<Integer, Integer> childStone : colMap.get(stone.getValue())) {
              if(!visited.contains(childStone)) dfs(childStone);
          }
      }
  }
  
  public int removeStones(int[][] stones) {
      for (int i=0 ; i<stones.length ; i++) {
          int r = stones[i][0];
          int c = stones[i][1];
          Pair<Integer, Integer> stone = new Pair(r,c);
          List<Pair<Integer,Integer>> rowStones = rowMap.getOrDefault(r, new ArrayList()); 
          List<Pair<Integer,Integer>> colStones = colMap.getOrDefault(c, new ArrayList()); 
          rowStones.add(stone);
          colStones.add(stone);
          rowMap.put(r, rowStones);
          colMap.put(c, colStones);
      }
      
      int islands = 0;
      for(int i=0 ; i<stones.length ; i++) {
          Pair<Integer, Integer> stone = new Pair(stones[i][0], stones[i][1]);
          if(!visited.contains(stone)) {
              dfs(stone);
              islands++;
          }
      }
      return stones.length - islands;
  }
}