class Solution {
  int[][] mem;
  int rows;
  int cols;
  
  public boolean isValid (int r, int c) {
      return !(r<0 || r>=rows || c<0 || c>=cols);
  }
  
  public int getUniquePaths(int r, int c){
      if(mem[r][c] != 0) return mem[r][c];
      
      int fromUp = isValid(r-1, c) ? getUniquePaths(r-1, c) : 0;
      int fromLeft = isValid(r, c-1) ? getUniquePaths(r, c-1) : 0;
      mem[r][c] = fromUp + fromLeft;
      return mem[r][c];
  }
  
  public int uniquePaths(int m, int n) {
      mem = new int[m][n];
      mem[0][0] = 1;
      rows = m;
      cols = n;
      return getUniquePaths(m-1,n-1);
  }
}