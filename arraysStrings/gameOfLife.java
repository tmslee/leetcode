class Solution {
  /*
  0 -> 0 to 0
  1 -> 1 to 1
  2 -> 0 to 1
  3 -> 1 to 0
  */
  int rows;
  int cols;
  int[] rOffset = {0, 0, 1, -1, 1, -1, 1, -1};
  int[] cOffset = {1, -1, 0, 0, 1, -1, -1, 1};
  
  public boolean isValid (int r, int c) {
      return !(r<0 || r>= rows || c<0 || c>=cols);
  }
  
  public void gameOfLife(int[][] board) {
      rows = board.length;
      cols = board[0].length;
      
      for(int r=0 ; r<board.length ; r++){
          for(int c=0 ; c<board[0].length ; c++){
              int surrounding = 0;
              for(int i=0 ; i<8 ; i++){
                  int newR = r + rOffset[i];
                  int newC = c + cOffset[i];
                  if(isValid(newR, newC) &&(board[newR][newC] == 1 || board[newR][newC] == 3)){
                      surrounding++;
                  }
              }
              if(board[r][c] == 0){
                  if(surrounding == 3) board[r][c] = 2;
              } else if (board[r][c] == 1){
                  if(surrounding < 2 || surrounding > 3) board[r][c] = 3;
              }
          }
      }
      
      for(int r=0 ; r<board.length ; r++){
          for(int c=0 ; c<board[0].length ; c++){
              if(board[r][c] == 2) board[r][c] = 1;
              else if(board[r][c] == 3) board[r][c] = 0;
          }
      }
  }
}