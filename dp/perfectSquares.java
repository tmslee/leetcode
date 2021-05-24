class Solution {
  List<Integer> squares = new ArrayList();
  
  public int getNumSquares(int n, int[] mem) {
      if(mem[n] > 0) return mem[n];
      int ans = Integer.MAX_VALUE;
      
      for(Integer square : squares) {
          if(square > n) break;
          else ans = Math.min(ans, 1 + getNumSquares(n-square, mem)); 
      }
      mem[n] = ans;
      return ans;
  }
  
  
  public int numSquares(int n) {
      int squareLimit = (int) (Math.floor(Math.sqrt(n)));
      int[] mem = new int[n+1];
      for(int i=1; i <=squareLimit ; i++){
          mem[i*i] = 1;
          squares.add(i*i);
      }
      return getNumSquares(n, mem);
  }
}