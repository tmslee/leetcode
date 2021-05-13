class Solution {
  Map<Integer, Integer> seenStateMap;
  
  public int getBitMap(int[] cells) {
      int state = 0x0;
      for(int i=0; i<cells.length ; i++){
          state <<= 1;
          state = (state|cells[i]);
      }
      return state;
  }
  
  public int[] getNextDay (int[] cells) {
      int [] newCells = new int[cells.length];
      for(int i=0 ; i<cells.length ; i++){
          if (i==0 || i== 7) newCells[i] = 0;
          else if(cells[i-1] == cells[i+1]) newCells[i] = 1;    
          else newCells[i] = 0;
      }
      return newCells;
  }
  
  public int[] prisonAfterNDays(int[] cells, int n) {
      seenStateMap = new HashMap();
      boolean fastForwarded = false;
      
      while (n > 0) {
          if(!fastForwarded){
              int bitMap = getBitMap(cells);
              if (seenStateMap.containsKey(bitMap)) {
                  int cycle = seenStateMap.get(bitMap) - n;
                  n %= cycle;
                  fastForwarded = true;
              } else seenStateMap.put(bitMap, n);
              
          }
          if(n>0){
              cells = getNextDay(cells);
              n--;
          }
          
      }
      return cells;
  }
}