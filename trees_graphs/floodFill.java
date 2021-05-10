class Solution {
  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
      if(image[sr][sc] == newColor) return image;
      fillHelper(image, sr, sc, newColor);
      return image;
  }
  
  public void fillHelper(int[][]image, int r, int c, int newColor) {
      int srcColor = image[r][c];
      image[r][c] = newColor;
      int [] rOffset = {0, 0, 1, -1};
      int [] cOffset = {1, -1, 0, 0};
      for (int i=0 ; i<4; i++) {
          int newR = r + rOffset[i];
          int newC = c + cOffset[i];
          if(isValid(image, newR, newC) && image[newR][newC] == srcColor) {
              fillHelper(image, newR, newC, newColor);
          }
      }
  }
  
  public boolean isValid (int[][] image, int r, int c){
      if(r<0 || r>=image.length || c<0 || c>=image[0].length) return false;
      return true;
  }
}