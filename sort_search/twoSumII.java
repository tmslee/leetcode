class Solution {
  public int[] twoSum(int[] numbers, int target) {
      Map<Integer, Integer> seenIdxMap = new HashMap<>();
      for (int i=0 ; i<numbers.length ; i++) {
          int currNum = numbers[i];            
          int complement = target-currNum;
          
          if(seenIdxMap.containsKey(complement)) {
              return new int[] {seenIdxMap.get(complement)+1, i+1};
          }
          seenIdxMap.put(currNum, i);
      }
      return new int[]{-1, -1};
  }
}