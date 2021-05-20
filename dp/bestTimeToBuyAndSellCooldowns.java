class Solution {
  //     public int maxProfit(int[] prices) {
  //         //0 buy -> sell/waitY
  //         //1 sell -> cooldown
  //         //2 cooldown -> buy/waitN
  //         //3 waitY -> sell/waitY
  //         //4 waitN -> buy/waitN
          
  //         // cooldown/waitN -> buy
  //         // buy/waitY -> sell
  //         // sell -> cooldown
  //         // buy/waitY -> waitY
  //         // cooldown/waitN -> waitN
  //         if(prices.length <= 1) return 0;
          
  //         int[][] stateMemory = new int[prices.length][5];
  //         stateMemory[0][0] = -prices[0];
  //         stateMemory[0][1] = 0;
  //         stateMemory[0][2] = 0;
  //         stateMemory[0][3] = 0;
  //         stateMemory[0][4] = 0; 
          
  //         stateMemory[1][0] = -prices[1];
  //         stateMemory[1][1] = stateMemory[0][0] + prices[1];
  //         stateMemory[1][2] = 0;
  //         stateMemory[1][3] = stateMemory[0][0];
  //         stateMemory[1][4] = 0; 
          
  //         for(int i=2 ; i<prices.length ; i++) {
  //             stateMemory[i][0] = Math.max(stateMemory[i-1][2], stateMemory[i-1][4]) - prices[i];
  //             stateMemory[i][1] = Math.max(stateMemory[i-1][0], stateMemory[i-1][3]) + prices[i]; 
  //             stateMemory[i][2] = stateMemory[i-1][1];
  //             stateMemory[i][3] = Math.max(stateMemory[i-1][0], stateMemory[i-1][3]);
  //             stateMemory[i][4] = Math.max(stateMemory[i-1][2], stateMemory[i-1][4]);
  //         }
  //         return Math.max(Math.max(
  //             Math.max(stateMemory[prices.length-1][0], stateMemory[prices.length-1][1]),
  //             Math.max(stateMemory[prices.length-1][2], stateMemory[prices.length-1][3])
  //             ), stateMemory[prices.length-1][4]);
  //     }
  
      public int maxProfit(int[] prices) {
          //held, sold, reset
          // held/reset -> held
          // held -> sold
          // sold/reset -> reset
          
          int held = Integer.MIN_VALUE;
          int sold = Integer.MIN_VALUE;
          int reset = 0;
              
          for(int i=0 ; i<prices.length ; i++){
              int tempHeld = held;
              int tempSold = sold;
              int tempReset = reset;
              
              held = Math.max(tempHeld, tempReset - prices[i]);
              sold = tempHeld + prices[i];
              reset = Math.max(tempSold, tempReset);
          }
          return Math.max(sold, reset);
      }
  }