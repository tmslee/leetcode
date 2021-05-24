class Solution {
  // we jsut have to capture all island of O that is fully contained inside outer edge
  // 1. check outermost layer identify all O's and mark them as invalid
  // 2. check inner rectangle and capture any O's not included in invalid
  
  Set<Pair<Integer, Integer>> invalidCoords = new HashSet();
  char[][] board;
  int rows;
  int cols;
  int[] rOffset = {0, 0, 1, -1};
  int[] cOffset = {1, -1, 0, 0};
  
  public boolean isValid (int r, int c) {
      return !(r<0 || r>= rows || c<0 || c>=cols);
  } 
  
  public void expandInvalid (int r, int c) {
      invalidCoords.add(new Pair(r,c));
      for (int i=0 ; i<4 ; i++) {
          int newR = r + rOffset[i];
          int newC = c + cOffset[i];
          Pair<Integer, Integer> newP = new Pair(newR, newC);
          if(isValid(newR, newC) && board[newR][newC] == 'O' && !invalidCoords.contains(newP)){
              expandInvalid(newR, newC);
          }
      }
  }
  
  public void capture (int r, int c) {
      board[r][c] = 'X';
      for (int i=0 ; i<4 ; i++) {
          int newR = r + rOffset[i];
          int newC = c + cOffset[i];
          Pair<Integer, Integer> newP = new Pair(newR, newC);
          if(isValid(newR, newC) && board[newR][newC] == 'O' && !invalidCoords.contains(newP)){
              capture(newR, newC);
          }
      }
  }
  
  public void populateInvalidCoords () {
      for(int r=0 ; r<rows ; r++) {
          if(board[r][0] == 'O' && !invalidCoords.contains(new Pair(r, 0))){
              expandInvalid(r, 0);  
          }
          if(board[r][cols-1] == 'O' && !invalidCoords.contains(new Pair(r, cols-1))){
              expandInvalid(r, cols-1);  
          }    
      }
      for(int c=0 ; c<cols ; c++) {
          if(board[0][c] == 'O' && !invalidCoords.contains(new Pair(0, c))){
              expandInvalid(0, c);  
          }
          if(board[rows-1][c] == 'O' && !invalidCoords.contains(new Pair(rows-1, c))){
              expandInvalid(rows-1, c);  
          }    
      }
  }
  
  public void solve(char[][] board) {
      if(board.length <= 2 || board[0].length <=2) return;
      this.board = board;
      rows = board.length;
      cols = board[0].length;
      
      populateInvalidCoords();
      for(int r=1 ; r<rows-1 ; r++){
          for(int c=1 ; c<cols-1 ; c++){
              if(board[r][c] == 'O' && !invalidCoords.contains(new Pair(r,c))){
                  capture(r,c);
              }
          }
      }
  }
}