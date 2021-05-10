class Solution {
  // rotate by layer starting from outermost parameter
  public void rotate(int[][] matrix) {
      int rows = matrix.length;
      for(int r=0 ; r<rows/2 ; r++){
          for(int c=r ; c<rows-1-r ; c++){
              // transformation
              // r,c -> c, rows-1-r
              // c,rows-1-r -> rows-1-r, rows-1-c
              // rows-1-r, rows-1-c ->  rows-1-c, rows-1-(rows-1-r) => rows-1-c, r
              // rows-1-c, rows-1-(rows-1-r) -> rows-1-(rows-1-r), rows-1-(rows-1-c) => r,c
              int temp = matrix[r][c];
              matrix[r][c] = matrix[rows-1-c][r];
              matrix[rows-1-c][r] = matrix[rows-1-r][rows-1-c];
              matrix[rows-1-r][rows-1-c] = matrix[c][rows-1-r];
              matrix[c][rows-1-r] = temp;
          }
      }
  }
}