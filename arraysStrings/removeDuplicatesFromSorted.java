class Solution {
  public int removeDuplicates(int[] nums) {
      if(nums.length == 0) return 0;
      int replaceIdx = 1;
      int lastSeen = nums[0];
      for(int i=1 ; i<nums.length ; i++){
          if(nums[i] != lastSeen) {
              nums[replaceIdx] = nums[i];
              replaceIdx++;
              lastSeen = nums[i];
          }
      }
      return replaceIdx;
  }
}