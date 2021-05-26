class Solution {
  Set<Integer> numSet = new HashSet();
      
  public int longestConsecutive(int[] nums) {
      for(int num : nums) numSet.add(num);
      int longestLen = 0;
      for(int num : nums){
          if(!numSet.contains(num-1)){ //if set does not contain, we can start a new streak
              int currStreak = 1;
              int currNum = num+1;
              while(numSet.contains(currNum)){
                  currStreak++;
                  currNum++;
              }
              longestLen = Math.max(longestLen, currStreak);
          }
      }
      return longestLen;
  }
  
}