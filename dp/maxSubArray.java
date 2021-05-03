class Solution {
  public int maxSubArray(int[] nums) {
      int ans = Integer.MIN_VALUE;
      int[] maxSum = new int[nums.length];
      
      for(int i=0 ; i<nums.length ; i++) {
          if (i == 0) maxSum[i] = nums[i];
          else {
              maxSum[i] = Math.max(maxSum[i-1] + nums[i], nums[i]); 
          }
          ans = Math.max(ans, maxSum[i]);
      }
      return ans;
  }
}