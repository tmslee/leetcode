class TicTacToe {
  int size;
  int[][] rowsCount;
  int[][] colsCount;
  int[][] diagCount;
  
  /** Initialize your data structure here. */
  public TicTacToe(int n) {
      size = n;
      rowsCount = new int[2][n];
      colsCount = new int[2][n];
      diagCount = new int[2][2];
  }
  
  /** Player {player} makes a move at ({row}, {col}).
      @param row The row of the board.
      @param col The column of the board.
      @param player The player, can be either 1 or 2.
      @return The current winning condition, can be either:
              0: No one wins.
              1: Player 1 wins.
              2: Player 2 wins. */
  public int move(int row, int col, int player) {
      int pIdx = player-1;
      
      rowsCount[pIdx][row] ++;
      colsCount[pIdx][col] ++;
      if(row == col) diagCount[pIdx][0]++;
      if(row+col == size-1) diagCount[pIdx][1]++;
      
      if(rowsCount[pIdx][row] == size || 
         colsCount[pIdx][col] == size || 
         diagCount[pIdx][0] == size || 
         diagCount[pIdx][1] == size) return player;
      else return 0;
  }
}

/**
* Your TicTacToe object will be instantiated and called as such:
* TicTacToe obj = new TicTacToe(n);
* int param_1 = obj.move(row,col,player);
*/