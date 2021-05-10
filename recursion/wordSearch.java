class Solution {
    
  public boolean isValid(char[][] board, int r, int c) {
      if (r<0 || r>=board.length || c<0 || c>=board[0].length) return false;
      return true;
  }
  
  public boolean search (char[][] board, int r, int c, String word, int currIdx) {
      if(board[r][c] == word.charAt(currIdx)) {
          board[r][c] = '#';
          if(currIdx == word.length() - 1) return true;
          
          int [] rOffset = {0, 0, 1, -1};
          int [] cOffset = {1, -1, 0, 0};
          for (int i=0 ; i<4 ; i++) {
              int newR = r + rOffset[i];
              int newC = c + cOffset[i];
              if(isValid(board, newR, newC) && search(board, newR, newC, word, currIdx+1)) {
                  return true;
              }
          }
          board[r][c] = word.charAt(currIdx);
          return false;
      } 
      return false;
  }
  
  public boolean exist(char[][] board, String word) {
      if(board.length == 0 || board[0].length == 0) return false;
      for (int r=0 ; r<board.length ; r++){
          for (int c=0 ; c<board[0].length ; c++){
              if(search(board, r, c, word, 0)) return true;
          }
      }
      return false;
  }
}