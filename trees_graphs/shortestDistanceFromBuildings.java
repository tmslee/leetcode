class Solution {
  Map<Pair<Integer, Integer>, int[][]> distMemMap;
  int[][] grid;
  int[] rOffset = {0, 0, 1, -1};
  int[] cOffset = {1, -1, 0, 0};
  int rows;
  int cols;
  int MAXVAL = Integer.MAX_VALUE;
  
  public boolean isValid(int r, int c) {
      return !(r<0 || r>=rows || c<0 || c>=cols);
  }
  
  public void fillDistMem (Pair<Integer, Integer> building) {
      int[][] mem = distMemMap.get(building);
      for(int[] row : mem) Arrays.fill(row, MAXVAL);
      
      Queue<Pair<Integer,Integer>> q = new LinkedList();
      Set<Pair<Integer, Integer>> seen = new HashSet();
      
      int dist = 0;
      q.add(building);
      
      while(!q.isEmpty()){
          int currSize = q.size();
          for(int i=0 ; i<currSize ; i++) {
              Pair<Integer, Integer> curr = q.poll();
              seen.add(curr);
              int r = curr.getKey();
              int c = curr.getValue();
              if(r != building.getKey() || c != building.getValue()) mem[r][c] = dist;
              for(int j=0 ; j<4 ; j++){
                  int newR = r + rOffset[j];
                  int newC = c + cOffset[j];
                  if(isValid(newR, newC) && !seen.contains(new Pair(newR, newC)) && grid[newR][newC] == 0) {
                      q.add(new Pair(newR, newC));
                  }
              }
          }
          dist++;
      }
  }
  
//     public void printMem(int[][] mem) {
//         for(int i=0 ; i<mem.length ; i++) {
//             String str = "";
//             for(int j=0 ; j<mem[0].length ; j++) {
//                 str += Integer.toString(mem[i][j]) + " ";
//             }
//             System.out.println(str);
//         }
//     }
  
  public int shortestDistance(int[][] grid) {
      this.grid = grid;
      rows = grid.length;
      cols = grid[0].length;
      distMemMap = new HashMap();
      
      int ans = Integer.MAX_VALUE;
      
      for(int r=0 ; r<rows ; r++) {
          for(int c=0 ; c<cols ; c++){
              if(grid[r][c] == 1) {
                  Pair<Integer, Integer> building = new Pair(r,c);
                  distMemMap.put(building, new int[rows][cols]);
                  fillDistMem(building);
              }
          }
      }
              
      for(int r=0 ; r<rows ; r++) {
          for(int c=0 ; c<cols ; c++){
              if(grid[r][c] == 0) {
                  int currRes = 0;
                  for(Pair<Integer, Integer> building : distMemMap.keySet()){
                      int dist = distMemMap.get(building)[r][c];
                      if(dist == Integer.MAX_VALUE) {
                          currRes = Integer.MAX_VALUE;
                          break;
                      } else {
                          currRes += dist;
                      }
                  }
                  ans = Math.min(ans, currRes);
              }
          }
      }
      
      // for(Pair<Integer, Integer> building : distMemMap.keySet()) printMem(distMemMap.get(building));
      
      return ans == MAXVAL ? -1 : ans;
  }
}