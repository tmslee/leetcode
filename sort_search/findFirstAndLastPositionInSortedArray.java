class Solution {
  public int searchExtremeties(int[] nums, int target, boolean isLeftMost){
      int l=0;
      int r=nums.length-1;
      while(l<=r) {
          int mid = l + (r-l)/2;
          if(nums[mid] < target) l = mid+1;
          else if(nums[mid] > target) r = mid-1;
          else {
              if(isLeftMost){
                  if(mid == 0 || nums[mid-1] < target) return mid;
                  else r = mid-1;
              } else {
                  if(mid == nums.length-1 || nums[mid+1] > target) return mid;
                  else l = mid+1;
              }
          }
      }
      return -1;
  }
  
  public int[] searchRange(int[] nums, int target) {
      return new int[] {searchExtremeties(nums, target, true), searchExtremeties(nums, target, false)};
  }
}