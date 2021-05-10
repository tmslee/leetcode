class Solution {
  public int threeSumClosest(int[] nums, int target) {
      if(nums.length == 0) return 0;
      
      Arrays.sort(nums);
      
      int ans = 0;
      boolean first = true;
      int outerPrev = nums[0];
      for(int i=0; i<nums.length ; i++){
          if (i==0 || nums[i] != outerPrev) {
              int l = i+1;
              int r = nums.length-1;
              while(l < r) {
                  int sum = nums[i] + nums[l] + nums[r];
                  
                  if(first) {
                      ans = sum;
                      first = false;
                  } else {
                      int prevDiff = Math.abs(target - ans);
                      int currDiff = Math.abs(target - sum);
                      if(currDiff < prevDiff) ans = sum;
                  }
                  
                  if(sum > target) r--;
                  else if(sum < target) l++;
                  else return target;
              }
          }
          outerPrev = nums[i];
      }
      return ans; 
  }
}