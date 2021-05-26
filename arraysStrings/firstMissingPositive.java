class Solution {
    
  public void swap(int[] nums, int idx) {
      int num = nums[idx];
      while(num > 0 && num <= nums.length && idx+1 != num) {
          int newNum = nums[num-1];
          int newIdx = num-1;
          nums[newIdx] = num;
          
          idx=newIdx;
          num = newNum;
      }
  }
  
  public int firstMissingPositive(int[] nums) {
      for(int i=0 ; i<nums.length; i ++){
          if(nums[i] > 0 && nums[i] <= nums.length && i+1 != nums[i]) {
              swap(nums, i);
          }
      }
      for(int i=0 ; i<nums.length ; i++){
          if(i+1 != nums[i]) return i+1;
      }
      return nums.length+1;
  }
}