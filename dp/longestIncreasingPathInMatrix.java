class Solution {
  // keep dp matrix that saves max increasing path startin from that coord
  // use dp mem and evaluate entire grid extract max val
  public boolean isValid(int r, int c, int rows, int cols){
      if(r<0 || r>=rows || c<0 || c>=cols) return false;
      return true;
  }
  
  public int getMaxPathFrom(int r, int c, int[][] matrix, int[][] maxPathFrom) {
      if(maxPathFrom[r][c] > 0) return maxPathFrom[r][c];
      int ans = 1;
      int currVal = matrix[r][c];
      int[] rOffset = {0, 0, 1, -1};
      int[] cOffset = {1, -1, 0, 0};
      for (int i=0 ; i<4 ; i++){
          int newR = r+rOffset[i];
          int newC = c+cOffset[i];
          if(isValid(newR, newC, matrix.length, matrix[0].length)) {
              if(matrix[newR][newC] > currVal){
                  int newPath = 1 + getMaxPathFrom(newR, newC, matrix, maxPathFrom);
                  ans = Math.max(ans, newPath);
              }
          }
      }
      maxPathFrom[r][c] = ans;
      return ans;
  }
  
  public int longestIncreasingPath(int[][] matrix) {
      if(matrix.length == 0 || matrix[0].length == 0) return 0;
      int rows = matrix.length;
      int cols = matrix[0].length;
      
      int[][] maxPathFrom = new int[rows][cols];
      
      int ans = 1;
      for(int r=0 ; r<rows ; r++) {
          for(int c=0 ; c<cols ; c++){
              ans = Math.max(ans, getMaxPathFrom(r, c, matrix, maxPathFrom));
          }
      }
      return ans;
  }
}