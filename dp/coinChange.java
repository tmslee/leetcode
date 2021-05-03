class Solution {
  public int coinChange(int[] coins, int amount) {
      int[] mem = new int[amount+1];
      Arrays.fill(mem, amount+1);
      
      mem[0] = 0;
      
      for (int i=1 ; i<=amount ; i++) {
          for(int j=0 ; j<coins.length ; j++) {
              if (coins[j] <= i) {
                  mem[i] = Math.min(mem[i], mem[i- coins[j]] + 1);
              }
          }
      }
      
      if(mem[amount] == amount+1) return -1;
      else return mem[amount];
  }
}