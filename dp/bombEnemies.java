class Solution {
  public int maxKilledEnemies(char[][] grid) {
      if (grid.length == 0 || grid[0].length == 0) return 0;
      
      int rows = grid.length;
      int cols = grid[0].length; 
      
      int ans = 0;
      int rowHit = 0;
      int[] colHit = new int[cols];
      
      for(int r=0 ; r<rows ; r++) {
          for(int c=0 ; c<cols ; c++){
              char currentSpace = grid[r][c];
              
              //calculate rowhit when youre first col or encountered a wall in the previous col
              if (c == 0 || grid[r][c-1] == 'W') { 
                  rowHit = 0;
                  for(int k=c ; k<cols ; k++){
                      if(grid[r][k] == 'W') break;
                      else if (grid[r][k] == 'E') rowHit++;
                  }
              }
              //calculate colhit when youre first row or encountered a wallin the previous row
              if (r == 0 || grid[r-1][c] == 'W') {
                  colHit[c] = 0;
                  for(int k=r ; k<rows ; k++){
                      if(grid[k][c] == 'W') break;
                      else if (grid[k][c] == 'E') colHit[c]++;
                  }
              }
              
              if (currentSpace == '0'){
                  ans = Math.max(ans, rowHit + colHit[c]);
              }
          }
      }
      
      return ans;
  }
}