class Solution {
  int[][] grid = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
  
  public int[] getCoord (int n){ //get coordinate based on number 
      int r = (n-1)/3;
      int c = (n-1)%3;
      return new int[]{r,c};
  }
  
  public List<Integer> getCrossing (int start, int end) {
      List<Integer> ans = new ArrayList();
      
      int[] startC = getCoord(start);
      int[] endC = getCoord(end);
      
      int rDiff = Math.abs(startC[0]-endC[0]);
      int cDiff = Math.abs(startC[1]-endC[1]);
      
      int rTot = startC[0] + endC[0];
      int cTot = startC[1] + endC[1];
      int totalDiff = rDiff+cDiff;
  
      if(totalDiff == 1 || (rDiff == 1 && cDiff == 1)) return ans; //adjacent; doesnt cross anything
      if(totalDiff == 2) { //distance 2 : on same col or row - crosses number located @ midpoint
          ans.add(grid[rTot/2][cTot/2]);
      } else if (totalDiff == 4){ //distance 4 : diagonal - only cross 5
          ans.add(5);
      } else { //distance 3 : cross 5 and another number that is located @ [endR+startR-1][endC+startC-1]
          ans.add(5);
          ans.add(grid[rTot-1][cTot-1]);
      }
      return ans;
  }
  
  public boolean destinationValid(int start, int end, Set<Integer> seen) {
      List<Integer> crossings = getCrossing(start, end);
      for(Integer crossing : crossings) {
          if(!seen.contains(crossing)) return false;
      }
      return true;
  }
  
  public List<Integer> getValidNext (List<Integer> currSequence) {
      List<Integer> res = new ArrayList();
      
      Set<Integer> seenSet = new HashSet(currSequence);
      int lastNum = currSequence.get(currSequence.size()-1);
      for (int i=1 ; i<10 ; i++) {
          if(!seenSet.contains(i) && destinationValid(lastNum, i, seenSet)) {
              res.add(i);
          }
      }
      return res;
  }
  
  public List<List<Integer>> validPatterns (int n, int[] mem) {
      List<List<Integer>> ans = new ArrayList();
      
      if(n==1) {
          for(int i=1 ; i<10 ; i++){
              List<Integer> newList = new ArrayList();
              newList.add(i);
              ans.add(newList);
          }
          mem[n-1] = ans.size();
          return ans;
      }
      
      List<List<Integer>> prevList = validPatterns(n-1, mem);
      for (List<Integer> sequence : prevList) {
          List<Integer> validNextNum = getValidNext(sequence);
          for(Integer num : validNextNum) {
              List<Integer> seqCopy = new ArrayList(sequence);
              seqCopy.add(num);
              ans.add(seqCopy);
          }
      }
      mem[n-1] = ans.size();
      return ans;
  } 
  
  public int numberOfPatterns(int m, int n) {
      int[] numPatterns = new int[n];
      validPatterns(n, numPatterns);
      int ans = 0;
      for(int i=m-1 ; i<n ; i++){
          System.out.println(numPatterns[i]);
          ans+= numPatterns[i];
      }
      return ans;
  }
}